package com.example.androidretrofitdemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidretrofitdemo.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var etEnterReqId: TextView
    private lateinit var btnSendReq: Button
    private lateinit var tvResponse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    
    private fun init() {

        // init views
        etEnterReqId = findViewById(R.id.et_enter_req_id)
        btnSendReq = findViewById(R.id.btn_send_req)
        tvResponse = findViewById(R.id.tv_response)

        // init ViewModel
        val repository = Repository()
        val mainViewModelFactory = MainViewModelFactory(repository)
        mainViewModel =
            ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        // start to observe ViewModel LiveData
        mainViewModel.responsePostsByUserId.observe(this, Observer { response ->
            // код выполнится при изменении обозреваемых данных
            if (response.isSuccessful) {
                tvResponse.text = response.body().toString()
            } else {
                tvResponse.text = response.code().toString()
            }
        })

        //set onClickListener to button to send request
        btnSendReq.setOnClickListener {

            if (etEnterReqId.text.toString() == "") {
                Toast.makeText(applicationContext, "Please enter ID", Toast.LENGTH_SHORT).show()
            } else {
                mainViewModel.getPostsByUserId(
                    userId = Integer.parseInt(etEnterReqId.text.toString()),
                    sort = "id",
                    order = "desc"
                )
            }

        }

    }

}
