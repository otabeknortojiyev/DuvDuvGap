package uz.yayra.otabek.duvduvgap.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.yayra.otabek.duvduvgap.screens.onboarding.OnboardingContract
import uz.yayra.otabek.duvduvgap.screens.onboarding.OnboardingDirections
import uz.yayra.otabek.duvduvgap.screens.splash.SplashContract
import uz.yayra.otabek.duvduvgap.screens.splash.SplashDirections
import uz.yayra.otabek.duvduvgap.screens.tabs.bookmarks.BookmarksContracts
import uz.yayra.otabek.duvduvgap.screens.tabs.bookmarks.BookmarksDirections
import uz.yayra.otabek.duvduvgap.screens.tabs.categories.CategoriesContracts
import uz.yayra.otabek.duvduvgap.screens.tabs.categories.CategoriesDirections
import uz.yayra.otabek.duvduvgap.screens.tabs.home.HomeContracts
import uz.yayra.otabek.duvduvgap.screens.tabs.home.HomeDirections
import uz.yayra.otabek.duvduvgap.screens.tabs.profile.ProfileContracts
import uz.yayra.otabek.duvduvgap.screens.tabs.profile.ProfileDirections

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @[Binds ViewModelScoped]
    fun bindSignUpDirection(impl: SplashDirections): SplashContract.Direction

    @[Binds ViewModelScoped]
    fun bindOnboardingDirection(impl: OnboardingDirections): OnboardingContract.Directions

    @[Binds ViewModelScoped]
    fun bindHomeDirection(impl: HomeDirections): HomeContracts.Directions

    @[Binds ViewModelScoped]
    fun bindCategoriesDirection(impl: CategoriesDirections): CategoriesContracts.Directions

    @[Binds ViewModelScoped]
    fun bindBookmarksDirection(impl: BookmarksDirections): BookmarksContracts.Directions

    @[Binds ViewModelScoped]
    fun bindProfileDirection(impl: ProfileDirections): ProfileContracts.Directions

}