package com.example.skeletonapp.Model.api

import retrofit2.http.GET
import retrofit2.http.Path

interface ConfigApi {
    @GET("{arquivo}")
    suspend fun buscarConfig(@Path("arquivo") arquivo:String): ConfigDto
}