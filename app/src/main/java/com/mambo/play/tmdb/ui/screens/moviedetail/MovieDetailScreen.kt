package com.mambo.play.tmdb.ui.screens.moviedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mambo.play.tmdb.domain.models.DataResult
import com.mambo.play.tmdb.domain.models.MovieDomain
import com.mambo.play.tmdb.ui.theme.padding
import com.mambo.play.tmdb.ui.theme.paddingMedium

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 1:24 pm
 */
class MovieDetailScreen(private val movieDomain: MovieDomain) : AndroidScreen() {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: MovieDetailScreenModel = getScreenModel()
        viewModel.updateMovie(movieDomain = movieDomain)

        val state by viewModel.state.collectAsState()
        MovieDetailScreenContent(state = state, onBackClicked = { navigator.pop() })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreenContent(state: MovieDetailScreenUiState, onBackClicked: () -> Unit) {
    Scaffold(topBar = {
        LargeTopAppBar(title = { Text(text = state.title.ifBlank { "Movie" }) },
            navigationIcon = {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "navigate back"
                    )
                }
            })
    }) {
        Column(modifier = Modifier.padding(it)) {
            when (state.dataResult) {
                DataResult.Loading -> {}
                is DataResult.Success -> {
                    val movie = state.dataResult.data
                    Column {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://image.tmdb.org/t/p/original${movie.backdrop}")
                                .crossfade(true).build(),
                            contentDescription = "movie image",
                            modifier = Modifier.fillMaxWidth()
                        )

                        Column(modifier = Modifier.paddingMedium()) {
                            Text(
                                modifier = Modifier.padding(top = MaterialTheme.padding.medium),
                                text = "Overview",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = movie.overview)
                        }
                    }
                }

                else -> {}
            }
        }
    }
}