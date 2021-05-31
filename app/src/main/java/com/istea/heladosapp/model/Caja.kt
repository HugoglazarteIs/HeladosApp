package com.istea.heladosapp.model

import java.io.Serializable

data class Caja(val numero: Int, var cantPedidos: Int, val maxPedidos: Int) : Serializable