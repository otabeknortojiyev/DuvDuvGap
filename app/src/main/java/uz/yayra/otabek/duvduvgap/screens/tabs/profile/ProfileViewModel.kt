package uz.yayra.otabek.duvduvgap.screens.tabs.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val direction: ProfileContracts.Directions,
) : ProfileContracts.ViewModel, ViewModel() {

    override fun onEventDispatcher(intent: ProfileContracts.Intent) = intent {
        when (intent) {

        }
    }

    override val container = container<ProfileContracts.UiState, ProfileContracts.SideEffect>(ProfileContracts.UiState())
}