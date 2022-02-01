package com.erenkara.personsapp.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erenkara.personsapp.viewmodel.HomeViewModel
import com.erenkara.personsapp.viewmodel.PersonRegisterViewModel

class PersonRegisterViewModelFactory (var application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PersonRegisterViewModel(application) as T
    }
}