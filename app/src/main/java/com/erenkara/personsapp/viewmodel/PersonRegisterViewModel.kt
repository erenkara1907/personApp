package com.erenkara.personsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.erenkara.personsapp.repository.PersonDaoApiRepository

class PersonRegisterViewModel(application: Application) : AndroidViewModel(application) {
    // var personRepository = PersonDaoDbRepository(application)
    var personRepository = PersonDaoApiRepository()

    fun personRegister(name: String, phone: String){
        personRepository.create(name,phone)
    }
}