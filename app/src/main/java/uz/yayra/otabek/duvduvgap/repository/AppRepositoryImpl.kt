package uz.yayra.otabek.duvduvgap.repository

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import uz.yayra.otabek.duvduvgap.data.local.LocalStorage
import uz.yayra.otabek.duvduvgap.data.local.room.NewsDataBase
import uz.yayra.otabek.duvduvgap.data.models.NewsData
import uz.yayra.otabek.duvduvgap.data.network.HomeApi
import uz.yayra.otabek.duvduvgap.utils.toResult
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val storage: LocalStorage, private val api: HomeApi, private val dataBase: NewsDataBase) :
    AppRepository {
    private val gson = Gson()

    override fun isFirst(): Boolean = storage.isFirstEnter
    override fun setFirst() {
        storage.isFirstEnter = false
    }

    override suspend fun getNewsEnglish(): Result<Unit> = withContext(Dispatchers.IO) {
        api.getNewsEnglish().toResult(gson) {
            val latest = it.latest
            dataBase.newsDao().deleteNewsLatest()
            dataBase.newsDao().insertNewsLatest(latest!!.map { NewsData(0, "Latest", title = it.title, imagePath = it.imageLink ?: "no") })
            Result.success(Unit)
        }
    }

    override fun getFromLocal(): Flow<List<NewsData>> {
        return dataBase.newsDao().getAllNews()
    }
}