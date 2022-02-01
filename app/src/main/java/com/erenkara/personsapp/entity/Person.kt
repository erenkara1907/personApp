package com.erenkara.personsapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("kisi_id")
    @Expose
    var id: Int,
    @SerializedName("kisi_ad")
    @Expose
    var name: String,
    @SerializedName("kisi_tel")
    @Expose
    var phone: String
) {
}