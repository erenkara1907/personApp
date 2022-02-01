package com.erenkara.personsapp.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erenkara.personsapp.viewmodel.HomeViewModel
import com.erenkara.personsapp.viewmodel.PersonDetailViewModel

class PersonDetailViewModelFactory (var application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PersonDetailViewModel(application) as T
    }
}