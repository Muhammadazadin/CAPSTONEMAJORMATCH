package com.example.register.kampus

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.register.R
import com.example.register.SpaceItemDecoration
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class UniversitasActivity : AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var universityRecyclerView: RecyclerView

    private lateinit var universityAdapter: UniversityAdapter
    private val universities: MutableList<University> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_universitas)

        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)
        universityRecyclerView = findViewById(R.id.universityRecyclerView)

        universityAdapter = UniversityAdapter(universities)
        universityRecyclerView.layoutManager = LinearLayoutManager(this)
        universityRecyclerView.adapter = universityAdapter

        val verticalSpaceHeightRes = R.dimen.item_vertical_spacing // Ganti dengan resource ID tinggi jarak yang Anda inginkan
        val itemDecoration = SpaceItemDecoration(this, verticalSpaceHeightRes)
        universityRecyclerView.addItemDecoration(itemDecoration)

        searchButton.setOnClickListener {
            val keyword = searchEditText.text.toString()
            if (keyword.isNotEmpty()) {
                searchUniversity(keyword)
            }
        }
    }

    private fun searchUniversity(keyword: String) {
        val client = OkHttpClient()

        val url = "https://match-major-backendv2-7qtkfzxmja-et.a.run.app/api/university/search/university?univ=$keyword"

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    // Handle network error
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                try {
                    val jsonArray = JSONArray(responseBody)
                    universities.clear()
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val university = University(
                            jsonObject.getString("university"),
                            jsonObject.getString("singkatan"),
                            jsonObject.getString("alamat")
                        )
                        universities.add(university)
                    }

                    runOnUiThread {
                        universityAdapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    runOnUiThread {
                        // Handle JSON parsing error
                    }
                }
            }
        })
    }
}
