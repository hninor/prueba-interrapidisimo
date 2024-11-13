package com.hninor.pruebainterrapidisimo.core.database.sqlitehelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context,
    private val queryList: List<String>,
    factory: SQLiteDatabase.CursorFactory?
) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {

        queryList.forEach { query ->
            db.execSQL(query)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


    companion object {
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "ESQUEMAS"

        // below is the variable for database version
        private val DATABASE_VERSION = 1


    }
}