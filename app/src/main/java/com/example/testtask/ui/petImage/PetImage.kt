package com.example.testtask.ui.petImage

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.testtask.model.PetPhoto
import com.example.testtask.ui.main.MainViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun PetImage(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController
){
    val scrollState = rememberScrollState()

    val isRefreshing = viewModel.isRefreshing.collectAsState().value
    val items = viewModel.items.collectAsState().value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = viewModel::refresh,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState, enabled = true)
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { navController.navigate("MainScreen") }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
            LazyColumn {
                items(items) {
                    OneImage(
                        petPhoto = it,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun OneImage(
    petPhoto: StateFlow<PetPhoto?>,
    viewModel: MainViewModel
){
    var scale by remember {
        viewModel.scale
    }

    val state = rememberTransformableState { zoomChange, _, _ ->
        scale *= zoomChange
    }

    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = petPhoto.value?.full ?: "").apply(block = fun ImageRequest.Builder.() {
                // TODO
            }).build()
        ),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            )
            .transformable(state = state)
    )
}