package com.example.domweather.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.glance.Button
import androidx.glance.layout.Column
import androidx.glance.text.Text
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import com.example.domweather.repository.OpenWeatherRepositoryImpl

class TemperatureWidget : GlanceAppWidget() {
    // Error ui ^ (Widgets dont crash)

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {
            val repository = remember { OpenWeatherRepositoryImpl(context) }

            val temp by repository.getStoredTemp("Indianapolis").collectAsState(initial = 0)

            WidgetContent(temp)
        }
    }
}

@Composable
fun WidgetContent(temp: Int) {
    Box(
        modifier = GlanceModifier.fillMaxSize()
    ) {
        Column {
            Text(
                text = "$temp°F",
                modifier = GlanceModifier
                    .padding(16.dp),
            )
            Text(
                text = "Indianapolis, IN",
                modifier = GlanceModifier
                    .padding(
                        horizontal = 16.dp
                    ),
            )
            Button(
                text = "Sync",
                modifier = GlanceModifier
                    .padding(16.dp),
                onClick = actionRunCallback<SyncAction>()
            )
        }
    }
}

class SyncAction : ActionCallback {

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        val repository = OpenWeatherRepositoryImpl(context)

        repository.updateStoredTemp("Indianapolis, IN")
    }
}
