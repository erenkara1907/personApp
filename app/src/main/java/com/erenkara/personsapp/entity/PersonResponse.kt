package com.erenkara.personsapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PersonResponse(
    @SerializedName("kisiler")
    @Expose
    var person : List<Person>,
    @SerializedName("success")
    @Expose
    var success : Int
) {
}