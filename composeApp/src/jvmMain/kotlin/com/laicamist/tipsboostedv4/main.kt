package com.laicamist.tipsboostedv4

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Calculadora de Propinas",
        state = rememberWindowState(
            size = DpSize(400.dp, 600.dp)
        )
    ) {
        Column(
            modifier = Modifier.wrapContentSize()
        ) {
            tipsApp()
        }
    }
}