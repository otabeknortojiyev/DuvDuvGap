package uz.yayra.otabek.duvduvgap.screens.tabs.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.orbitmvi.orbit.compose.collectAsState
import uz.yayra.otabek.duvduvgap.R
import uz.yayra.otabek.duvduvgap.data.models.CategoryData
import uz.yayra.otabek.duvduvgap.ui.theme.BlackPrimary
import uz.yayra.otabek.duvduvgap.ui.theme.GrayDarker
import uz.yayra.otabek.duvduvgap.ui.theme.GrayLighter
import uz.yayra.otabek.duvduvgap.ui.theme.GrayPrimary

object CategoriesTab : Tab {
    private fun readResolve(): Any = CategoriesTab

    override val options: TabOptions
        @Composable get() {
            val title = "Categories"

            return remember {
                TabOptions(
                    index = 0u, title = title
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: CategoriesContracts.ViewModel = getViewModel<CategoriesViewModel>()
        val uiState = viewModel.collectAsState()
        CategoriesScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@Composable
fun CategoriesScreenContent(uiState: State<CategoriesContracts.UiState>, onEventDispatcher: (CategoriesContracts.Intent) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        item(span = { GridItemSpan(2) }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            ) {
                Text(
                    text = "Categories",
                    color = BlackPrimary,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 16.dp, top = 20.dp),
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold))
                )
                Text(
                    text = "Thousands of articles in each category",
                    color = GrayPrimary,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular))
                )
            }
        }
        itemsIndexed(uiState.value.categories) { index, item ->
            Categories(item)
        }
    }
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White)
    systemUiController.setNavigationBarColor(Color.White)
}

@Composable
fun Categories(data: CategoryData) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .clip(shape = RoundedCornerShape(20.dp))
        .clickable {

        }
        .border(width = 1.dp, color = GrayLighter, shape = RoundedCornerShape(20.dp))
        .padding(vertical = 24.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = data.image), contentDescription = null)
        Text(text = data.name, color = GrayDarker)
    }
}