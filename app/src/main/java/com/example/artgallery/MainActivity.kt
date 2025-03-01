package com.example.artgallery

import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtGalleryTheme {
                Surface (modifier = Modifier.fillMaxSize(),color = MaterialTheme.colorScheme.background) {
                    ArtGallery(
                        modifier = Modifier.padding()
                    )
                }
            }
        }
    }
}

@Composable
fun ArtGallery(modifier: Modifier = Modifier) {
    val imageSetCity = arrayOf<Int>(
        R.drawable.city1,
        R.drawable.city2,
        R.drawable.city3
    )
    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Column (
            modifier = modifier
                .padding(top = 16.dp)
                .fillMaxHeight(0.6F)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            for(image in imageSetCity){
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxWidth()
                )
            }
        }
        Text(
            text = "City Landscapes",
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .padding(16.dp)
                .fillMaxHeight(0.2F)
                .fillMaxSize()
        )
        Row (
            modifier = modifier
                .fillMaxHeight(0.2F),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            NavigateGallery({}, "Previous")
            NavigateGallery({}, "Next")
        }
    }
}

@Composable
fun NavigateGallery(onClick: () -> Unit, descriptor: String){
    Button (
        onClick = onClick
    ) {
        Text(text = descriptor)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtGalleryTheme {
        ArtGallery()
    }
}