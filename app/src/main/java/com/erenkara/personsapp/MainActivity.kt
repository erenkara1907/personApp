package com.erenkara.personsapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.erenkara.personsapp.entity.Person
import com.erenkara.personsapp.ui.theme.PersonsAppTheme
import com.erenkara.personsapp.viewmodel.HomeViewModel
import com.erenkara.personsapp.viewmodelfactory.HomeViewModelFactory
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PageNavigation()
                }
            }
        }
    }
}

@Composable
fun PageNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homeScreen") {
        composable("homeScreen") {
            HomeScreen(navController = navController)
        }

        composable("personRegisterScreen") {
            PersonRegisterScreen(navController)
        }

        composable("personDetailScreen/{person}", arguments = listOf(
            navArgument("person") { type = NavType.StringType }
        )) {
            val json = it.arguments?.getString("person")!!
            val person = Gson().fromJson(json, Person::class.java)
            PersonDetailScreen(person = person,navController)
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {

    val search = remember { mutableStateOf(false) }
    val searchResult = remember { mutableStateOf("") }
    // val context = LocalContext.current
    // val viewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory(context.applicationContext as Application))
    val viewModel : HomeViewModel = viewModel()
    val personList = viewModel.personList.observeAsState(listOf())


    LaunchedEffect(key1 = true){
        viewModel.personGetAll()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (search.value) {
                        TextField(
                            value = searchResult.value,
                            onValueChange = {
                                searchResult.value = it
                                viewModel.personSearch(it)
                            },
                            label = { Text(text = "Arama") },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedLabelColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                unfocusedLabelColor = Color.White,
                                unfocusedIndicatorColor = Color.White
                            )
                        )
                    } else {
                        Text(text = "Kişiler")
                    }
                },
                actions = {

                    if (search.value) {
                        IconButton(onClick = {
                            search.value = false
                            searchResult.value = ""
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.close_icon),
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            search.value = true
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.search_icon),
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    }
                }
            )
        },
        content = {
            LazyColumn(content = {
                items(
                    count = personList.value!!.count(),
                    itemContent = {
                        val person = personList.value!![it]
                        Card(
                            modifier = Modifier
                                .padding(all = 5.dp)
                                .fillMaxWidth()
                        ) {
                            Row(modifier = Modifier.clickable {
                                val personJson = Gson().toJson(person)
                                navController.navigate("personDetailScreen/$personJson")
                            }) {
                                Row(
                                    modifier = Modifier
                                        .padding(all = 10.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = "${person.name} - ${person.phone}")
                                    IconButton(onClick = {
                                        viewModel.personDelete(person.id)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.delete_icon),
                                            contentDescription = "",
                                            tint = Color.Gray
                                        )
                                    }
                                }

                            }
                        }

                    }
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("personRegisterScreen") },
                backgroundColor = colorResource(id = R.color.teal_200),
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.add_icon),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PersonsAppTheme {
        PageNavigation()
    }
}