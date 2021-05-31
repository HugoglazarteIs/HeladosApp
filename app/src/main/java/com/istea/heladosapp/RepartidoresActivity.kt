package com.istea.heladosapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.istea.heladosapp.adapter.PedidosAdapter
import com.istea.heladosapp.adapter.RepartidoresAdapter
import com.istea.heladosapp.model.Caja
import com.istea.heladosapp.model.Pedido
import com.istea.heladosapp.model.Repartidor

class RepartidoresActivity : AppCompatActivity() {

    var listCajas: ArrayList<Caja> = ArrayList<Caja>()
    var listRepartidores: ArrayList<Repartidor> = ArrayList<Repartidor>()
    var listPedidos: ArrayList<Pedido> = ArrayList<Pedido>()

    lateinit var rvRepartidores: RecyclerView
    lateinit var b_volver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repartidores)
        inicializar()

        b_volver.setOnClickListener {
            val intento: Intent = Intent(this, MainActivity::class.java)
            intento.putExtra("listPedidos", listPedidos)
            intento.putExtra("listCajas", listCajas)
            startActivity(intento)
        }
    }

    @SuppressLint("WrongConstant")
    private fun inicializar(){
        listCajas = intent.getSerializableExtra("listCajas") as ArrayList<Caja>
        listRepartidores = intent.getSerializableExtra("listRepartidores") as ArrayList<Repartidor>
        listPedidos = intent.getSerializableExtra("listPedidos") as ArrayList<Pedido>

        b_volver = findViewById(R.id.r_volver)
        rvRepartidores = findViewById(R.id.rv_repartidores)
        rvRepartidores.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rvRepartidores.adapter = RepartidoresAdapter(listRepartidores)

    }
}