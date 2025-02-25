package uz.yayra.otabek.duvduvgap.screens.tabs.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.orbitmvi.orbit.compose.collectAsState

object ProfileTab : Tab {
    private fun readResolve(): Any = ProfileTab

    override val options: TabOptions
        @Composable get() {
            val title = "Profile"

            return remember {
                TabOptions(
                    index = 0u, title = title
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: ProfileContracts.ViewModel = getViewModel<ProfileViewModel>()
        val uiState = viewModel.collectAsState()
        ProfileScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreenContent(uiState: State<ProfileContracts.UiState>, onEventDispatcher: (ProfileContracts.Intent) -> Unit) {
    val rects = listOf(
        Items(50f, 0xff16a085),
        Items(100f, 0xff8e44ad),
        Items(75f, 0xff2980b9),
        Items(125f, 0xff2c3e50),
        Items(100f, 0xfff39c12),
        Items(75f, 0xff27ae60),
        Items(50f, 0xffd35400),
        Items(110f, 0xfff6b93b),
        Items(100f, 0xff0a3d62),
        Items(75f, 0xffb71540)
    )
    FlowRow(modifier = Modifier.fillMaxSize(), maxItemsInEachRow = 4) {
        rects.forEach {
            Box(
                modifier = Modifier
                    .size(Dp(it.width), 100.dp)
                    .padding(5.dp)
                    .background(Color(it.color))
            )
        }
    }
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White)
    systemUiController.setNavigationBarColor(Color.White)
}

data class Items(val width: Float, val color: Long)