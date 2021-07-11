package com.example.suitmediaapp.db

import android.net.Uri
import android.provider.BaseColumns
import com.example.suitmediaapp.utils.Const.AUTHORITY
import com.example.suitmediaapp.utils.Const.SCHEME

internal class DatabaseContract {

    internal class GuestColumn : BaseColumns {

        companion object {

            const val TABLE_NAME = "guest"
            const val ID = "id"
            const val NAME = "name"
            const val BIRTHDATE = "birthdate"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }

}