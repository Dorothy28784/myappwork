package com.dorothy.myappwork.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = viewModel(),
    modifier: Modifier
) {
    val hobby = homeScreenViewModel.hobby.collectAsState()
    var hobbyName by remember { mutableStateOf(TextFieldValue("")) }
    var hobbyDescription by remember { mutableStateOf(TextFieldValue("")) }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = hobbyName,
            onValueChange = { hobbyName = it },
            label = { Text(text = "Hobby Name") },
            maxLines = 1
        )
        OutlinedTextField(
            value = hobbyDescription,
            onValueChange = { hobbyDescription = it },
            label = { Text(text = "Hobby Description") },
            minLines = 3
        )

        OutlinedButton(
            onClick = {
                homeScreenViewModel.createHobby(
                    name = hobbyName.text,
                    description = hobbyDescription.text
                )
            }
        ) {
            Text(text = "Create Hobby")
        }
        HorizontalDivider()
        Text(
            text = hobby.value.name
        )
        Text(
            text = hobby.value.description
        )
    }
}

//fun createHobby(name:String, description:String): HobbyDataModel{
//    return HobbyDataModel(name= name, description = description)
//}