package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application.applicationContext)
    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAllList() {
        listAllGuests.value = repository.getAll()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }

    fun getPresents() {
        listAllGuests.value = repository.getPresent()
    }

    fun getAbsents() {
        listAllGuests.value = repository.getAbsent()
    }

}