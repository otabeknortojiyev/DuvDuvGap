package uz.yayra.otabek.duvduvgap.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("news")
data class NewsData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val genre: String,
    val title: String,
    val imagePath: String,
)