package com.ort.easyclub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var txtUsername: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtConfirmPassword: EditText

    private lateinit var btnRegister: Button

    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        txtUsername=findViewById(R.id.inputUsername)
        txtEmail=findViewById(R.id.inputEmail)
        txtPassword=findViewById(R.id.inputPassword)
        txtConfirmPassword=findViewById(R.id.inputConfirmPassword)

        btnRegister=findViewById(R.id.btnRegister)

        progressBar = findViewById(R.id.progressBar)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        dbReference = database.reference.child("User")

        btnRegister.setOnClickListener{
            createNewAccount()
        }
    }

    private fun createNewAccount(){
        val username:String = txtUsername.text.toString()
        val email:String = txtEmail.text.toString()
        val password:String = txtPassword.text.toString()
        val confirmPassword:String = txtConfirmPassword.text.toString()

        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword)){
            if(password === confirmPassword){
                progressBar.visibility=View.VISIBLE

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
                    task ->
                    if(task.isComplete){
                        val user: FirebaseUser?=auth.currentUser
                        verifyEmail(user)

                        val userBD = dbReference.child(user?.uid.toString())

                        userBD.child("Username").setValue(username)
                        action()
                    }
                }
            }
            else{
                Toast.makeText(this, "La contraseña no coincide!", Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(this, "Por favor, llene los campos", Toast.LENGTH_LONG).show()
        }
    }
    private fun action(){
        startActivity(Intent(this, LoginActivity::class.java))
    }
    private fun verifyEmail(user:FirebaseUser?){
        user?.sendEmailVerification()?.addOnCompleteListener(this){
            task ->
            if(task.isComplete){
                Toast.makeText(this, "Email enviado", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Error al enviar el email", Toast.LENGTH_LONG).show()
            }
        }
    }
}