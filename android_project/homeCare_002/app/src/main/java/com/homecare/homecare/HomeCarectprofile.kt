package com.homecare.homecare

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeCarectprofile : AppCompatActivity() {
    //defining widgets
    private lateinit var ctprofiletextview : TextView
    private lateinit var ctprofile_username: TextInputEditText
    private lateinit var ctprofile_contactno: TextInputEditText
    private lateinit var ctprofile_nicno: TextInputEditText
    private lateinit var ctprofile_dob: TextInputEditText
    private lateinit var ctprofile_gender: Spinner
    private lateinit var ctprofile_district: Spinner
    private lateinit var ctprofile_tos: Spinner
    private lateinit var ctprofile_description: EditText

    private lateinit var userName: String
    private lateinit var contactNumber: String
    private lateinit var nicNumber: String
    private lateinit var dob: String
    private lateinit var gender: String
    private lateinit var district: String
    private lateinit var tos: String
    private lateinit var description: String

    /*//Defining Firebase auth instance
    private lateinit var auth: FirebaseAuth*/

    //Defining Firebase realtime database instance
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_carectprofile)

        //Assigning widgets
        ctprofiletextview = findViewById(R.id.ctprofiletextview)
        ctprofile_username = findViewById(R.id.ctprofile_username)
        ctprofile_contactno = findViewById(R.id.ctprofile_contactno)
        ctprofile_nicno = findViewById(R.id.ctprofile_nicno)
        ctprofile_dob = findViewById(R.id.ctprofile_dob)
        ctprofile_gender =  findViewById(R.id.ctprofile_gender)
        ctprofile_district = findViewById(R.id.ctprofile_district)
        ctprofile_tos = findViewById(R.id.ctprofile_tos)
        ctprofile_description = findViewById(R.id.ctprofile_description)

        //Assigning database reference to Firebase realtime database reference instance
        database = FirebaseDatabase.getInstance().getReference("HomeCare")

        //Assigning user id to uid
        val uid = Firebase.auth.currentUser?.uid.toString()

        database.child("userCARETAKER").child(uid).get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")

            val state_published = it.child("published").value.toString()
            userName = it.child("userName").value.toString()
            contactNumber = it.child("contactNumber").value.toString()
            nicNumber = it.child("nicNumber").value.toString()
            dob = it.child("dateofbirth").value.toString()
            gender = it.child("gender").value.toString()
            district = it.child("district").value.toString()
            tos = it.child("typeofservice").value.toString()
            description = it.child("description").value.toString()

            if(description.equals("null")){
                ctprofile_description.setText("")
            }else {
                ctprofile_description.setText(description)
            }
            if(userName.equals("null")){
                ctprofile_username.setText("")
            }else {
                ctprofile_username.setText(userName)
            }
            if(contactNumber.equals("null")){
                ctprofile_contactno.setText("")
            }else {
                ctprofile_contactno.setText(contactNumber)
            }
            if(nicNumber.equals("null")){
                ctprofile_nicno.setText("")
            }else {
                ctprofile_nicno.setText(nicNumber)
            }
            if(dob.equals("null")){
                ctprofile_dob.setText("")
            }else {
                ctprofile_dob.setText(dob)
            }

            setctprofile_gendervalue(gender)
            setctprofile_tosvalue(tos)
            setctprofile_districtvalue(district)

            if(state_published.equals("0")){

                ctprofiletextview.text = "Update your profile to publish"

            }else if(state_published.equals("1")){

                ctprofiletextview.text = "Your profile was published. You can update profile here"

            }

        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }

    private fun setctprofile_gendervalue(gender:String){
        when (gender) {
            "Male" -> ctprofile_gender.setSelection(1)
            "Female" -> ctprofile_gender.setSelection(2)
            else -> {
                ctprofile_gender.setSelection(0)
            }
        }
    }
    private fun setctprofile_tosvalue(tos:String){
        when (tos) {
            "Doctor" -> ctprofile_tos.setSelection(1)
            "Physio" -> ctprofile_tos.setSelection(2)
            "Nurse" -> ctprofile_tos.setSelection(3)
            "Care Taker" -> ctprofile_tos.setSelection(4)
            "Elder Care" -> ctprofile_tos.setSelection(5)
            "Post Natal Care" -> ctprofile_tos.setSelection(6)
            else -> {
                ctprofile_tos.setSelection(0)
            }
        }
    }
    private fun setctprofile_districtvalue(district:String){
        when (district) {
            "Colombo" -> ctprofile_district.setSelection(1)
            "Gampaha" -> ctprofile_district.setSelection(2)
            "Kalutara" -> ctprofile_district.setSelection(3)
            "Kandy" -> ctprofile_district.setSelection(4)
            "Matale" -> ctprofile_district.setSelection(5)
            "Nuwara Eliya" -> ctprofile_district.setSelection(6)
            "Galle" -> ctprofile_district.setSelection(7)
            "Matara" -> ctprofile_district.setSelection(8)
            "Hambantota" -> ctprofile_district.setSelection(9)
            "Jaffna" -> ctprofile_district.setSelection(10)
            "Kilinochchi" -> ctprofile_district.setSelection(11)
            "Mannar" -> ctprofile_district.setSelection(12)
            "Vavuniya" -> ctprofile_district.setSelection(13)
            "Mullaitivu" -> ctprofile_district.setSelection(14)
            "Batticaloa" -> ctprofile_district.setSelection(15)
            "Ampara" -> ctprofile_district.setSelection(16)
            "Trincomalee" -> ctprofile_district.setSelection(17)
            "Kurunegala" -> ctprofile_district.setSelection(18)
            "Puttalam" -> ctprofile_district.setSelection(19)
            "Anuradhapura" -> ctprofile_district.setSelection(20)
            "Polonnaruwa" -> ctprofile_district.setSelection(21)
            "Badulla" -> ctprofile_district.setSelection(22)
            "Moneragala" -> ctprofile_district.setSelection(23)
            "Ratnapura" -> ctprofile_district.setSelection(24)
            "Kegalle" -> ctprofile_district.setSelection(25)
            else -> {
                ctprofile_tos.setSelection(0)
            }
        }
    }
}