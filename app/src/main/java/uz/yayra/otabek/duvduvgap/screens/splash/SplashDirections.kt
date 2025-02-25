package uz.yayra.otabek.duvduvgap.screens.splash

import uz.yayra.otabek.duvduvgap.screens.main.MainScreen
import uz.yayra.otabek.duvduvgap.screens.onboarding.OnboardingScreen
import uz.yayra.otabek.duvduvgap.ui.navigation.AppNavigator
import javax.inject.Inject

class SplashDirections @Inject constructor(private val navigator: AppNavigator) : SplashContract.Direction {
    override suspend fun goToHomeTab() {
        navigator.replaceAll(MainScreen)
    }

    override suspend fun goToOnboarding() {
        navigator.replaceAll(OnboardingScreen)
    }
}