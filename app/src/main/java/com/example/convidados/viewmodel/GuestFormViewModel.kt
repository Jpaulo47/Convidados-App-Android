package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository  = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: MutableLiveData<GuestModel> = guestModel

    fun insert(guestModel: GuestModel) {
        repository.insert(guestModel)
    }

    fun update(guestModel: GuestModel) {
        repository.update(guestModel)
    }

    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }

    fun save(guest: GuestModel) {
        if (guest.id == 0) {
            insert(guest)
        } else {
            update(guest)
        }
    }
}