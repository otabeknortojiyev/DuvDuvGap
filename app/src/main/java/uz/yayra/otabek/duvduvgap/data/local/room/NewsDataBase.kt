package uz.yayra.otabek.duvduvgap.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.yayra.otabek.duvduvgap.data.models.NewsData


@Database(entities = [NewsData::class], version = 1)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}