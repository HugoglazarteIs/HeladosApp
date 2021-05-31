package com.istea.heladosapp.model

import java.io.Serializable

data class Pedido(var helado: Helado, var caja: Caja, var repartidor: Repartidor) : Serializable