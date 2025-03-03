package com.ucb.usecases

import kotlinx.coroutines.delay

class DoLogin {
    suspend fun invoke(userName: String, password: String) : Boolean {
        delay(5000)
        return (userName.equals("calyr") && password.equals("123456"))
    }
}