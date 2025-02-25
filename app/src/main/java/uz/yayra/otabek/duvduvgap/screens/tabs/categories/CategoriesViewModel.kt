package uz.yayra.otabek.duvduvgap.screens.tabs.categories

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val direction: CategoriesContracts.Directions,
) : CategoriesContracts.ViewModel, ViewModel() {

    override fun onEventDispatcher(intent: CategoriesContracts.Intent) = intent {
        when (intent) {

        }
    }

    override val container = container<CategoriesContracts.UiState, CategoriesContracts.SideEffect>(CategoriesContracts.UiState())
}