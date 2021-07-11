package com.example.suitmediaapp.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.suitmediaapp.db.DatabaseContract.GuestColumn
import com.example.suitmediaapp.db.DatabaseContract.GuestColumn.Companion.ID
import com.example.suitmediaapp.db.DatabaseContract.GuestColumn.Companion.TABLE_NAME
import java.sql.SQLException

class GuestHelper(context: Context) {

    private var databaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {

        private const val DATABASE_TABLE = TABLE_NAME
        private var INSTANCE: GuestHelper? = null

        fun getInstance(context: Context): GuestHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: GuestHelper(context)
            }
    }

    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "${GuestColumn.ID} ASC"
        )
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$ID = ?", arrayOf(id))
    }
}