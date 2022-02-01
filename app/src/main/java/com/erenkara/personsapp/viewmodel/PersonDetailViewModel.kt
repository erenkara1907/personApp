package com.erenkara.personsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.erenkara.personsapp.repository.PersonDaoApiRepository

class PersonDetailViewModel(application: Application) : AndroidViewModel(application) {
    // var personRepository = PersonDaoDbRepository(application)
    var personRepository = PersonDaoApiRepository()

    fun personUpdate(id: Int, name: String, phone: String) {
        personRepository.update(id, name, phone)
    }
}