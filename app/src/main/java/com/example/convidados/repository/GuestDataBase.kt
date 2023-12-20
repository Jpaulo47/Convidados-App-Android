package com.example.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.convidados.constants.DataBaseConstants

class GuestDataBase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "guest.db"
        private const val DATABASE_VERSION = 1
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE " + DataBaseConstants.GUEST.TABLE_NAME + " (" +
                DataBaseConstants.GUEST.COLUMNS.ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                DataBaseConstants.GUEST.COLUMNS.NAME + " TEXT, " +
                DataBaseConstants.GUEST.COLUMNS.PRESENCE + " INTEGER);")

    }

    override fun onUpgrade(db:SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}