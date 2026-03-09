package com.laicamist.tipsboostedv2.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TipViewModel: ViewModel() {
    private val monto = MutableStateFlow("")
    val monto_: StateFlow<String> = monto
    private val porcentaje = MutableStateFlow("")
    val porcentaje_ : StateFlow<String> = porcentaje
    private val redondeo = MutableStateFlow(false)
    val redondeo_ : StateFlow<Boolean> = redondeo
    private val resultado = MutableStateFlow("")
    val resultado_ : StateFlow<String> = resultado

    fun setmonto(entrada: String) {
        monto.value = entrada
        calculopropina()
    }
    fun setPorcentaje(entrada: String) {
        porcentaje.value = entrada
        calculopropina()
    }
    fun setRedondeo(decision: Boolean) {
        redondeo.value = decision
        calculopropina()
    }
    fun setResultado(entrada: String) {
        resultado.value = entrada
        calculopropina()
    }

    fun calculopropina() {
        var propina = (porcentaje.value.toDoubleOrNull()?:0.0) / 100 *(monto.value.toDoubleOrNull() ?: 0.0)
        if(redondeo.value) {
            propina = kotlin.math.ceil(propina)
        }
        resultado.value = propina.toString()
    }
}