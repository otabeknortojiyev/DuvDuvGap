package uz.yayra.otabek.duvduvgap.screens.splash

import org.orbitmvi.orbit.ContainerHost

interface SplashContract {

    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(val isLoading: Boolean = false)

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Direction {
        suspend fun goToHomeTab()
        suspend fun goToOnboarding()
    }


    interface Intent {
        data object Init : Intent
    }
}