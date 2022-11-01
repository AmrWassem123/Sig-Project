package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener {

            val email = binding.emailEt.text.toString()
            val password = binding.passET.text.toString()
            val confirmpassword = binding.confirmPassEt.text.toString()
            if(email.isEmpty() || password.isEmpty()|| confirmpassword.isEmpty()) {
                Toast.makeText(baseContext, "please fill the box", Toast.LENGTH_SHORT).show()
            }
            else{



             auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->

                     if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this, SignInActivity::class.java)
                        startActivity(intent)

                    }  else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "authentication failed", Toast.LENGTH_SHORT).show()

                    }



            }

        }
    }
}}