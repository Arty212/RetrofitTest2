package com.example.retrofittest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter<Meteo>(
            applicationContext,
            android.R.layout.simple_list_item_1
        )

        list.adapter = adapter

        val retrofit = Retrofit.Builder()
            .baseUrl("http://icomms.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(APIService::class.java)

        val call = apiService.getMeteo(34)

        call.enqueue(object : Callback<List<Meteo>> {

            override fun onFailure(call: Call<List<Meteo>>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Что-то пошло не так",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(call: Call<List<Meteo>>, response: Response<List<Meteo>>) {
                adapter.addAll(response.body())
                adapter.notifyDataSetChanged()
            }

        })

    }
}
