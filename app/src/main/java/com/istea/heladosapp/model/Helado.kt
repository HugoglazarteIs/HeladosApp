package com.istea.heladosapp.model

import java.io.Serializable

class Helado(var gustos:ArrayList<String>, var tipo:ETipoHelado, var desc:String, var precio:Double, var foto:Int) :
    Serializable {
}