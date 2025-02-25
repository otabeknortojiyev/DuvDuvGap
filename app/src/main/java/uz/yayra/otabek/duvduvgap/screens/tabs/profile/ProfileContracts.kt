package uz.yayra.otabek.duvduvgap.screens.tabs.profile

import org.orbitmvi.orbit.ContainerHost

interface ProfileContracts {

    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val isLoading: Boolean = false,
    )

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Directions {

    }

    interface Intent {

    }
}