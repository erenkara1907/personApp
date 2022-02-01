package com.erenkara.personsapp.retrofit

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getPersonDaoInterface(): PersonDaoInterface {
            return RetrofitClient.getClient(BASE_URL).create(PersonDaoInterface::class.java)
        }
    }
}