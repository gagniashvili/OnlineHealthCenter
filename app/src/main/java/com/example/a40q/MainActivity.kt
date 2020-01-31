package com.example.a40q

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        login.setOnClickListener {
            userLogIn()
        }
        registrationm.setOnClickListener {
            startActivity(Intent(this, Registration::class.java))
        }
        pasup.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.test, null)
            val email = view.findViewById<EditText>(R.id.edittextid)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _  ->
                forgotPassword(email)
            })

            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _  ->  })
            builder.show()
        }
    }
    private fun forgotPassword(email : EditText) {
        if(email.text.toString().isEmpty()) {
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            return
        }
        auth.sendPasswordResetEmail(email.text.toString()).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                Toast.makeText(baseContext, "Email Sent", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun userLogIn() {
        if(email.text.toString().isEmpty()) {
            Toast.makeText(baseContext, "Fill This", Toast.LENGTH_SHORT).show()
        }else if (password.text.toString().isEmpty()) {
            Toast.makeText(baseContext, "Fill This", Toast.LENGTH_SHORT).show()
        }

        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener(this) {task ->
            if(task.isSuccessful) {
                val user = auth.currentUser
                updateUI(user)
            }else{
                Toast.makeText(baseContext, "Login Failed! Try Again", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }

        }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser != null){
            startActivity(Intent(this, Mainpage::class.java))
        }
    }


}
