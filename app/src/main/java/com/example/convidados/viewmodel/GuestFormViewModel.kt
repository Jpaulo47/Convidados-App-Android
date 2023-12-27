package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.convidados.R
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val apllication = application

    private val repository  = GuestRepository(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: MutableLiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<String>()
    val saveGuest: MutableLiveData<String> = _saveGuest

    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }

    fun save(guest: GuestModel) {
        if (guest.id == 0) {
            if (repository.insert(guest)) {
                _saveGuest.value = apllication.getString(R.string.insert_sucess)
            } else {
                _saveGuest.value = apllication.getString(R.string.error)
            }
        } else {
            if (repository.update(guest)) {
                _saveGuest.value = apllication.getString(R.string.update_sucess)
            } else {
                _saveGuest.value = apllication.getString(R.string.error)
            }
        }
    }
}