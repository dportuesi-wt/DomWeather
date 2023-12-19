package com.example.domweather.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    temperature: Int,
    onSyncClicked: () -> Unit = {}
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                Text(
                    text = "$temperature°F",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Indianapolis, IN",
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp
                        ),
                    textAlign = TextAlign.Center,
                )
            }

            Button(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                onClick = onSyncClicked
            ) {
                Text(text = "Sync")
            }
        }
    }
}

@Composable
@Preview
fun WeatherCardPreview() {
    WeatherCard(
        temperature = 46
    )
}