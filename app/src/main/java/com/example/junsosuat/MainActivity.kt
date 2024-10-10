package com.example.junsosuat

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.junsosuat.ui.theme.JunsoSuatTheme
import com.example.junsosuat.ui.theme.Artwork

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JunsoSuatTheme {
                JunsoSuatApp()
            }
        }
    }
}

@SuppressLint("AutoboxingStateCreation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JunsoSuatApp() {
    var currentArtworkIndex by remember { mutableStateOf(0) }
    val artworks = listOf(
        Artwork(R.drawable.jun1, "Papeda IKan Kuah Kuning","Junso", "2024"),
        Artwork(R.drawable.jun2, "Ikan Asar", "Junso", "2024"),
        Artwork(R.drawable.jun3, "Kasbi Komplet", "Junso", "2024"),
        Artwork(R.drawable.jun4, "ikan Kuah Pala Banda", "Junso", "2024"),
        Artwork(R.drawable.jun5, "Suami", "Junso", "2024")
        // Add more artworks here
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("MAKANAN KHAS MALUKU")
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ArtworkDisplay(artwork = artworks[currentArtworkIndex])

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {
                    currentArtworkIndex = (currentArtworkIndex - 1 + artworks.size) % artworks.size
                }) {
                    Text("Previous")
                }

                Button(onClick = {
                    currentArtworkIndex = (currentArtworkIndex + 1) % artworks.size
                }) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
fun ArtworkDisplay(artwork: Artwork) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = artwork.imageResourceId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = artwork.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            text = "by ${artwork.artist}",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Text(
            text = artwork.year,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

