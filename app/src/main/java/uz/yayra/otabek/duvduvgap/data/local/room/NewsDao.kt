package uz.yayra.otabek.duvduvgap.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.yayra.otabek.duvduvgap.data.models.NewsData

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllNews(): Flow<List<NewsData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsLatest(news: List<NewsData>)

    @Query("DELETE FROM news")
    fun deleteNewsLatest()
}