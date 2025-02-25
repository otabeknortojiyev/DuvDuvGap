package uz.yayra.otabek.duvduvgap.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import uz.yayra.otabek.duvduvgap.R
import uz.yayra.otabek.duvduvgap.screens.tabs.bookmarks.BookmarksTab
import uz.yayra.otabek.duvduvgap.screens.tabs.categories.CategoriesTab
import uz.yayra.otabek.duvduvgap.screens.tabs.home.HomeTab
import uz.yayra.otabek.duvduvgap.screens.tabs.profile.ProfileTab

object MainScreen : Screen {
    private fun readResolve(): Any = MainScreen

    @Composable
    override fun Content() {
        TabNavigator(tab = HomeTab) {
            Scaffold(content = {
                CurrentTab();
                val p = it
            }, bottomBar = {
                CustomBottomAppBar(
                    actions = {
                        TabNavigationItem(tab = HomeTab)
                        TabNavigationItem(tab = CategoriesTab)
                        TabNavigationItem(tab = BookmarksTab)
                        TabNavigationItem(tab = ProfileTab)
                    }, modifier = Modifier.clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                )
            })
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    if (tab == tabNavigator.current) {
        Column(modifier = Modifier
            .weight(1f)
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable { tabNavigator.current = tab }
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = when (tab) {
                    HomeTab -> {
                        painterResource(id = R.drawable.home_blue)
                    }

                    CategoriesTab -> {
                        painterResource(id = R.drawable.categories_blue)
                    }

                    BookmarksTab -> {
                        painterResource(id = R.drawable.bookmarks_blue)
                    }

                    else -> {
                        painterResource(id = R.drawable.profile_blue)
                    }
                },
                contentDescription = tab.options.title,
            )
        }
    } else {
        Column(
            modifier = Modifier
                .weight(1f)
                .clip(shape = RoundedCornerShape(10.dp))
                .clickable { tabNavigator.current = tab }
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = when (tab) {
                    HomeTab -> {
                        painterResource(id = R.drawable.home_gray)
                    }

                    CategoriesTab -> {
                        painterResource(id = R.drawable.categories_gray)
                    }

                    BookmarksTab -> {
                        painterResource(id = R.drawable.bookmarks_gray)
                    }

                    else -> {
                        painterResource(id = R.drawable.profile_gray)
                    }
                },
                contentDescription = tab.options.title,
            )
        }
    }
}


@Composable
fun CustomBottomAppBar(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Transparent,
    actions: @Composable RowScope.() -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .background(containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            content = actions
        )
    }
}