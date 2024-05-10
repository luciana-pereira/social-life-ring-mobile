package com.greenconect.socialliferingmobile.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import com.greenconect.socialliferingmobile.model.*

interface ApiGreenConnect {
    @POST("/api/auth")
    fun login(@Body loginData: LoginData): Call<ResponseBody>
}