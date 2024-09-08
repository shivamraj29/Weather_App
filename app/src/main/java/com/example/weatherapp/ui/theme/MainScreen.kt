package com.example.weatherapp.ui.theme

import android.graphics.ColorSpace.Rgb
import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
//import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainScreen(
   viewState: MainViewModel.StateofPage,
   navigateToDetail: (weatherData)-> Unit,
   locationData: locationData
){
    val viewmodel: MainViewModel = viewModel()
    val viewState by viewmodel.resp




    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth()
                        .background(color = Color(red = 255, 242, 215)),
                    horizontalAlignment = Alignment.CenterHorizontally,

                )
                {
                    TopAppBar(title = { Text(text = "Weather App", letterSpacing = 2.sp,
                        fontSize = 30.sp, fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.padding(top = 8.dp))
                    }, colors = TopAppBarDefaults.topAppBarColors(titleContentColor = Color.LightGray,
                        containerColor = Color(0,98,105)
                    ))

                    Column(modifier = Modifier.padding(16.dp)) {

                        val gradientColors = listOf(Blue, Magenta, Red /*...*/)
                        Text(
                            text = "Get Real Time Weather Updates",
                            style = TextStyle(
                                brush = Brush.linearGradient(
                                    colors = gradientColors
                                )
                            ), modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp) ,fontSize = 20.sp, textAlign = TextAlign.Center
                        )

                        searchLoc(viewModel = viewmodel)
                        myapp(viewMainModel = viewmodel, locationData = locationData)

                    }



                    Column(modifier = Modifier.wrapContentHeight()) {
                        if (!viewState.isEditing) {
                            viewState.List?.let {
                                WeatherInfo(
                                    data = it, navigateToDetail
                                )
                            }
                        } else if (viewState.error != null) {
                            Text(text = "error occured ${viewState.error}")
                        }
                    }
                }

            }
        }

                
            }
        }
    



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchLoc(viewModel: MainViewModel
              ){

    var query by viewModel.query
    val state by viewModel.resp
    val focusManager = LocalFocusManager.current
    val search = state.search
    var isSearching by remember {
        mutableStateOf(false)
    }

    
Column(
    modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 16.dp),
   // horizontalAlignment = Alignment.CenterHorizontally,
) {


    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(value = query, onValueChange = {
            query = it
            isSearching= true
            if (query.length >= 3) {
                viewModel.fetchSearch(query)
            }

        },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,

            label = { Text(text = "Enter Location") },
            shape = RoundedCornerShape(32.dp),
            trailingIcon = {
                IconButton(onClick = {
                    if (query.isNotEmpty()) {
                        viewModel.fetchForecast(query)
                        focusManager.clearFocus()
                        isSearching = false
                    }

                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(end = 6.dp)
                    )
                }
            }
        )


    }

    if (search != null && isSearching && query.length>=3)
    LazyColumn (modifier = Modifier.padding(start = 24.dp,end = 24.dp)){
        items(search){ search->
            suggestedList(search = search, onClick ={newQuery->
                query= newQuery
                state.isEditing = false
                viewModel.fetchForecast(query)
                focusManager.clearFocus()
                isSearching = false
              //  query = ""


            } )
        }
    }


}
}

@Composable
fun WeatherInfo(data: weatherData,
                navigateToDetail: (weatherData)-> Unit,

) {
    Column(modifier = Modifier
        .fillMaxWidth()
        )
        {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 16.dp,
            ),
            colors = CardDefaults.cardColors(containerColor = Color(231,212,181)),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
                .clickable { navigateToDetail(data) },
            shape = RoundedCornerShape(16.dp)

            ) {
            Column(modifier = Modifier
                .wrapContentHeight()
               // .paint(painter = rememberAsyncImagePainter(model = "https:"+ data.current.condition.icon))
                ){
              //  Image(painter = rememberAsyncImagePainter(model = data.current.condition.icon), contentDescription = null,)
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "${data.current.temp_c.toInt()}°C", fontSize = 32.sp)
                    Text(text = "Feels Like:\n${data.current.feelslike_c.toInt()}°C")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp),
                    ) {
                    
                Text(text = data.location.name, fontSize = 22.sp)
                    if (data.location.region.length>14){
                Text(text = data.location.region.drop(15))}
                    else{Text(text = data.location.region)}
                Text(text = data.current.last_updated)
            }
                Column(modifier = Modifier
                    .padding(16.dp)
                   // .weight(1f)
                    ,
                   horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                    AsyncImage(model = "https:"+ data.current.condition.icon, contentDescription =null,
                        modifier = Modifier.size(80.dp),  contentScale = ContentScale.FillBounds,)
                }
            }
            Column {
                HorizontalDivider(thickness = 2.dp, color = Color.Black)
                Row(modifier = Modifier.fillMaxWidth().padding(top=8.dp, bottom = 8.dp, start = 16.dp, end = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "See Details", fontSize = 16.sp)
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null,)
                }}
        }
    }
}}

@Composable
fun suggestedList(search: searchItem,
                  onClick:(String)->Unit){
    Box(modifier = Modifier
        //.border(2.dp, color = MaterialTheme.colorScheme.surfaceTint)
        .fillMaxWidth()

        ){
        Row(modifier = Modifier.clickable { onClick(search.name) }) {
            Text(text = search.name+", " +search.region, modifier = Modifier.padding(8.dp), fontWeight = FontWeight.SemiBold)
        }
    }
}

/* Text(text = data.current.condition.text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)
                    Text(text = "Humidity:${data.current.humidity}")
                    Text(text = "Precip:"+data.current.precip_mm.toString()+"%")
                    Text(text = "Wind:"+data.current.wind_kph+"kph", modifier = Modifier.padding(bottom = 32.dp))

                    */