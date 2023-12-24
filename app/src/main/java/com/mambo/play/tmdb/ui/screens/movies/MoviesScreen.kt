package com.mambo.play.tmdb.ui.screens.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getScreenModel
import com.mambo.play.tmdb.domain.models.DataResult
import com.mambo.play.tmdb.domain.models.MovieDomain

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 11:47 am
 */
object MoviesScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val screenModel: MoviesScreenModel = getScreenModel()
        val state by screenModel.state.collectAsState()
        MoviesScreenContent(
            state = state,
            onRetry = screenModel::getMovies,
            onMovieClicked = {}
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreenContent(
    state: MoviesScreenUiState,
    onRetry: () -> Unit,
    onMovieClicked: (MovieDomain) -> Unit
) {
    Scaffold(
        topBar = {
            LargeTopAppBar(title = {
                Text(
                    text = "Movies",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                )
            })
        }
    ) {
        when (state.data) {
            DataResult.Empty -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Empty", fontSize = 32.sp)
                    Text(text = "Please add something")
                }
            }

            is DataResult.Failure -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Failure", fontSize = 32.sp)
                    Text(text = state.data.message)
                    Button(modifier = Modifier.padding(top = 16.dp), onClick = onRetry) {
                        Text(text = "retry")
                    }
                }
            }

            DataResult.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            is DataResult.Success -> {
                val list = state.data.data
                LazyVerticalGrid(
                    modifier = Modifier.padding(it),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(list) { movie ->
                        MovieItem(item = movie) {
                            onMovieClicked.invoke(movie)
                        }
                    }
                }
            }
        }
    }
}