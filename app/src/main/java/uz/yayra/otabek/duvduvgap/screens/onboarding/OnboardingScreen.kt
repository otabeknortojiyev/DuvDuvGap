package uz.yayra.otabek.duvduvgap.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.orbitmvi.orbit.compose.collectAsState
import uz.yayra.otabek.duvduvgap.R
import uz.yayra.otabek.duvduvgap.ui.theme.BlackPrimary
import uz.yayra.otabek.duvduvgap.ui.theme.GrayPrimary
import uz.yayra.otabek.duvduvgap.ui.theme.PurplePrimary
import kotlin.math.absoluteValue

object OnboardingScreen : Screen {
    private fun readResolve(): Any = OnboardingScreen

    @Composable
    override fun Content() {
        val viewModel: OnboardingContract.ViewModel = getViewModel<OnboardingViewModel>()
        val uiState = viewModel.collectAsState()
        OnboardingScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreenContent(
    uiState: State<OnboardingContract.UiState>, onEventDispatcher: (OnboardingContract.Intent) -> Unit,
) {
    val pagerState = rememberPagerState { 3 }
    val list = arrayListOf(R.drawable.image1_onboarding, R.drawable.image2_onboarding, R.drawable.image3_onboarding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            state = pagerState,
            pageSpacing = 20.dp
        ) {
            Image(
                painter = when (it) {
                    0 -> {
                        painterResource(id = list[0])
                    }

                    1 -> {
                        painterResource(id = list[1])
                    }

                    else -> {
                        painterResource(id = list[2])
                    }
                }, contentDescription = null
            )
        }
        HorizontalPagerIndicator(
            pageCount = 3,
            currentPage = pagerState.currentPage,
            targetPage = pagerState.targetPage,
            currentPageOffsetFraction = pagerState.currentPageOffsetFraction
        )
        Text(
            text = "First to know",
            color = BlackPrimary,
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Text(
            text = "All news in one place, be the\nfirst to know last news",
            textAlign = TextAlign.Center,
            color = GrayPrimary,
            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
            modifier = Modifier.padding(bottom = 48.dp)
        )
        Text(
            text = "Get Started", color = Color.White, textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp, end = 20.dp, bottom = 20.dp
                )
                .clip(shape = RoundedCornerShape(20.dp))
                .background(PurplePrimary)
                .clickable {
                    onEventDispatcher(OnboardingContract.Intent.Init)
                }
                .padding(vertical = 16.dp),
        )
    }
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White)
    systemUiController.setNavigationBarColor(Color.White)
}

@Composable
private fun HorizontalPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    targetPage: Int,
    currentPageOffsetFraction: Float,
    modifier: Modifier = Modifier,
    indicatorColor: Color = PurplePrimary,
    unselectedIndicatorSize: Dp = 16.dp,
    selectedIndicatorSize: Dp = 20.dp,
    indicatorCornerRadius: Dp = 4.dp,
    indicatorPadding: Dp = 8.dp,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentSize()
            .height(selectedIndicatorSize + indicatorPadding * 2)
    ) {
        repeat(pageCount) { page ->
            val (color, size) = if (currentPage == page || targetPage == page) {
                val pageOffset = ((currentPage - page) + currentPageOffsetFraction).absoluteValue
                val offsetPercentage = 1f - pageOffset.coerceIn(0f, 1f)

                val size = unselectedIndicatorSize + ((selectedIndicatorSize - unselectedIndicatorSize) * offsetPercentage)

                indicatorColor.copy(
                    alpha = offsetPercentage
                ) to size
            } else {
                indicatorColor.copy(alpha = 0.1f) to unselectedIndicatorSize
            }
            Box(
                modifier = Modifier
                    .padding(
                        horizontal = ((selectedIndicatorSize + indicatorPadding * 2) - size) / 2, vertical = size / 4
                    )
                    .clip(RoundedCornerShape(indicatorCornerRadius))
                    .background(color)
                    .width(size)
                    .height(size / 2)
            )
        }
    }
}