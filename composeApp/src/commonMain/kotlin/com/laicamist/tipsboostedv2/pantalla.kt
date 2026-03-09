package com.laicamist.tipsboostedv2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.laicamist.tipsboosted.ui.TipViewModel
import com.laicamist.tipsboosted.ui.theme.TipesTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import tipsboosted.composeapp.generated.resources.Res
import tipsboosted.composeapp.generated.resources.app_name
import tipsboosted.composeapp.generated.resources.monto
import tipsboosted.composeapp.generated.resources.porcentaje
import tipsboosted.composeapp.generated.resources.rendondear
import tipsboosted.composeapp.generated.resources.resultado
import tipsboosted.composeapp.generated.resources.titulo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tipsAppinterface() {
    TopAppBar(
        title = { Text(stringResource(Res.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tipsApp(viewModel: TipViewModel = viewModel()) {
    val monto by viewModel.monto_.collectAsState()
    val porcentaje by viewModel.porcentaje_.collectAsState()
    val redondeo by viewModel.redondeo_.collectAsState()
    val resultado by viewModel.resultado_.collectAsState()
    DisposableEffect(Unit) {
        println("Interfaz creada")

        onDispose {
            println("Interfaz destruida")
        }
    }
    TipesTheme {
        Scaffold(
            topBar = { tipsAppinterface() } // aquí reutilizas tu barra superior
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .statusBarsPadding()
                    .safeDrawingPadding()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .widthIn(max = 600.dp) // límite en PC
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(stringResource(Res.string.titulo))
                    OutlinedTextField(
                        value = monto,
                        onValueChange = { nuevoValor ->
                            if (nuevoValor.equals("")) {
                                viewModel.setmonto("0.0")
                            } else {
                                // Validar que no empiece con punto
                                if (!nuevoValor.startsWith(".")) {
                                    // Intentar convertir a número
                                    val numero = nuevoValor.toDoubleOrNull()
                                    viewModel.setmonto(nuevoValor)
                                }else{
                                    viewModel.setmonto("0.0")
                                }
                            }
                        },
                        label = { Text(stringResource(Res.string.monto)) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = porcentaje,
                        onValueChange = { nuevoPorcentaje ->
                            if (nuevoPorcentaje.equals("")) {
                                viewModel.setmonto("0.0")
                            } else {
                                // Validar que no empiece con punto
                                if (!nuevoPorcentaje.startsWith(".")) {
                                    // Intentar convertir a número
                                    val numero = nuevoPorcentaje.toDoubleOrNull()
                                    viewModel.setmonto(nuevoPorcentaje)
                                }else{
                                    viewModel.setmonto("0.0")
                                }
                            }
                        },
                        label = { Text(stringResource(Res.string.porcentaje)) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Switch(
                            checked = redondeo, // valor booleano de tu ViewModel
                            onCheckedChange = { viewModel.setRedondeo(it) }
                        )
                        Text(stringResource(Res.string.rendondear))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "${stringResource(Res.string.resultado)}$ $resultado",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }
        }
    }
}

