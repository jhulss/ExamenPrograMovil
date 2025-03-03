package com.ucb.ucbtest.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {

    private val _cadena = MutableLiveData("0")
    val cadena: LiveData<String> = _cadena

    fun increment() {
        _cadena.value = ((_cadena.value?.toIntOrNull() ?:0) + 1).toString()
    }

}