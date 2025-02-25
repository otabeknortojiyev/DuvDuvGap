package uz.yayra.otabek.duvduvgap.repository

import kotlinx.coroutines.flow.Flow
import uz.yayra.otabek.duvduvgap.data.models.NewsData

interface AppRepository {
    fun isFirst(): Boolean
    fun setFirst()

    suspend fun getNewsEnglish(): Result<Unit>
    fun getFromLocal(): Flow<List<NewsData>>
}