package com.homecare.homecare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.sql.DatabaseMetaData

class HomeCarerg : AppCompatActivity() {

    //Defining widgets
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var mobileNo: EditText
    private lateinit var nic: EditText
    private lateinit var btn_reg: Button

    //Firebase database reference
    private lateinit var dref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_carerg)

        //Assigning widgets
        firstName = findViewById(R.id.editTextfirstName)
        lastName = findViewById(R.id.editTextLastname)
        email = findViewById(R.id.editTextEmailAddress)
        mobileNo = findViewById(R.id.editTextPhoneno)
        nic = findViewById(R.id.editTextNic)
        btn_reg = findViewById(R.id.btn_register)


        //Defining Firebase database reference
        dref = FirebaseDatabase.getInstance("https://homecare-29a72-default-rtdb.firebaseio.com/").getReference("HomeCare")

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
    private fun saveEmployeeData(){
        //getting values
        val firstName_t = firstName.text.toString()
        val lastName_t = lastName.text.toString()
        val userName = firstName_t +" "+ lastName_t
        val emailAddress = email.text.toString()
        val contactNumber = mobileNo.text.toString()
        val nicNumber = nic.text.toString()

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
        } else if(firstName_t.isEmpty()&&lastName_t.isEmpty()&&emailAddress.isEmpty()&&contactNumber.isEmpty()&&nicNumber.isEmpty()){
            Toast.makeText(this,"Please Fill the form to Register", Toast.LENGTH_LONG).show()
        }else{
            //Wtrite in database
            val caretakerID = dref.push().key!!

            val caretaker = HomeCarectuserModel(userName,emailAddress,contactNumber,nicNumber)

            dref.child(caretakerID).setValue(caretaker)
                .addOnCanceledListener {
                    Toast.makeText(this,"Registered Successfully", Toast.LENGTH_LONG).show()
                }.addOnFailureListener{ err->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

    }
}