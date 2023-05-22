package com.example.lab7

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ChangePassword : AppCompatActivity() {

    private lateinit var emailText: EditText
    private lateinit var changebutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        emailText = findViewById(R.id.editText_email)
        changebutton = findViewById(R.id.button_changepassword)

        changebutton.setOnClickListener{
            val email = emailText.text.toString()
            var textFail = false

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this,R.string.empty_login, Toast.LENGTH_SHORT).show()
                textFail = true
            }


            if (textFail == false){
                val foundAccount = accountList.find{ it.login == email}
                if(foundAccount == null) Toast.makeText(this,R.string.error_change_password,Toast.LENGTH_SHORT).show()
                else {
                    Toast.makeText(this,R.string.success_change,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}