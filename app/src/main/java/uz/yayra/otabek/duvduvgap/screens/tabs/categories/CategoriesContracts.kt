package uz.yayra.otabek.duvduvgap.screens.tabs.categories

import org.orbitmvi.orbit.ContainerHost
import uz.yayra.otabek.duvduvgap.R
import uz.yayra.otabek.duvduvgap.data.models.CategoryData

interface CategoriesContracts {

    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val isLoading: Boolean = false,
        val categories: List<CategoryData> = arrayListOf(
            CategoryData(R.drawable.sport, "Sports"),
            CategoryData(R.drawable.scale, "Politics"),
            CategoryData(R.drawable.sun, "Life"),
            CategoryData(R.drawable.game, "Gaming"),
            CategoryData(R.drawable.giraffe, "Animals"),
            CategoryData(R.drawable.tree, "Nature"),
            CategoryData(R.drawable.burger, "Food"),
            CategoryData(R.drawable.art, "Art"),
            CategoryData(R.drawable.paper, "History"),
            CategoryData(R.drawable.fashion, "Fashion")
        ),
    )

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Directions {

    }

    interface Intent {

    }
}