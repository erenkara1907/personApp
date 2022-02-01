package com.erenkara.personsapp.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.erenkara.personsapp.entity.PersonDb
import com.erenkara.personsapp.room.DatabaseClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PersonDaoDbRepository(var application: Application) {
    var personList = MutableLiveData<List<PersonDb>>()
    var db : DatabaseClass

    init {
        db = DatabaseClass.accessDatabase(application)!!
        personList = MutableLiveData()
    }

    fun getPersons() : MutableLiveData<List<PersonDb>> {
        return personList
    }

    fun personGetAll() {
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            personList.value = db.personDao().getAll()
        }
    }

    fun personSearch(searchWord: String) {
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            personList.value = db.personDao().searchPerson(searchWord)
        }
    }

    fun personRegister(name: String, phone: String) {
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            val newPerson = PersonDb(0,name,phone)
            db.personDao().add(newPerson)
        }
    }

    fun personUpdate(id: Int, name: String, phone: String) {
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            val updatedPerson = PersonDb(id,name,phone)
            db.personDao().update(updatedPerson)
        }
    }

    fun personDelete(id: Int) {
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            val deletedPerson = PersonDb(id, "", "")
            db.personDao().delete(deletedPerson)
            personGetAll()
        }
    }
}