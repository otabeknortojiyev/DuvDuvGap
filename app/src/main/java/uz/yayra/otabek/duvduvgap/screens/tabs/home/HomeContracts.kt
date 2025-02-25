package uz.yayra.otabek.duvduvgap.screens.tabs.home

import org.orbitmvi.orbit.ContainerHost
import uz.yayra.otabek.duvduvgap.data.models.NewsData

interface HomeContracts {

    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val isLoading: Boolean = false,
        val randomNews: List<NewsData> = arrayListOf(
            NewsData(
                0,
                "Politics",
                "The latest situation in the presidential election",
                "https://firebasestorage.googleapis.com/v0/b/examcloud-557a8.appspot.com/o/file%2Fcityyy.jpg?alt=media&token=ee9acb1d-7196-4403-8811-5c306655e451"
            )
        ),
        val recommended: List<NewsData> = arrayListOf(
            NewsData(
                0,
                "Politics",
                "The latest situation in the presidential election",
                "https://firebasestorage.googleapis.com/v0/b/examcloud-557a8.appspot.com/o/file%2Fcityyy.jpg?alt=media&token=ee9acb1d-7196-4403-8811-5c306655e451"
            )
        ),
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