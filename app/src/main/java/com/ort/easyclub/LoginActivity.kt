package com.ort.easyclub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // busca el Button
        val loginButton = findViewById<Button>(R.id.btnLogin)


        // busca los EditText
        val inputUsername = findViewById<EditText>(R.id.inputUsername)
        val inputPassword = findViewById<EditText>(R.id.inputPassword)

        loginButton.setOnClickListener{
            if(inputUsername.text.isNullOrBlank()&&inputPassword.text.isNullOrBlank()){
                Toast.makeText(this,"Please fill the required fields", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "${inputUsername.text} is logged in", Toast.LENGTH_SHORT).show()
            }
        }
    }
}