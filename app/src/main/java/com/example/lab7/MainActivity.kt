package com.example.lab7

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var loginButton: Button
    private lateinit var forgotPassword: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailText = findViewById(R.id.editText_email)
        passwordText = findViewById(R.id.editText_password)
        loginButton = findViewById(R.id.button_login)
        forgotPassword = findViewById(R.id.forgot_password)


        loginButton.setOnClickListener{
            val email = emailText.text.toString()
            val password = passwordText.text.toString()
            var textFail = false

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this,R.string.empty_login,Toast.LENGTH_SHORT).show()
                textFail = true
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this,R.string.empty_password,Toast.LENGTH_SHORT).show()
                textFail = true
            }

            if (textFail == false){
                val foundAccount = accountList.find{ it.login == email}
                if(foundAccount == null) Toast.makeText(this,R.string.fail_login,Toast.LENGTH_SHORT).show()
                else {
                    if(foundAccount.password != password) Toast.makeText(this,R.string.fail_login,Toast.LENGTH_SHORT).show()
                    else Toast.makeText(this,R.string.success_login,Toast.LENGTH_SHORT).show()
                }
            }

        }

        forgotPassword.setOnClickListener {
            val intent = Intent(this, ChangePassword::class.java)
            startActivity(intent)
        }

    }

}