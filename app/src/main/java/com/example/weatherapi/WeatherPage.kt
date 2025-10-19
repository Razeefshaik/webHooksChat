package com.example.weatherapi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun WeatherPage(viewModel: WeatherViewModel){

    var city by remember{

        mutableStateOf("")
    }

     val weatherResult= viewModel.weatherResult.observeAsState()

    Scaffold {padding->


        Column (modifier = Modifier
            .padding(padding)
            .fillMaxWidth()){

            Row (modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){

                OutlinedTextField(value = city, onValueChange = { city = it },
                    label = { Text(text = "Enter city") }
                )

                IconButton(onClick = { viewModel.getData(city) }) {


                    Icon(
                        imageVector = Icons.Default.Search, contentDescription = "Search Icon"

                    )
                }

            }

            when(val result=weatherResult.value){
                is NetworkResponse.Error ->{
                    Text(text = "Error"+result.message)
                }
                NetworkResponse.Loading -> {
                    CircularProgressIndicator()
                }
                is NetworkResponse.Success -> {

                    Text(text = result.data.toString())

                }
                null -> {


                }
            }
        }
    }
}




@Composable
@Preview(showBackground = true)
fun prev(){

    WeatherPage(viewModel = WeatherViewModel())
}