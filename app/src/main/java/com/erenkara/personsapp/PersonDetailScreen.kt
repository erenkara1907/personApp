package com.erenkara.personsapp

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.erenkara.personsapp.entity.Person
import com.erenkara.personsapp.viewmodel.PersonDetailViewModel
import com.erenkara.personsapp.viewmodelfactory.PersonDetailViewModelFactory

@Composable
fun PersonDetailScreen(person: Person, navController: NavController) {
    val tfPersonName = remember { mutableStateOf("") }
    val tfPersonPhone = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current
    // val context = LocalContext.current
    // val viewModel: PersonDetailViewModel = viewModel(factory = PersonDetailViewModelFactory(context.applicationContext as Application))
    val viewModel : PersonDetailViewModel = viewModel()

    LaunchedEffect(key1 = true) {
        tfPersonName.value = person.name
        tfPersonPhone.value = person.phone
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Kişi Detay") })
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = tfPersonName.value,
                    onValueChange = { tfPersonName.value = it },
                    label = {
                        Text(
                            text = "Kişi Ad"
                        )
                    })

                TextField(
                    value = tfPersonPhone.value,
                    onValueChange = { tfPersonPhone.value = it },
                    label = {
                        Text(
                            text = "Kişi Tel"
                        )
                    })

                Button(onClick = {
                    val personName = tfPersonName.value
                    val personPhone = tfPersonPhone.value

                    viewModel.personUpdate(person.id, personName, personPhone)

                    localFocusManager.clearFocus()
                    navController.navigate("homeScreen")
                }, modifier = Modifier.size(250.dp, 50.dp)) {
                    Text(text = "Güncelle")
                }
            }
        }
    )
}