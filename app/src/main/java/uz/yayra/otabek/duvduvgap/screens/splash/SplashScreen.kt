package uz.yayra.otabek.duvduvgap.screens.splash

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import uz.yayra.otabek.duvduvgap.R
import uz.yayra.otabek.duvduvgap.ui.theme.PurplePrimary


object SplashScreen : Screen {
    private fun readResolve(): Any = SplashScreen

    @Composable
    override fun Content() {
        val viewModel: SplashContract.ViewModel = getViewModel<SplashViewModel>()
        val uiState = viewModel.collectAsState()
        SplashScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@SuppressLint("ResourceAsColor")
@Composable
private fun SplashScreenContent(
    uiState: State<SplashContract.UiState>, onEventDispatcher: (SplashContract.Intent) -> Unit,
) {
    var isRotated by remember { mutableStateOf(false) }
    var isText by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isRotated) -45f else 0f, animationSpec = if (isRotated) {
            tween(durationMillis = 500)
        } else {
            tween(durationMillis = 500)
        }, label = ""
    )
    val scale by animateFloatAsState(
        targetValue = if (isRotated) 2.5f else 1f, animationSpec = if (isRotated) {
            tween(durationMillis = 500)
        } else {
            tween(durationMillis = 500)
        }, label = ""
    )
    val alpha by animateFloatAsState(
        targetValue = if (isText) 1f else 0f, animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing), label = ""
    )
    LaunchedEffect(Unit) {
        isRotated = true
        delay(500)
        isRotated = false
        delay(400)
        isText = true
        delay(500)
        onEventDispatcher(SplashContract.Intent.Init)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 36.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_icon), contentDescription = null, modifier = Modifier
                    .rotate(rotation)
                    .scale(scale)
            )
            Text(
                text = "Duv Duv Gap",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp)
                    .alpha(alpha),
                textAlign = TextAlign.Start,
                color = PurplePrimary,
                fontSize = 36.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_semibold))
            )
        }
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White)
    systemUiController.setNavigationBarColor(Color.White)
}