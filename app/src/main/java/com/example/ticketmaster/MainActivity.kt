package com.example.ticketmaster

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MainActivity"
val API_KEY = "5XwI0TRHSPM0s5Iu5bbB0HuMcYgSmoX7"
var keyword = "sports"
var location = "hartford"
var eventList = ArrayList<Event>()
var init_array = arrayOf<String>()
var ticket_links = init_array.toMutableList()
val eventDataCount = 0


class MainActivity : AppCompatActivity() {

    private lateinit var firestoreDb: FirebaseFirestore

    private lateinit var fireBaseDb: FirebaseFirestore

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var venue: TextView
    private lateinit var address: TextView
    private lateinit var dateTime: TextView
    private lateinit var ticket: TextView
    private lateinit var price: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firestore


    }


    fun hideKeyboard(view: View) {
        val inputMethodManager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun searchEvents(view: View) {

        val noResults = findViewById<TextView>(R.id.no_results)
        if (noResults.visibility == View.VISIBLE) {
            noResults.visibility = View.INVISIBLE
        }

        eventList.clear()
        ticket_links.clear()

        val adapter = EventsAdapter(
            this,
            eventList
        )
        val searchResults = findViewById<RecyclerView>(R.id.Search_Results)
        searchResults.adapter = adapter
        searchResults.layoutManager = LinearLayoutManager(this)

        val keyword: Editable = findViewById<EditText>(R.id.Event_Or_Keyword).text
        val location: Editable = findViewById<EditText>(R.id.Location).text

        if (keyword.isEmpty() || location.isEmpty()) {
            var title = ""
            var message = ""
            if (keyword.isEmpty() && !location.isEmpty()) {
                title = "❌ Search Term Missing"
                message = "Search Term cannot be empty. Please enter a Search Term and Location"
            }
            if (!keyword.isEmpty() && location.isEmpty()) {
                title = "❌ Location Missing"
                message = "Location cannot be empty. Please enter a Search Term and Location"
            }
            if (keyword.isEmpty() && location.isEmpty()) {
                title = "❌ Search Term and Location Missing"
                message = "Fields cannot be empty. Please enter a Search Term and Location"
            }
            val builder = AlertDialog.Builder(this)
            builder.setTitle(title)
            builder.setMessage(message)
                .setPositiveButton("Okay.") { _, id -> }
            val dialog = builder.create()
            dialog.show()
            return
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://app.ticketmaster.com") // Set the base URL for the REST API
            .addConverterFactory(GsonConverterFactory.create()) // Add Gson converter factory for JSON serialization/deserialization
            .build() // Build the Retrofit instance
        val eventAPI = retrofit.create(EventService::class.java)
        eventAPI.getEvents(API_KEY, keyword, location).enqueue(object : Callback<EventData> {
            override fun onResponse(call: Call<EventData>, response: Response<EventData>) {
                //Log.d(TAG, "onResponse: $response")

                val body = response.body()
                //println(body)

                if (body == null) {
                    // Log.w(TAG, "Valid response was not received")
                    return
                }
                if (body.page.totalElements > 0) {
                    body._embedded.events.forEach() { element ->
                        eventList.add(element)
                        ticket_links.add(element.url)
                    }
                    println(ticket_links)
                    adapter.notifyDataSetChanged()
                } else {
                    noResults.visibility = View.VISIBLE
                }

            }

            override fun onFailure(call: Call<EventData>, t: Throwable) {
                //Log.d(TAG, "onFailure : $t")
            }
        })

    }


}
