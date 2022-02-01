package com.erenkara.personsapp.retrofit

import com.erenkara.personsapp.entity.CrudResponse
import com.erenkara.personsapp.entity.PersonResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface PersonDaoInterface {
    @GET("kisiler/tum_kisiler.php")
    fun getAll(): Call<PersonResponse>

    @POST("kisiler/tum_kisiler_arama.php")
    @FormUrlEncoded
    fun search(@Field("kisi_ad") name: String): Call<PersonResponse>

    @POST("kisiler/delete_kisiler.php")
    @FormUrlEncoded
    fun remove(@Field("kisi_id") id: Int): Call<CrudResponse>

    @POST("kisiler/insert_kisiler.php")
    @FormUrlEncoded
    fun create(
        @Field("kisi_ad") name: String,
        @Field("kisi_tel") phone: String,
    ): Call<CrudResponse>

    @POST("kisiler/update_kisiler.php")
    @FormUrlEncoded
    fun update(
        @Field("kisi_id") id: Int,
        @Field("kisi_ad") name: String,
        @Field("kisi_tel") phone: String,
    ): Call<CrudResponse>
}