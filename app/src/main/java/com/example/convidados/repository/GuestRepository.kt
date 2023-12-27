package com.example.convidados.repository

import android.content.Context
import com.example.convidados.model.GuestModel

class GuestRepository (context: Context) {

    private val guestDataBase = GuestDataBase.getDataBase(context).guestDAO()


    fun insert(guestModel: GuestModel): Boolean {
        return guestDataBase.insert(guestModel) > 0
    }

    fun update(guestModel: GuestModel): Boolean {
        return guestDataBase.update(guestModel) > 0
    }

    fun delete(id : Int) {
        val guest = get(id)
        guestDataBase.delete(guest)
    }

    fun getAll(): List<GuestModel>{
        return guestDataBase.getAll()
    }

   fun getPresent(): List<GuestModel> {
        return guestDataBase.getPresent()
    }

    fun getAbsent(): List<GuestModel> {
        return guestDataBase.getAbsent()
    }

    fun get(id: Int): GuestModel {
        return guestDataBase.get(id)
    }

}