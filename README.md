<p align = "center">МИНИСТЕРСТВО НАУКИ И ВЫСШЕГО ОБРАЗОВАНИЯ
РОССИЙСКОЙ ФЕДЕРАЦИИ
ФЕДЕРАЛЬНОЕ ГОСУДАРСТВЕННОЕ БЮДЖЕТНОЕ
ОБРАЗОВАТЕЛЬНОЕ УЧРЕЖДЕНИЕ ВЫСШЕГО ОБРАЗОВАНИЯ
«САХАЛИНСКИЙ ГОСУДАРСТВЕННЫЙ УНИВЕРСИТЕТ»</p>
<br>
<p align = "center">Институт естественных наук и техносферной безопасности</p>
<p align = "center">Кафедра информатики</p>
<p align = "center">Пак Никита Витальевич</p>
<br>
<p align = "center">Лабораторная работа №9</p>
<p align = "center">«Создание интерфейсов с использованием макетов и виджетов»</p>
<p align = "center">01.03.02 Прикладная математика и информатика</p>
<br><br><br><br><br><br><br><br>
<p align = "right" >Научный руководитель</p>
<p align = "right" >Соболев Евгений Игоревич</p>
<p align = "center" >Южно-Сахалинск</p>
<p align = "center" >2023 г.</p>
<p align = "center" ><b>ВВЕДЕНИЕ</b></p>
<p>Kotlin (Ко́тлин) — статически типизированный, объектно-ориентированный язык программирования, работающий поверх Java Virtual Machine и разрабатываемый компанией JetBrains. Также компилируется в JavaScript и в исполняемый код ряда платформ через инфраструктуру LLVM. Язык назван в честь острова Котлин в Финском заливе, на котором расположен город Кронштадт</p>
<p>Авторы ставили целью создать язык более лаконичный и типобезопасный, чем Java, и более простой, чем Scala. Следствием упрощения по сравнению со Scala стали также более быстрая компиляция и лучшая поддержка языка в IDE. Язык полностью совместим с Java, что позволяет Java-разработчикам постепенно перейти к его использованию; в частности, язык также встраивается Android, что позволяет для существующего Android-приложения внедрять новые функции на Kotlin без переписывания приложения целиком.</p>
<p align = "center" >РЕШЕНИЕ ЗАДАЧ</p>

<p align = "center" >Упражнение. Сделать функциональный макет
Необходимо реализовать макет по изображению, соблюдая отступы согласно рисунку 1. 
</p>

<p align = "center" >Реализовать простую авторизацию по логину паролю, при удачной попытке вывести пользователю сообщение об авторизации, если пароль логин неверный, то сообщение об ошибке. 
Сделать вторую Activity для восстановления пароля в стилистике рисунка 1, также сделать переход с главной Activity на вторую.
</p>

