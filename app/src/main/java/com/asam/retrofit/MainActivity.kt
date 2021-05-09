package com.asam.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.asam.retrofit.adapters.NewsAdapter
import com.asam.retrofit.api.NewsServices
import com.asam.retrofit.models.Article
import com.asam.retrofit.models.NewsResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    var news: List<Article> = ArrayList<Article>()

    val API_KEY: String = "4ddbb8c5b48742bbab1bafc240258067";
    val baseUrl = "https://newsapi.org/v2/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

        val api = retrofit.create(NewsServices::class.java)
        api.getNews("us", API_KEY).enqueue(object : Callback<NewsResponse>{
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                news = response.body()!!.articles
                if(response.isSuccessful){
                    Log.w("Response", "${response.body()!!.articles}")
                    hideProgressBar()
                    showData(news)
                } else {
                    showProgressBar()
                }
            }

        })

    }

    private fun showData(news: List<Article>){
        recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            adapter = NewsAdapter(news)
        }
    }

    private fun hideProgressBar(){
        progressB.visibility = View.INVISIBLE
    }
    private fun showProgressBar(){
        progressB.visibility = View.VISIBLE
    }

}