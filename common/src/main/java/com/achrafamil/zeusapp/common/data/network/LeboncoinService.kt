package com.achrafamil.zeusapp.common.data.network

import retrofit2.http.GET

interface LeboncoinService {

    @GET("img/shared/technical-test.json")
    suspend fun getTracks(): List<RestTrack>
}
