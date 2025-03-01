package com.example.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.BrushPainter
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

private class Gallery (galleryName: String, galleryImages: Array<Int>) {
    val name = galleryName

    val images = galleryImages

}

@Composable
fun ArtGallery(modifier: Modifier = Modifier) {
    val galleries = arrayOf(
        Gallery(
            "City Landscapes",
            arrayOf(
                R.drawable.city1,
                R.drawable.city2,
                R.drawable.city3
            )
        ),
        Gallery(
            "Mountain Landscapes",
            arrayOf(
                R.drawable.mountain1,
                R.drawable.mountain2,
                R.drawable.mountain3,
                R.drawable.mountain4
            )
        ),
        Gallery(
            "Island Landscapes",
            arrayOf(
                R.drawable.beach1,
                R.drawable.beach2,
                R.drawable.beach3
            )
        )
    )

    var galleryNumber by remember { mutableIntStateOf(0) }

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFBFCFFF)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Column (
            modifier = modifier
                .padding(20.dp)
                .fillMaxHeight(0.7F)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFF809FFF).copy(alpha = 0.8f), shape = RoundedCornerShape(15.dp))
                .border(BorderStroke(2.dp, SolidColor(Color.Black)), shape = RoundedCornerShape(15.dp)),
        ) {
            for(image in galleries[galleryNumber].images){
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                )
            }
        }
        Text(
            text = galleries[galleryNumber].name,
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
            NavigateGallery(
                {
                    if(galleryNumber == 0){
                        galleryNumber = 0
                    }else{
                        galleryNumber -= 1
                    }
                },
                "Previous",
                modifier = Modifier
                    .width(110.dp)
            )
            Spacer(Modifier.fillMaxWidth(0.1f))
            NavigateGallery(
                {
                    if(galleryNumber == galleries.lastIndex){
                        galleryNumber = galleries.lastIndex
                    }else{
                        galleryNumber += 1
                    }
                },
                "Next",
                modifier = Modifier
                    .width(110.dp)
            )
        }
    }
}

@Composable
fun NavigateGallery(onClick: () -> Unit, descriptor: String, modifier: Modifier){
    Button (
        onClick = onClick,
        modifier = modifier
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