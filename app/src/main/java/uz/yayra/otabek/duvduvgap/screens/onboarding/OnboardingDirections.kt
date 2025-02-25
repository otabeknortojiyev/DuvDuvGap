package uz.yayra.otabek.duvduvgap.screens.onboarding

import uz.yayra.otabek.duvduvgap.screens.main.MainScreen
import uz.yayra.otabek.duvduvgap.ui.navigation.AppNavigator
import javax.inject.Inject

class OnboardingDirections @Inject constructor(private val navigator: AppNavigator) : OnboardingContract.Directions {
    override suspend fun goToHomeTab() {
        navigator.replaceAll(MainScreen)
    }
}