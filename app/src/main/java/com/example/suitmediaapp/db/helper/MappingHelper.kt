package com.example.suitmediaapp.db.helper

import android.database.Cursor
import com.example.suitmediaapp.data.model.GuestItem
import com.example.suitmediaapp.db.DatabaseContract
import com.example.suitmediaapp.db.entity.GuestLocal

object MappingHelper {

    fun mapCursorToArrayList(cursor: Cursor?): ArrayList<GuestItem> {
        val guestList = ArrayList<GuestItem>()
        var guest: GuestItem

        cursor?.apply {
            while (moveToNext()) {
                guest = GuestItem()
                guest.id = getInt(getColumnIndexOrThrow(DatabaseContract.GuestColumn.ID))
                guest.name = getString(getColumnIndexOrThrow(DatabaseContract.GuestColumn.NAME))
                guest.birthdate = getString(getColumnIndexOrThrow(DatabaseContract.GuestColumn.BIRTHDATE))
                guestList.add(guest)
            }
        }
        return guestList
    }
}