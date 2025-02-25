package uz.yayra.otabek.duvduvgap.screens.onboarding

import org.orbitmvi.orbit.ContainerHost

interface OnboardingContract {

    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(val isLoading: Boolean = false)

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Directions {
        suspend fun goToHomeTab()
    }

    interface Intent {
        data object Init : Intent
    }
}