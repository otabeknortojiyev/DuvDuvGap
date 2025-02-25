package uz.yayra.otabek.duvduvgap.screens.tabs.bookmarks

import org.orbitmvi.orbit.ContainerHost
import uz.yayra.otabek.duvduvgap.data.models.NewsData

interface BookmarksContracts {

    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val isLoading: Boolean = false,
        val saved: List<NewsData> = emptyList(),
    )

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Directions {

    }

    interface Intent {
        data object Init : Intent
    }
}