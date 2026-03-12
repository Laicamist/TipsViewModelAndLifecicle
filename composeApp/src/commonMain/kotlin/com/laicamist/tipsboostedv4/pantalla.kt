package com.laicamist.tipsboostedv4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
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
import com.laicamist.tipsboostedv4.ui.TipViewModel
import com.laicamist.tipsboostedv4.ui.theme.TipesTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import tipsboostedv4.composeapp.generated.resources.Res
import tipsboostedv4.composeapp.generated.resources.app_name
import tipsboostedv4.composeapp.generated.resources.monto
import tipsboostedv4.composeapp.generated.resources.porcentaje
import tipsboostedv4.composeapp.generated.resources.rendondear
import tipsboostedv4.composeapp.generated.resources.resultado
import tipsboostedv4.composeapp.generated.resources.titulo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tipsAppinterface() {
    TopAppBar(
        title = { Text(stringResource(Res.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tipsApp(viewModel: TipViewModel = viewModel { TipViewModel() }) {
    val monto by viewModel.monto_.collectAsState()
    val porcentaje by viewModel.porcentaje_.collectAsState()
    val redondeo by viewModel.redondeo_.collectAsState()
    val resultado by viewModel.resultado_.collectAsState()

    TipesTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Calculadora de Propinas") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
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
                        .widthIn(max = 600.dp)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        text = stringResource(Res.string.titulo),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    OutlinedTextField(
                        value = monto,
                        onValueChange = { nuevoValor ->
                            val regex = Regex("^\\d*\\.?\\d*$")
                            if (nuevoValor.isEmpty()) {
                                viewModel.setmonto("0.0")
                            } else if (regex.matches(nuevoValor) && !nuevoValor.startsWith(".")) {
                                viewModel.setmonto(nuevoValor)
                            }
                        },
                        label = { Text(stringResource(Res.string.monto)) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.width(300.dp)
                    )
                    OutlinedTextField(
                        value = porcentaje,
                        onValueChange = { nuevoValor ->
                            val regex = Regex("^\\d*\\.?\\d*$") //Expresion regular para validar entrada
                            if (nuevoValor.isEmpty()) {
                                viewModel.setPorcentaje("0.0")
                            } else if (regex.matches(nuevoValor)) {
                                if (!nuevoValor.startsWith(".")) {
                                    viewModel.setPorcentaje(nuevoValor)
                                } else {
                                    viewModel.setPorcentaje("0.0")
                                }
                            }
                        },
                        label = { Text(stringResource(Res.string.porcentaje)) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.width(300.dp)
                    )
                    // Switch con texto alineado
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Switch(
                            checked = redondeo,
                            onCheckedChange = { viewModel.setRedondeo(it) }
                        )
                        Text(stringResource(Res.string.rendondear))
                    }

                    // Resultado en una Card
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(Res.string.resultado),
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "$ $resultado",
                                style = MaterialTheme.typography.headlineLarge,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                }
            }
        }
    }
}
