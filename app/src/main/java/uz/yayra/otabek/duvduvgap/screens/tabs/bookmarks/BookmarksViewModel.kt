package uz.yayra.otabek.duvduvgap.screens.tabs.bookmarks

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.yayra.otabek.duvduvgap.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val direction: BookmarksContracts.Directions, private val repository: AppRepository,
) : BookmarksContracts.ViewModel, ViewModel() {

    override fun onEventDispatcher(intent: BookmarksContracts.Intent) = intent {
        when (intent) {
            BookmarksContracts.Intent.Init -> {
                reduce { state.copy(isLoading = true) }
                val news = repository.getFromLocal()
                reduce { state.copy(isLoading = false, saved = news) }
            }
        }
    }

    override val container = container<BookmarksContracts.UiState, BookmarksContracts.SideEffect>(BookmarksContracts.UiState())
}