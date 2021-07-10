package com.example.suitmediaapp.data.remote

import com.example.suitmediaapp.data.model.GuestItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiClient {

    @GET("{idcode}")
    fun getListUser(
        @Path("idcode") idcode: String
    ): Call<List<GuestItem>>

}