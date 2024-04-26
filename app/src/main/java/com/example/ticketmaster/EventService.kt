package com.example.ticketmaster

import android.text.Editable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EventService {
    // Requesting a random User info based on specified  nationality, https://randomuser.me/api/?nat=us
    // Adding the nationality parameter to your request
    // get(".") indicates that your url is the same as the base url
    @GET(".")
    fun getEventInfo(@Query("keyword") keyword: String): Call<EventData>

    // Requesting Multiple Users, https://randomuser.me/api/?results=5000
    @GET(".")
    fun getMultipleEventsInfo(@Query("results") amount: Int): Call<EventData>


    // Requesting Multiple Users, https://randomuser.me/api/?results=5000&nat=us
    @GET("/discovery/v2/events.json")
    fun getEvents(
        //@Query("size") size: Int,
        @Query("apikey") apikey: String,
        @Query("keyword") keyword: Editable,
        @Query("city") location: Editable
    ): Call<EventData>
}
