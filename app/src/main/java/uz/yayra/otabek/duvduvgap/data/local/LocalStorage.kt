package uz.yayra.otabek.duvduvgap.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalStorage @Inject constructor(@ApplicationContext context: Context) : SharedPreference(context) {
    var isFirstEnter by booleans(false)
}