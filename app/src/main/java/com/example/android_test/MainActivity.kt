package com.example.android_test

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

/*
The MainActivity represents a single screen on the App.  This entire program will be a single
screen app.  AppCompatActivity is an Android SDK class that represents an Activity (or screen).

When Android wants to display this screen, it will run the code in the onCreate function.
 */

class MainActivity : AppCompatActivity() {

    private lateinit var date_input: EditText
    private lateinit var date_input2: EditText
    private lateinit var weight_output: TextView


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Associate the layout XML with this activity so Android knows what to draw
        setContentView(R.layout.activity_main)

        // Get references to the button objects in the layout XML
        // The R.id.<name> is based on the id names in the layout XML
        val to_date_button = findViewById<Button>(R.id.to_date)
        val custom_button = findViewById<Button>(R.id.custom)
        val restart_button = findViewById<Button>(R.id.restart)

        date_input = findViewById<EditText>(R.id.date_input)
        date_input2 = findViewById<EditText>(R.id.date_input2)
        weight_output = findViewById<TextView>(R.id.weight_out)

        // Define which functions to run if a button is pressed
        to_date_button.setOnClickListener { toDateWeight() }
        custom_button.setOnClickListener { customWeight() }
        restart_button.setOnClickListener { reset() }


    }

    @SuppressLint("SimpleDateFormat")
    private fun format(input: java.util.Date): String {

        // initialize given date
        // date = input
        // Create formatter to format down to yyyy-mm-dd
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        // Format current date using formatter above
        val formattedDate = formatter.format(input)

        return formattedDate.format(this)


    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun stringToDate(date: String): LocalDate {

        // Create a converter for future use.
        var new = date.toString()
        var newDate = LocalDate.parse(new, DateTimeFormatter.ISO_DATE)
        return newDate
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun editTextToDate(date: Editable): LocalDate {

        // Create a converter for future use.
        var new = date.toString()
        var newDate = LocalDate.parse(new, DateTimeFormatter.ISO_DATE)
        return newDate
    }


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun toDateWeight() {
        try {
            // stringToDate needs applied for calculations to work.
            val date1 = stringToDate(date_input.text.toString())
            val date2 = stringToDate(format(Calendar.getInstance().time))

            // Preform the "math" to find the difference between the two days.
            val difference = Duration.between(date1.atStartOfDay(), date2.atStartOfDay()).toDays()
            val weight = kotlin.math.abs(difference) * 2
            weight_output.text = "$weight lbs"
        } catch (e: Exception) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_LONG).show()

        }


    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun customWeight() {
        try {
            // stringToDate needs applied for calculations to work.
            val date1 = stringToDate(date_input.text.toString())
            val date2 = stringToDate(date_input2.text.toString())

            // Preform the "math" to find the difference between the two days.
            val difference = Duration.between(date1.atStartOfDay(), date2.atStartOfDay()).toDays()
            val weight = kotlin.math.abs(difference) * 2
            weight_output.text = "$weight lbs"
        } catch (e: Exception) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_LONG).show()

        }

    }

    private fun reset() {
        // Clear out the input/output fields
        date_input.text.clear()
        date_input2.text.clear()
        weight_output.text = ""
    }

}