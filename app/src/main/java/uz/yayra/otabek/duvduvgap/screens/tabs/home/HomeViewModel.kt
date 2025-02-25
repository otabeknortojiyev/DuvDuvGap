package uz.yayra.otabek.duvduvgap.screens.tabs.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.yayra.otabek.duvduvgap.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val direction: HomeContracts.Directions, private val repository: AppRepository,
) : HomeContracts.ViewModel, ViewModel() {

    override fun onEventDispatcher(intent: HomeContracts.Intent) = intent {
        when (intent) {
            HomeContracts.Intent.Init -> {
                repository.getFromLocal().onStart {
                    reduce { state.copy(isLoading = true) }
                }.onEach {
                    reduce { state.copy(randomNews = it) }
                }.launchIn(viewModelScope)

//                viewModelScope.launch {
//                    val result = repository.getNewsEnglish()
//                    result.onSuccess {
//                        val latest = it.latest
//                        reduce {
//                            state.copy(isLoading = false, randomNews = latest!!.map {
//                                NewsData(
//                                    0, "Latest", it.title, imagePath = if (it.imageLink != null) {
//                                        it.imageLink.toString()
//                                    } else {
//                                        "no"
//                                    }
//                                )
//                            })
//                        }
//                        val news = it.news
//                        if (news != null) {
//                            reduce {
//                                state.copy(isLoading = false, recommended = news!!.map {
//                                    NewsData(
//                                        0, genre = it.summary.toString(), it.title, imagePath = if (it.imageLink != null) {
//                                            it.imageLink.toString()
//                                        } else {
//                                            "no"
//                                        }
//                                    )
//                                })
//                            }
//                        }
//                    }.onFailure {
//                        postSideEffect(HomeContracts.SideEffect.ResultMessage(it.message.toString()))
//                    }
//                }
            }
        }
    }

    override val container = container<HomeContracts.UiState, HomeContracts.SideEffect>(HomeContracts.UiState())
}