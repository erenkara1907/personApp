package com.erenkara.personsapp.repository

import androidx.lifecycle.MutableLiveData
import com.erenkara.personsapp.entity.CrudResponse
import com.erenkara.personsapp.entity.Person
import com.erenkara.personsapp.entity.PersonResponse
import com.erenkara.personsapp.retrofit.ApiUtils
import com.erenkara.personsapp.retrofit.PersonDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonDaoApiRepository {
    var personList = MutableLiveData<List<Person>>()
    var personDaoInterface: PersonDaoInterface

    init {
        personDaoInterface = ApiUtils.getPersonDaoInterface()
        personList = MutableLiveData()
    }

    fun getPersons(): MutableLiveData<List<Person>> {
        return personList
    }

    fun getAll() {
        personDaoInterface.getAll().enqueue(object : Callback<PersonResponse> {
            override fun onResponse(
                call: Call<PersonResponse>,
                response: Response<PersonResponse>
            ) {
                val list = response.body()!!.person
                personList.value = list
            }

            override fun onFailure(call: Call<PersonResponse>, t: Throwable) {}
        })
    }

    fun search(searchWord: String) {
        personDaoInterface.search(searchWord).enqueue(object : Callback<PersonResponse> {
            override fun onResponse(
                call: Call<PersonResponse>,
                response: Response<PersonResponse>
            ) {
                val list = response.body()!!.person
                personList.value = list
            }

            override fun onFailure(call: Call<PersonResponse>, t: Throwable) {}
        })
    }

    fun remove(id: Int) {
        personDaoInterface.remove(id).enqueue(object : Callback<CrudResponse> {
            override fun onResponse(call: Call<CrudResponse>, response: Response<CrudResponse>) {
                getAll()
            }

            override fun onFailure(call: Call<CrudResponse>, t: Throwable) {}
        })
    }

    fun create(name: String, phone: String) {
        personDaoInterface.create(name, phone).enqueue(object : Callback<CrudResponse> {
            override fun onResponse(call: Call<CrudResponse>, response: Response<CrudResponse>) {}
            override fun onFailure(call: Call<CrudResponse>, t: Throwable) {}
        })
    }

    fun update(id: Int, name: String, phone: String) {
        personDaoInterface.update(id, name, phone).enqueue(object : Callback<CrudResponse> {
            override fun onResponse(call: Call<CrudResponse>, response: Response<CrudResponse>) {}
            override fun onFailure(call: Call<CrudResponse>, t: Throwable) {}
        })
    }
}