package com.example.androidretrofitdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidretrofitdemo.adapter.PostAdapterForRV
import com.example.androidretrofitdemo.model.PostModel
import com.example.androidretrofitdemo.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private val postAdapterForRV by lazy { PostAdapterForRV() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        rv_posts.adapter = postAdapterForRV
        rv_posts.layoutManager = LinearLayoutManager(this)

        // init ViewModel
        val repository = Repository()
        val mainViewModelFactory = MainViewModelFactory(repository)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        // send request to viewModel to send post request and
        // start observe liveData in viewModel for response
        mainViewModel.pushPostFormUrlEncoded(PostModel(7889,4784,"eva edet v vavilon", "abrakadabra"))
        mainViewModel.responsePushPostFormUrlEncoded.observe(this, Observer { response ->

            if (response.isSuccessful) {

                val arrList = ArrayList<PostModel>().apply {
                    add(response.body()!!)
                    add(PostModel(0,0,"${response.headers()}",""))
                }

                postAdapterForRV.setList(arrList)

            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }

        })

    }

}
