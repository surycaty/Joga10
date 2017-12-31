package com.surycaty.joga10.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import com.surycaty.joga10.dao.JogadorDAO

/**
 * Created by negus on 09/12/17.
 */

class Database(context: Context?) : SQLiteOpenHelper(context, "JOGA10", null, 1) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(JogadorDAO.createJogador)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {

    }

    fun open() {
        val db = readableDatabase
    }
}
