package com.example.fetchapi.data.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchapi.data.api.RetrofitInstance
import com.example.fetchapi.data.model.Post
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {
    private  val _post = mutableStateOf<List<Post>>(emptyList());
    val posts: State<List<Post>> = _post

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                _post.value = RetrofitInstance.api.getPosts()
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}