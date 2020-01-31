package com.example.a40q

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.registration.*

class Registration : AppCompatActivity() {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val db: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)




        registration.setOnClickListener {
            userSignUp()

        }
        returnn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }




    }
    private fun OnDatabase() {

        val hashMap=HashMap<String,String>()
        hashMap.put("email",regEmail.text.toString())
        hashMap.put("number", number.text.toString())
        hashMap.put("age", date.text.toString())
        hashMap.put("username", date.text.toString())
        auth = FirebaseAuth.getInstance()
        db.child("Users").setValue(hashMap)

    }
    private fun userSignUp() {

        if(regEmail.text.toString().isEmpty()) {
            regEmail.error = "Please Enter Valid Email"
            regEmail.requestFocus()
            return
        }

        if(regPass.text.toString().isEmpty()) {
            regPass.error = "Please Enter Password"
            regPass.requestFocus()
            return
        }


        auth.createUserWithEmailAndPassword(regEmail.text.toString(), regPass.text.toString()).addOnCompleteListener() { it ->
            if (it.isSuccessful) {
                startActivity(Intent(this, MainActivity::class.java))
                OnDatabase()
            } else {
                Toast.makeText(baseContext, "Authentication Failed! Try Again",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}