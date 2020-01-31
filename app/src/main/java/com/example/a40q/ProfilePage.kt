package com.example.a40q

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.profile.*


class ProfilePage: AppCompatActivity() {

    private val db: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        signout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        passwordChange.setOnClickListener {
            startActivity(Intent(this, NewPass::class.java))
        }



        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val saxeli = dataSnapshot.child("Users").child("username").value.toString()
                val asaki = dataSnapshot.child("Users").child("age").value.toString()
                val nomeri = dataSnapshot.child("Users").child("number").value.toString()
                val meili = dataSnapshot.child("Users").child("email").value.toString()

                name.setText(saxeli)
                age.setText(asaki)
                phone.setText(nomeri)
                mail.setText(meili)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w( "loadPost:onCancelled", databaseError.toException())
                // ...
            }


        })

    }
}