package com.example.date

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var tvDatePicker : TextView
    private lateinit var btnDatePicker : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDatePicker = findViewById(R.id.tvDate)
        btnDatePicker = findViewById(R.id.btnDatePicker)

        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

            val year = 0
            myCalendar.set(Calendar.YEAR, year)
            val month = 0
            myCalendar.set(Calendar.MONTH, month)
            val dayofmonth = 0
            myCalendar.set(Calendar.DAY_OF_MONTH, dayofmonth)
            updateLable(myCalendar)
        }

        btnDatePicker.setOnClickListener {
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }


    }

    private fun updateLable(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        tvDatePicker.setText(sdf.format(myCalendar.time))

    }
}