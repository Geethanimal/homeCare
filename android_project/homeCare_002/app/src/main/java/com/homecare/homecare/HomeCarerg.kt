package com.homecare.homecare

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.net.PasswordAuthentication
import java.sql.DatabaseMetaData
import java.text.SimpleDateFormat
import java.util.*

class HomeCarerg : AppCompatActivity() {

    //Defining widgets
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var mobileNo: EditText
    private lateinit var nic: EditText
    private lateinit var dob: EditText
    private lateinit var gender: Spinner
    private lateinit var district: Spinner
    private lateinit var pw1: TextInputEditText
    private lateinit var pw2: TextInputEditText
    private lateinit var btn_reg: Button
    private lateinit var btn_datepicker: Button

    //Firebase database reference
    private lateinit var dref: DatabaseReference

    //Defining Firebase auth instance
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_carerg)

        //Assigning widgets
        firstName = findViewById(R.id.editTextfirstName)
        lastName = findViewById(R.id.editTextLastname)
        email = findViewById(R.id.editTextEmailAddress)
        mobileNo = findViewById(R.id.editTextPhoneno)
        nic = findViewById(R.id.editTextNic)
        dob = findViewById(R.id.editTextDob)
        gender = findViewById(R.id.spinnerGender)
        district = findViewById(R.id.spinnerDistrict)
        pw1 =  findViewById(R.id.editTextpassowrd)
        pw2 = findViewById(R.id.editTextCpassword)
        btn_reg = findViewById(R.id.btn_register)
        btn_datepicker = findViewById(R.id.btn_datepicker)

        // Initialize Firebase Auth
        auth = Firebase.auth

        //assigning calender into new instance
        var myCalender = Calendar.getInstance()

        //defining date picker instance
        val datePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
            myCalender.set(Calendar.YEAR, year)
            myCalender.set(Calendar.MONTH, month)
            myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updatelabel(myCalender)
        }

        btn_datepicker.setOnClickListener{
            DatePickerDialog(this,datePicker,myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH),myCalender.get(Calendar.DAY_OF_MONTH)).show()
        }

        //Defining Firebase database reference
        dref = FirebaseDatabase.getInstance("https://homecare-29a72-default-rtdb.firebaseio.com/").getReference("HomeCare")

        //registering user
        btn_reg.setOnClickListener{
            saveEmployeeData()
        }

        //starting HomeCarelg activity
        val btn_lgin = findViewById<Button>(R.id.btn_login)
        btn_lgin.setOnClickListener{
            val intent = Intent(this,HomeCarelg::class.java)
            startActivity(intent)
        }
    }

    //method for assigning date picker value into label
    private fun updatelabel(myCalender: Calendar) {
        val myFormat = "dd-mm-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        dob.setText(sdf.format(myCalender.time))


    }

    //method for registering ne user
    private fun saveEmployeeData(){
        //getting values
        val firstName_t = firstName.text.toString()
        val lastName_t = lastName.text.toString()
        val userName = firstName_t +" "+ lastName_t
        val emailAddress = email.text.toString()
        val contactNumber = mobileNo.text.toString()
        val nicNumber = nic.text.toString()
        val password1 = pw1.text.toString()
        val password2 =  pw2.text.toString()

        //textbox validation
        if(firstName_t.isEmpty()){
            firstName.error = "Please enter First Name"
        } else if(lastName_t.isEmpty()){
            lastName.error = "Please enter Last Name"
        } else if(emailAddress.isEmpty()){
            email.error = "Please enter Email"
        } else if(contactNumber.isEmpty()){
            mobileNo.error = "Please enter mobile number"
        } else if(nicNumber.isEmpty()){
            nic.error = "Please enter National Identity number"
        }else if(password1.isEmpty()){
            pw1.error = "Please enter your Password here"
        } else if(password2.isEmpty()){
            pw2.error = "Please enter your Password here"
        }else if(password1!=password2){
            pw1.error = "Your password not matched"
            pw2.error = "Your password not matched"
        } else{
            //Create user account using firebase Custom(Email and password Authentication)
            auth.createUserWithEmailAndPassword(emailAddress, password1)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        //Wtrite in database
                        val caretakerID = user?.uid.toString()

                        //pass data to model
                        val caretaker = HomeCarectuserModel(userName,emailAddress,contactNumber,nicNumber,password1)

                        //pass data to firebase realtime database
                        dref.child("userCARETAKER").child(caretakerID).setValue(caretaker)
                            .addOnSuccessListener {
                                Toast.makeText(this,"Registered Successfully", Toast.LENGTH_LONG).show()
                                openlg()

                            }.addOnFailureListener{ err->
                                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                            }
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        }

    }
    private fun openlg(){
        val intent = Intent(this,HomeCarelg::class.java)
        startActivity(intent)
    }
}