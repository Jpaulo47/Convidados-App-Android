package com.example.convidados.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.model.GuestModel

class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guestModel: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guestModel.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guestModel.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guestModel: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guestModel.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guestModel.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guestModel.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    @SuppressLint("Recycle", "Range")
    fun getAll(): List<GuestModel>{

        val list: MutableList<GuestModel> = ArrayList()

        try {
            val db = guestDataBase.readableDatabase
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor =
                db.query(DataBaseConstants.GUEST.TABLE_NAME, projection, null, null, null, null, null)

            if(cursor != null && cursor.count > 0){
                while(cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    list.add( GuestModel(id, name, presence))

                }
            }

            cursor.close()

            return list
        }catch (e: Exception){
            return list
        }

    }

    @SuppressLint("Recycle", "Range")
    fun getFilterType(isPresent: Boolean): List<GuestModel>{
        val present = if(isPresent) 1 else 0
        val list: MutableList<GuestModel> = ArrayList()

        try {
            val db = guestDataBase.readableDatabase

            val cursor =
                db.rawQuery("SELECT * FROM ${DataBaseConstants.GUEST.TABLE_NAME} WHERE ${DataBaseConstants.GUEST.COLUMNS.PRESENCE} = $present", null)

            if(cursor != null && cursor.count > 0){
                while(cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    list.add( GuestModel(id, name, presence))

                }
            }

            cursor.close()

            return list
        }catch (e: Exception){
            return list
        }

    }


}