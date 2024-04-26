package com.example.ticketmaster

import android.R
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.ai.client.generativeai.GenerativeModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class EventsAdapter(context: Context, private val events: ArrayList<Event>) :
    RecyclerView.Adapter<EventsAdapter.MyViewHolder>() {
    var count = 0
    private lateinit var firestoreDb: FirebaseFirestore


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.event_name)
        val image = itemView.findViewById<ImageView>(R.id.event_image)
        val venue = itemView.findViewById<TextView>(R.id.event_venue)
        val address = itemView.findViewById<TextView>(R.id.event_address)
        val dateTime = itemView.findViewById<TextView>(R.id.event_date_time)
        val price = itemView.findViewById<TextView>(R.id.event_price)
        val ticket = itemView.findViewById<Button>(R.id.event_tickets)
        val event: CardView = itemView.findViewById(R.id.event_row)
        val tags = itemView.findViewById<TextView>(R.id.event_gemini_tags)
        val saveEvent = itemView.findViewById<Button>(R.id.save_event)


    }

    fun convertDate(dateString: String): String {
        if (dateString.isEmpty()) {
            return "[TBD]"
        }
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")

        val date = LocalDate.parse(dateString, inputFormatter)
        return date.format(outputFormatter)
    }

    fun convertTime(timeString: String): String {

        System.out.println(timeString)

        val inputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val outputFormatter = DateTimeFormatter.ofPattern("h:mma")
        if (timeString.isNullOrEmpty()) {
            val time = "[TBD]"
            return time
        } else {
            val time = LocalTime.parse(timeString, inputFormatter)
            return time.format(outputFormatter)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate a layout from our XML (row_item.XML) and return the holder
        // create a new view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_event, parent, false)
        firestoreDb = FirebaseFirestore.getInstance()
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val currentItem = events[position]
        val highestQualityImage = currentItem.images.maxByOrNull {
            it.width.toInt() * it.height.toInt()
        }
        val context = holder.itemView.context
        println(highestQualityImage)
        Glide.with(context)
            .load("${currentItem.images[0].url}")
            .placeholder(R.drawable.ic_launcher_foreground) // In case the image is not loaded show this placeholder image
            //.override(currentItem.images[0].width, currentItem.images[0].height)
            .into(holder.image)

        holder.name.text = "${currentItem.name}"
        holder.venue.text =
            "${currentItem._embedded.venues[0].name}, ${currentItem._embedded.venues[0].city.name}"
        holder.address.text =
            "Address: ${currentItem._embedded.venues[0].address.line1}, ${currentItem._embedded.venues[0].city.name}, ${currentItem._embedded.venues[0].state.name} "
        holder.dateTime.text =
            "Date: " + convertDate("${currentItem.dates.start.localDate}") + " @ " + convertTime("${currentItem.dates.start.localTime}")
        holder.ticket.tag = "${currentItem.url}"

        holder.ticket.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(currentItem.url))
            it.context.startActivity(intent)

        }

        if (currentItem.priceRanges.isNullOrEmpty()) {
            holder.price.text = ""
        } else {
            holder.price.text =
                "Price Range: $${currentItem.priceRanges[0].min} - $${currentItem.priceRanges[0].max}"
        }

        val generativeModel = GenerativeModel(
            // For text-only input, use the gemini-pro model
            modelName = "gemini-pro",
            // Access your API key as a Build Configuration variable (see "Set up your API key" above)
            apiKey = BuildConfig.apiKey
        )
        val prompt =
            "Give me a 200 character description about the event using the following information: Event Title: ${currentItem.name}, Venue: ${currentItem._embedded.venues[0].name}, City: ${currentItem._embedded.venues[0].city.name}"
        MainScope().launch {
            val response = generativeModel.generateContent(prompt)
            holder.tags.text = (response.text)
        }


    }

    override fun getItemCount(): Int {
        // Return the size of your dataset (invoked by the layout manager)
        return events.size
    }

}
