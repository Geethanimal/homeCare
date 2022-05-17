package com.example.dropdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var option: Spinner
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        option = findViewById(R.id.sp_option)as Spinner
        result = findViewById(R.id.tv_result)as TextView

        var options = arrayOf("Option 1","Option 2","Option 3")

        option.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        option.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?){
                result.text="Please Select an option"
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                result.text=options.get(position)
            }
        }
    }
}