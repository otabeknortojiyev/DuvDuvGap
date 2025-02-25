package uz.yayra.otabek.duvduvgap.data.network

import retrofit2.Response
import retrofit2.http.GET
import uz.yayra.otabek.duvduvgap.data.response.NewsResponseEnglish

interface HomeApi {
    @GET("news?lang=english")
    suspend fun getNewsEnglish(): Response<NewsResponseEnglish>
}