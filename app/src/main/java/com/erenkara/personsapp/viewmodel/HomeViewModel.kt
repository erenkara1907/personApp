package com.erenkara.personsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.erenkara.personsapp.entity.Person
import com.erenkara.personsapp.repository.PersonDaoApiRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    // var personRepository = PersonDaoDbRepository(application)
    var personRepository = PersonDaoApiRepository()
    var personList = MutableLiveData<List<Person>>()

    init {
        personGetAll()
        personList = personRepository.getPersons()
    }

    fun personGetAll() {
        personRepository.getAll()
    }

    fun personSearch(searchWord: String) {
        personRepository.search(searchWord)
    }

    fun personDelete(id: Int) {
        personRepository.remove(id)
    }
}