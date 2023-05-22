package com.example.lab7


data class Accounts(val login: String, val password: String)

var accountList = listOf(
    Accounts("Android@mail.ru","9999"),
    Accounts("Studio@mail.ru","laba"))