![Screenshot](https://github.com/Pupkapus/lab9/blob/main/image.png)

![Screenshot](https://github.com/Pupkapus/lab9/blob/main/2.png)

<p align = "center" >MainActivity</p>

```kotlin
    
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
    
```

<p align = "center" >ChangePassword</p>

```kotlin

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

```

<p align = "center" >Accounts</p>

```kotlin

package com.example.lab7


data class Accounts(val login: String, val password: String)

var accountList = listOf(
    Accounts("Android@mail.ru","9999"),
    Accounts("Studio@mail.ru","laba"))

```

<p align = "center" >activity_main</p>

```kotlin

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">


    <TextView
        android:id="@+id/Top_background"
        android:layout_width="match_parent"
        android:layout_height="150dp"
        android:background="@color/orange"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/Top_textview"
        android:layout_width="0dp"
        android:layout_height="70dp"
        android:layout_marginStart="32dp"
        android:layout_marginTop="48dp"
        android:layout_weight="1"
        android:text="@string/top_text"
        android:textColor="@color/white"
        android:textSize="30dp"
        android:textStyle="bold"
        app:layout_constraintEnd_toStartOf="@+id/Top_image"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <ImageView
        android:id="@+id/Top_image"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_marginTop="32dp"
        android:layout_marginEnd="16dp"
        android:layout_weight="1"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@drawable/quest" />

    <androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/imageWaves"
        android:layout_width="match_parent"
        android:layout_height="100dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/Top_background"
        android:background="@drawable/background" ></androidx.constraintlayout.widget.ConstraintLayout>

    <LinearLayout
        android:id="@+id/layout_email"
        android:layout_width="0dp"
        android:layout_height="60dp"
        android:layout_marginStart="32dp"
        android:layout_marginEnd="32dp"
        android:orientation="horizontal"
        android:background="@drawable/rounded_orange"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageWaves" >

        <ImageView
            android:id="@+id/imageView"
            android:layout_width="35dp"
            android:layout_height="35dp"
            android:layout_gravity="center"
            android:layout_marginLeft="20dp"
            app:srcCompat="@drawable/email" />

        <EditText
            android:id="@+id/editText_email"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:background="@android:color/transparent"
            android:hint="@string/login_email"
            android:layout_marginLeft="10dp"
            android:layout_marginRight="15dp"
            android:layout_weight="1"
            android:ems="10"
            android:inputType="textEmailAddress" />
    </LinearLayout>

    <LinearLayout
        android:id="@+id/layout_password"
        android:layout_width="0dp"
        android:layout_height="60dp"
        android:layout_marginStart="32dp"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="32dp"
        android:background="@drawable/rounded_orange"
        android:orientation="horizontal"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/layout_email">

        <ImageView
            android:id="@+id/imageView2"
            android:layout_width="35dp"
            android:layout_height="35dp"
            android:layout_gravity="center"
            android:layout_marginLeft="20dp"
            app:srcCompat="@drawable/closed" />

        <EditText
            android:id="@+id/editText_password"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_marginLeft="10dp"
            android:layout_marginRight="15dp"
            android:layout_weight="1"
            android:background="@android:color/transparent"
            android:ems="10"
            android:hint="@string/login_password"
            android:inputType="textEmailAddress" />
    </LinearLayout>

    <androidx.appcompat.widget.AppCompatButton
        android:id="@+id/button_login"
        android:layout_width="0dp"
        android:layout_height="60dp"
        android:layout_marginStart="32dp"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="32dp"
        android:background="@drawable/rounded_orange_solid"
        android:text="@string/button_login"
        android:textColor="@color/white"
        android:textSize="20dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/layout_password"
        tools:ignore="MissingConstraints" />

    <TextView
        android:id="@+id/forgot_password"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="32dp"
        android:gravity="center"
        android:text="@string/textview_forgot"
        android:textColor="@color/orange"
        android:textSize="15dp"
        android:textStyle="bold"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/button_login" />

    <TextView
        android:id="@+id/sing_with"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="32dp"
        android:layout_marginEnd="32dp"
        android:text="@string/sign_with"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/forgot_password" />

    <LinearLayout
        android:id="@+id/layout_sign_with"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginEnd="32dp"
        android:orientation="horizontal"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/sing_with">

        <ImageView
            android:id="@+id/imageView3"
            android:layout_width="30dp"
            android:layout_height="30dp"
            android:layout_weight="1"
            app:srcCompat="@drawable/google" />

        <ImageView
            android:id="@+id/imageView4"
            android:layout_width="30dp"
            android:layout_height="30dp"
            android:layout_weight="1"
            app:srcCompat="@drawable/fb" />
    </LinearLayout>

    <TextView
        android:id="@+id/register"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="32dp"
        android:layout_marginEnd="32dp"
        android:text="@string/register"
        android:textColor="@color/orange"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/layout_sign_with"
        android:textStyle="bold"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```

***

<p align = "center" >ВЫВОД</p>
<p>Подводя итог всему сказанному, могу сделать вывод, что, поработав c kotlin, я узнал многое и применил это на практике. Все задачи были выполнены.</p>

 
