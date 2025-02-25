package uz.yayra.otabek.duvduvgap.screens.tabs.bookmarks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.orbitmvi.orbit.compose.collectAsState
import uz.yayra.otabek.duvduvgap.R
import uz.yayra.otabek.duvduvgap.screens.tabs.home.Recommended
import uz.yayra.otabek.duvduvgap.ui.theme.BlackPrimary
import uz.yayra.otabek.duvduvgap.ui.theme.GrayPrimary

object BookmarksTab : Tab {
    private fun readResolve(): Any = BookmarksTab

    override val options: TabOptions
        @Composable get() {
            val title = "Bookmark"

            return remember {
                TabOptions(
                    index = 0u, title = title
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: BookmarksContracts.ViewModel = getViewModel<BookmarksViewModel>()
        val uiState = viewModel.collectAsState()
        BookmarksScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@Composable
fun BookmarksScreenContent(uiState: State<BookmarksContracts.UiState>, onEventDispatcher: (BookmarksContracts.Intent) -> Unit) {
    LaunchedEffect(Unit) {
        onEventDispatcher(BookmarksContracts.Intent.Init)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 20.dp),
    ) {
        Text(
            text = "Bookmarks",
            color = BlackPrimary,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp, top = 20.dp),
            fontFamily = FontFamily(Font(R.font.montserrat_semibold))
        )
        Text(
            text = "Saved articles to the library", color = GrayPrimary, fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.montserrat_regular))
        )
        if (uiState.value.saved.isEmpty()) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.bookmark_placeholder), contentDescription = null)
                Text(
                    text = "You haven't saved any articles yet. Start reading and bookmarking them now",
                    color = BlackPrimary,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp)
                )
            }
        } else {
            LazyColumn {
                itemsIndexed(uiState.value.saved) { index, item ->
                    Recommended(item, index == uiState.value.saved.size - 1)
                }
            }
        }
    }
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White)
    systemUiController.setNavigationBarColor(Color.White)
}