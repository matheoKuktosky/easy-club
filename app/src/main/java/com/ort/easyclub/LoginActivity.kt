package com.ort.easyclub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var textViewSignUp: TextView
    private lateinit var forgotPassword: TextView
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // busca el Button
        loginButton = findViewById(R.id.btnLogin)


        // busca los EditText
        inputEmail = findViewById(R.id.inputUsername)
        inputPassword = findViewById(R.id.inputPassword)

        forgotPassword = findViewById(R.id.forgotPassword)
        textViewSignUp = findViewById(R.id.textViewSignUp)

        progressBar = findViewById(R.id.progressBar2)
        auth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener{
            val email:String=inputEmail.text.toString()
            val password:String=inputPassword.text.toString()

            if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                progressBar.visibility= View.VISIBLE

                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
                    task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Login!", Toast.LENGTH_SHORT)
                    }
                    else{
                        Toast.makeText(this,"Error en la autenticación", Toast.LENGTH_SHORT)
                    }
                }
            }
        }

        textViewSignUp.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        forgotPassword.setOnClickListener{
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

    }
}