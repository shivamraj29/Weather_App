package com.example.weatherapp.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun weatherDetail(data: weatherData) {
    val viewmodel: MainViewModel = viewModel()

   

 




    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(red = 255, 242, 215))) {


        Column {
            TopAppBar(title = { Text(text = "Weather Detail", letterSpacing = 2.sp,
                fontSize = 30.sp, fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(top = 8.dp))
            }, colors = TopAppBarDefaults.topAppBarColors(titleContentColor = Color.LightGray,
                containerColor = Color(0,98,105)
            ))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                ) {
            Column() {



                    Text(text = data.location.name, fontSize = 28.sp)
                    Text(text = data.location.region)
                    Text(text = data.current.last_updated)



            }
                  AsyncImage(model = "https:"+ data.current.condition.icon, contentDescription =null ,
                        modifier = Modifier.size(80.dp))
                }
            Spacer(modifier = Modifier.padding(16.dp))
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 16.dp
                    ),
                    colors = CardDefaults.elevatedCardColors(containerColor = Color(231,212,181)),
                    modifier = Modifier.padding(8.dp)

                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text ="Pressure:"+data.current.pressure_in.toString())
                            Text(text = "Visiblity:"+data.current.vis_km.toString())
                            Text(text = "UV Index:"+data.current.uv.toString())
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = "${data.current.temp_c.toInt()}°C", fontSize = 32.sp)
                            Text(text = "Feels Like:")
                            Text(text ="${data.current.feelslike_c.toInt()}°C" )
                        }
                        Column (
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier.padding(8.dp)


                        ){
                            Text(text = data.current.condition.text,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End)
                            Text(text = "Humidity:${data.current.humidity}%")
                            Text(text = "Precip:"+data.current.precip_mm.toString()+"%")
                            Text(text = "Wind:"+data.current.wind_kph.toString()+"kph/"+data.current.wind_dir,
                                textAlign = TextAlign.End)
                        }
                    }

                }


            Spacer(modifier = Modifier.padding(16.dp))
    
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Sunrise at:\n"+data.forecast.forecastday.first().astro.sunrise)
                Text(text = "Moon Phase:\n"+ data.forecast.forecastday.first().astro.moon_phase, textAlign = TextAlign.Center)
                Text(text = "Sunset At:\n"+ data.forecast.forecastday.first().astro.sunset)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {


                LazyHorizontalGrid(rows = GridCells.Fixed(1)) {

                    items(data.forecast.forecastday) { item ->
                        gridItem(item)
                    }
                }
            }
        }
    }
}

@Composable
fun gridItem(data: ForecastDay){
    Box(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(4.dp)
        .border(border = BorderStroke(1.dp, color = Color.Black), shape = RoundedCornerShape(8.dp))
        ){
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 16.dp
            ), colors = CardDefaults.cardColors(containerColor = Color(255,233,208))){
        Column(modifier = Modifier.padding(8.dp)) {


         Text(text = data.date )
            AsyncImage(model = "https:"+ data.day.condition.icon, contentDescription =null,
                modifier = Modifier.size(32.dp))
        Text(text = "${data.day.maxtemp_c.toInt()}°C",)
        Text(text = "${data.day.mintemp_c.toInt()}°C")
        Text(text = data.day.condition.text, )
        //Image(painter = rememberAsyncImagePainter(model = "https://cdn.weatherapi.com/weather/64x64/day/176.png"), contentDescription = null)

    }}}
    
}
}

