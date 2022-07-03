package com.example.androidretrofitdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidretrofitdemo.model.PostModel
import com.example.androidretrofitdemo.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val responseFirstPost: MutableLiveData<Response<PostModel>> = MutableLiveData()
    val responsePostById: MutableLiveData<Response<PostModel>> = MutableLiveData()
    val responsePostsByUserId: MutableLiveData<Response<List<PostModel>>> = MutableLiveData()

    fun getFirstPost() {
        viewModelScope.launch {
            val response = repository.getFirstPost()
            responseFirstPost.value = response
        }
    }

    fun getPostById(id: Int) {
        viewModelScope.launch {
            val response = repository.getPostById(id)
            responsePostById.value = response
        }
    }

    fun getPostsByUserId(userId: Int, sort: String, order: String) {
        viewModelScope.launch {
            val response = repository.getPostsByUserId(userId, sort, order)
            responsePostsByUserId.value = response
        }
    }

}