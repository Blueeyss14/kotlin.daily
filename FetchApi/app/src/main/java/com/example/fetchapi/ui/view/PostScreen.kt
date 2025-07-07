package com.example.fetchapi.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fetchapi.data.model.Post
import com.example.fetchapi.data.viewModel.PostViewModel
import com.example.fetchapi.ui.theme.PurpleGrey40


@Composable
fun PostScreen(viewModel: PostViewModel = PostViewModel(), paddingValues : PaddingValues) {

    val posts by viewModel.posts;

    if (posts.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(paddingValues)

        ) {
            items(posts) {
                PostCard(it)
            }
        }
    }

}

@Composable
fun PostCard(post: Post) {
    Card(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(Color.Blue.copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Post ${post.id}")
            Text(text = post.title)
            Text(text = post.body)
        }
    }
}