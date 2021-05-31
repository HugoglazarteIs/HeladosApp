package com.istea.heladosapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.istea.heladosapp.model.Caja
import com.istea.heladosapp.model.Pedido
import com.istea.heladosapp.model.Repartidor

class MainActivity : AppCompatActivity() {

    lateinit var b_caja: Button
    lateinit var b_ver: Button
    lateinit var b_repartidores: Button
    var listCajas: ArrayList<Caja> = ArrayList<Caja>()
    var listRepartidores: ArrayList<Repartidor> = ArrayList<Repartidor>()
    var listPedidos: ArrayList<Pedido> = ArrayList<Pedido>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializar()

        b_caja.setOnClickListener {
            val intento: Intent = Intent(this, CajaActivity::class.java)
            intento.putExtra("listCajas", listCajas)
            intento.putExtra("listRepartidores", listRepartidores)
            intento.putExtra("listPedidos", listPedidos)
            startActivity(intento)
        }

        b_ver.setOnClickListener {
            val intento: Intent = Intent(this, PedidosActivity::class.java)
            intento.putExtra("listCajas", listCajas)
            intento.putExtra("listRepartidores", listRepartidores)
            intento.putExtra("listPedidos", listPedidos)
            startActivity(intento)
        }

        b_repartidores.setOnClickListener {
            val intento: Intent = Intent(this, RepartidoresActivity::class.java)
            intento.putExtra("listCajas", listCajas)
            intento.putExtra("listRepartidores", listRepartidores)
            intento.putExtra("listPedidos", listPedidos)
            startActivity(intento)
        }
    }

    private fun inicializar(){
        b_caja = findViewById(R.id.b_main_caja)
        b_ver = findViewById(R.id.b_main_verPedidos)
        b_repartidores = findViewById(R.id.b_main_repartidores)
        inicializarListas()
    }

    private fun inicializarListas(){
        if(intent.getSerializableExtra("listCajas") != null){
            listCajas = intent.getSerializableExtra("listCajas") as ArrayList<Caja>
        } else {
            listCajas.add(Caja(1,0, 5))
            listCajas.add(Caja(2,0, 10))
            listCajas.add(Caja(3,0, 15))
        }
        if(intent.getSerializableExtra("listRepartidores") != null){
            listRepartidores = intent.getSerializableExtra("listRepartidores") as ArrayList<Repartidor>
        } else {
            listRepartidores.add(Repartidor("Carlos", R.mipmap.carlos))
            listRepartidores.add(Repartidor("Marta", R.mipmap.marta))
            listRepartidores.add(Repartidor("Pepe", R.mipmap.pepe))
            listRepartidores.add(Repartidor("Juan", R.mipmap.juan))
        }
        if(intent.getSerializableExtra("listPedidos") != null){
            listPedidos = intent.getSerializableExtra("listPedidos") as ArrayList<Pedido>
        }
    }

}