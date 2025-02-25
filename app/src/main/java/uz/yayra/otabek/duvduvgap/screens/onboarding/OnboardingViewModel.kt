package uz.yayra.otabek.duvduvgap.screens.onboarding

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.yayra.otabek.duvduvgap.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val directions: OnboardingContract.Directions, private val repository: AppRepository) :
    ViewModel(), OnboardingContract.ViewModel {
    override fun onEventDispatcher(intent: OnboardingContract.Intent) = intent {
        when (intent) {
            OnboardingContract.Intent.Init -> {
                repository.setFirst()
                directions.goToHomeTab()
            }
        }
    }

    override val container = container<OnboardingContract.UiState, OnboardingContract.SideEffect>(OnboardingContract.UiState())

}