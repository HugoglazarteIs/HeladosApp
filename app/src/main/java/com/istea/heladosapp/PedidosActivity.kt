package com.istea.heladosapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.istea.heladosapp.adapter.HeladosCajaAdapter
import com.istea.heladosapp.adapter.PedidosAdapter
import com.istea.heladosapp.model.Caja
import com.istea.heladosapp.model.Pedido
import com.istea.heladosapp.model.Repartidor

class PedidosActivity : AppCompatActivity() {

    var listRepartidores: ArrayList<Repartidor> = ArrayList<Repartidor>()
    var listPedidos: ArrayList<Pedido> = ArrayList<Pedido>()
    var listCajas: ArrayList<Caja> = ArrayList<Caja>()

    lateinit var rvPedidos: RecyclerView
    lateinit var b_volver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)
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

        listRepartidores = intent.getSerializableExtra("listRepartidores") as ArrayList<Repartidor>
        listPedidos = intent.getSerializableExtra("listPedidos") as ArrayList<Pedido>
        listCajas = intent.getSerializableExtra("listCajas") as ArrayList<Caja>

        b_volver = findViewById(R.id.p_b_volver)
        rvPedidos = findViewById(R.id.rv_pedidos)
        rvPedidos.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rvPedidos.adapter = PedidosAdapter(listPedidos)

        printLista()
    }

    private fun printLista(){
        println(listPedidos[0].caja.numero.toString())
        println(listPedidos[0].helado.tipo.toString())
        println(listPedidos[0].repartidor.nombre.toString())
    }
}