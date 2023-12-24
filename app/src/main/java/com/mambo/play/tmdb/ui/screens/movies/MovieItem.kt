package com.mambo.play.tmdb.ui.screens.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mambo.play.tmdb.domain.models.MovieDomain

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 12:45 pm
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(item: MovieDomain, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                text = item.title,
                color = Color.Black,
                maxLines = 1
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/original${item.image}")
                    .crossfade(true)
                    .build(),
                contentDescription = "movie image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().height(400.dp)
            )
        }

        Row(
            modifier = Modifier
                .background(Color.White)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                text = item.overview,
                color = Color.Black,
                maxLines = 3
            )
        }
    }
}