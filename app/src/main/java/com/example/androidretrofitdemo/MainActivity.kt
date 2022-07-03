package com.example.androidretrofitdemo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidretrofitdemo.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.tv_response)

        // init
        val repository = Repository()
        val mainViewModelFactory = MainViewModelFactory(repository)
        mainViewModel =
            ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        mainViewModel.getPost()

        //start to observe
        mainViewModel.myResponse.observe(this, Observer{ response ->

            // код выполнится при изменении обозреваемых данных

            if (response.isSuccessful) {

                Log.d("Response", response.body()?.id.toString())
                Log.d("Response", response.body()?.userId.toString())
                Log.d("Response", response.body()?.title!!)
                Log.d("Response", response.body()?.body!!)

                textView.text = response.body()?.title!!

            } else {

                Log.d("Response", response.errorBody().toString())

                textView.text = response.code().toString()

            }

        })

    }


}
