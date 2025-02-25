package uz.yayra.otabek.duvduvgap.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import uz.yayra.otabek.duvduvgap.repository.AppRepository

class NewsWorker(context: Context, params: WorkerParameters, private val repository: AppRepository) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            val result = repository.getNewsEnglish()
            if (result.isSuccess) {
                Result.success()
            } else {
                Result.retry()
            }
        } catch (e: Exception) {
            Result.failure()
        }
    }
}