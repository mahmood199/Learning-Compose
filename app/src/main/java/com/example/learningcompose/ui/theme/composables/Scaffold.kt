package com.example.learningcompose.ui.theme.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.learningcompose.ui.theme.LearningComposeTheme
import kotlinx.coroutines.launch

@Composable
fun LearningScaffold(
    modifier: Modifier = Modifier
) {
    val scaffoldState = rememberScaffoldState()
    val topBarState = remember { mutableStateOf(true) }
    val bottomBarState = remember { mutableStateOf(true) }
    val fabState = remember { mutableStateOf(true) }

    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        content = { padding ->
            Content(
                padding,
                topBarState,
                bottomBarState,
                fabState
            )
        },
        topBar = { TopBar(scaffoldState, topBarState) },
        bottomBar = { BottomBar(bottomBarState) },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            if (fabState.value) {
                FloatingActionButton(scaffoldState)
            }
        },
        drawerContent = { DrawerContent() },
    )
}

@Composable
fun Content(
    padding: PaddingValues,
    topBarState: MutableState<Boolean>,
    bottomBarState: MutableState<Boolean>,
    fabState: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {

}

@Composable
fun TopBar(
    scaffoldState: ScaffoldState,
    state: MutableState<Boolean>
) {
    val coroutineScope = rememberCoroutineScope()
    AnimatedVisibility(
        visible = state.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            TopAppBar(
                title = { Text("Top Bar") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    )
}

@Composable
fun BottomBar(state: MutableState<Boolean>) {
    AnimatedVisibility(
        visible = state.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomAppBar { Text("Bottom Bar") }
        }
    )
}

@Composable
fun FloatingActionButton(scaffoldState: ScaffoldState) {
    val coroutineScope = rememberCoroutineScope()
    FloatingActionButton(
        onClick = {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar("FAB Action Button Clicked")
            }
        }
    ) {
        Text("FAB")
    }
}

@Composable
fun DrawerContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Drawer Content")
    }
}

@Preview
@Composable
fun LearningScaffoldPreview() {
    LearningComposeTheme {
        LearningScaffold()
    }
}