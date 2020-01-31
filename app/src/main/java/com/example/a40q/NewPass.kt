package com.example.a40q

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.newpass.*

class NewPass : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newpass)
        auth = FirebaseAuth.getInstance()

        changepass.setOnClickListener {
            ChangePassword()
        }
    }
    private fun ChangePassword() {
        if(currpass.text.toString().isNotEmpty() && newpass.text.toString().isNotEmpty() && confirm.text.toString().isNotEmpty()) {
            if(newpass.text.toString().equals(confirm.text.toString())) {
                val user = auth.currentUser
                if(user != null && user.email != null) {
                        val credential = EmailAuthProvider.getCredential(user.email!!, currpass.text.toString())
                    user?.reauthenticate(credential)?.addOnCompleteListener{task ->
                        if(task.isSuccessful){
                            Toast.makeText(baseContext, "Re-Authentication is Successful", Toast.LENGTH_LONG).show()
                            user?.updatePassword(newpass.text.toString()).addOnCompleteListener { it ->
                                if(it.isSuccessful){
                                    Toast.makeText(baseContext, "Password Changed Succesfully", Toast.LENGTH_LONG).show()
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finish()
                                }
                            }
                        }else{
                            Toast.makeText(baseContext, "Re-Authentication Is Not Successful", Toast.LENGTH_LONG).show()
                        }
                    }
                }else{
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }else{
                Toast.makeText(baseContext, "Password Does Not Match", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(baseContext, "Fill All Fields", Toast.LENGTH_LONG).show()
        }
    }
}