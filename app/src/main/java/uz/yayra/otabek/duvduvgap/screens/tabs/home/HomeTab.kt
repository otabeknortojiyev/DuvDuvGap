package uz.yayra.otabek.duvduvgap.screens.tabs.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import coil3.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.orbitmvi.orbit.compose.collectAsState
import uz.yayra.otabek.duvduvgap.R
import uz.yayra.otabek.duvduvgap.data.models.NewsData
import uz.yayra.otabek.duvduvgap.ui.theme.BlackDarker
import uz.yayra.otabek.duvduvgap.ui.theme.BlackPrimary
import uz.yayra.otabek.duvduvgap.ui.theme.GrayLighter
import uz.yayra.otabek.duvduvgap.ui.theme.GrayPrimary
import uz.yayra.otabek.duvduvgap.utils.AppTextField


object HomeTab : Tab {
    private fun readResolve(): Any = HomeTab

    override val options: TabOptions
        @Composable get() {
            val title = "Home"

            return remember {
                TabOptions(
                    index = 0u, title = title
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: HomeContracts.ViewModel = getViewModel<HomeViewModel>()
        val uiState = viewModel.collectAsState()
        HomeScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeScreenContent(
    uiState: State<HomeContracts.UiState>, onEventDispatcher: (HomeContracts.Intent) -> Unit,
) {
    LaunchedEffect(Unit) {
        onEventDispatcher(HomeContracts.Intent.Init)
    }
    val pullRefreshState =
        rememberPullRefreshState(refreshing = uiState.value.isLoading, onRefresh = { onEventDispatcher(HomeContracts.Intent.Init) })
    val search = remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            item {
                Text(
                    text = "Browse",
                    color = BlackPrimary,
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                    fontSize = 24.sp
                )
                Text(
                    text = "Discover things of this world",
                    color = GrayPrimary,
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_regular)
                    ),
                    fontSize = 16.sp
                )
                AppTextField(
                    value = search.value,
                    onValueChange = {
                        search.value = it
                    },
                    hint = "Search",
                    leadingIcon = {
                        Image(painter = painterResource(id = R.drawable.search), contentDescription = null)
                    },
                    trailingIcon = {
                        Image(painter = painterResource(id = R.drawable.microphone),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(10.dp))
                                .clickable {

                                }
                                .padding(4.dp))
                    },
                    textStyle = TextStyle(color = BlackDarker),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 24.dp)
                )
                Text(
                    text = "Latest",
                    color = BlackPrimary,
                    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
                LazyRow {
                    itemsIndexed(uiState.value.randomNews) { index, item ->
                        BigNews(item)

                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 36.dp, bottom = 20.dp, end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Recommended for you", color = BlackPrimary, fontFamily = FontFamily(Font(R.font.montserrat_medium)), fontSize = 20.sp
                    )
                    Text(text = "See All", color = GrayPrimary, fontFamily = FontFamily(Font(R.font.montserrat_regular)), fontSize = 16.sp)
                }
            }
            itemsIndexed(uiState.value.recommended) { index, item ->
                Recommended(
                    item, index == uiState.value.recommended.size - 1
                )
            }
        }
        PullRefreshIndicator(
            refreshing = uiState.value.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            backgroundColor = if (uiState.value.isLoading) Color.Red else Color.Green,
        )
    }
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White)
    systemUiController.setNavigationBarColor(Color.White)
}

@Composable
fun BigNews(data: NewsData) {
    Box(
        modifier = Modifier
            .size(300.dp)
            .padding(20.dp)
            .clip(shape = RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = if (data.imagePath != "no") {
                rememberAsyncImagePainter(data.imagePath)
            } else {
                painterResource(id = R.drawable.big_background)
            }, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier
                .fillMaxSize()
                .alpha(0.9f)
        )
        Image(painter = painterResource(id = R.drawable.bookmarks_white),
            contentDescription = null,
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .padding(20.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .clickable {

                }
                .padding(4.dp))
        Column(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
        ) {
            Text(
                text = data.genre,
                color = GrayLighter,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 10.dp),
                fontFamily = FontFamily(Font(R.font.montserrat_light))
            )
            Text(
                text = data.title, color = Color.White, fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.montserrat_semibold))
            )
        }
    }
}

@Composable
fun Recommended(data: NewsData, isLast: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = if (isLast) {
                    64.dp
                } else {
                    0.dp
                }
            ), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .padding(20.dp)
                .clip(shape = RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = if (data.imagePath != "no") {
                    rememberAsyncImagePainter(data.imagePath)
                } else {
                    painterResource(id = R.drawable.big_background)
                }, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = data.title, color = BlackPrimary, modifier = Modifier.padding(2.dp), fontFamily = FontFamily(Font(R.font.montserrat_medium)))
            Text(
                text = if (data.genre != "null") {
                    data.genre
                } else {
                    ""
                }, color = GrayPrimary, modifier = Modifier.padding(2.dp), fontFamily = FontFamily(Font(R.font.montserrat_light))
            )
        }
    }
}