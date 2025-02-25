package uz.yayra.otabek.duvduvgap.screens.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.yayra.otabek.duvduvgap.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val direction: SplashContract.Direction,
    private val repository: AppRepository,
) : ViewModel(), SplashContract.ViewModel {

    override fun onEventDispatcher(intent: SplashContract.Intent) = intent {
        when (intent) {
            SplashContract.Intent.Init -> {
                if (repository.isFirst()) {
                    direction.goToOnboarding()
                } else {
                    direction.goToHomeTab()
                }
            }
        }
    }

    override val container = container<SplashContract.UiState, SplashContract.SideEffect>(SplashContract.UiState())
}