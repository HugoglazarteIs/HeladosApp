package com.istea.heladosapp

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.util.EthiopicCalendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.istea.heladosapp.adapter.HeladosCajaAdapter
import com.istea.heladosapp.model.*

class CajaActivity : AppCompatActivity() {

    var listHelado: ArrayList<Helado> = ArrayList<Helado>()
    var listCajas: ArrayList<Caja> = ArrayList<Caja>()
    var listRepartidores: ArrayList<Repartidor> = ArrayList<Repartidor>()
    var listPedidos: ArrayList<Pedido> = ArrayList<Pedido>()

    lateinit var rvHelados: RecyclerView
    lateinit var cajaSel: Spinner
    lateinit var layoutCaja: ConstraintLayout

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caja)
        inicializar()

        rvHelados = findViewById(R.id.rv_helados)
        rvHelados.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rvHelados.adapter = HeladosCajaAdapter(listHelado) { item ->  cargarDetallePedido(item) }

    }

    private fun inicializar(){
        listHelado.add(Helado(ArrayList<String>(), ETipoHelado.CONO, "Cono de helado", 200.0, R.mipmap.helado_cono))
        listHelado.add(Helado(ArrayList<String>(), ETipoHelado.VASO, "Vaso de helado de 1/4", 400.0, R.mipmap.helado_vaso))
        listHelado.add(Helado(ArrayList<String>(), ETipoHelado.KILO, "Kilo de helado", 800.0, R.mipmap.helado_kilo))
/*        listCajas.add(Caja(1,0, 5))
        listCajas.add(Caja(2,0, 10))
        listCajas.add(Caja(3,0, 15))
        listRepartidores.add(Repartidor("Carlos"))
        listRepartidores.add(Repartidor("Marta"))
        listRepartidores.add(Repartidor("Pepe"))
        listRepartidores.add(Repartidor("Juan"))*/

        listCajas = intent.getSerializableExtra("listCajas") as ArrayList<Caja>
        listRepartidores = intent.getSerializableExtra("listRepartidores") as ArrayList<Repartidor>
        listPedidos = intent.getSerializableExtra("listPedidos") as ArrayList<Pedido>

        layoutCaja = findViewById(R.id.ly_caja)
        cajaSel = findViewById(R.id.sp_cajas_disponibles)
        inicializarSpinner()
    }

    private fun inicializarSpinner(){
        val adaptadorSp = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getNumCaja())
        adaptadorSp.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        cajaSel.adapter = adaptadorSp
    }

    private fun getNumCaja(): ArrayList<String>{
        var listCajasNum: ArrayList<String> = ArrayList<String>()
            listCajas.forEach{item -> listCajasNum.add(item.numero.toString())}
        return listCajasNum
    }

    private fun cargarDetallePedido(helado: Helado){
        // if cajero valido
        if(cajeroIsValid(cajaSel.selectedItem.toString().toInt())){
            Toast.makeText(this, "Cajero valido", Toast.LENGTH_SHORT).show()
            // toma item y caja y carga layout
            Toast.makeText(this, helado.desc + helado.precio.toString(), Toast.LENGTH_SHORT).show()

            var viewDetalle = LayoutInflater.from(this).inflate(R.layout.caja_detalle_layout, null)
            val caja: TextView = viewDetalle.findViewById(R.id.cd_t_idcaja)
            val heladoTitulo: TextView = viewDetalle.findViewById(R.id.cd_t_tipoHelado)
            val cargar: Button = viewDetalle.findViewById(R.id.cd_b_cargar)
            caja.setText(cajaSel.selectedItem.toString())
            heladoTitulo.setText(helado.tipo.toString())

            val gusto01: TextView = viewDetalle.findViewById(R.id.cd_t_gusto01)
            val gusto02: TextView = viewDetalle.findViewById(R.id.cd_t_gusto02)
            val gusto03: TextView = viewDetalle.findViewById(R.id.cd_t_gusto03)
            val gusto04: TextView = viewDetalle.findViewById(R.id.cd_t_gusto04)

            val spOpcionales: Spinner = viewDetalle.findViewById(R.id.cd_sp_opcional)
            val spRepartidores: Spinner = viewDetalle.findViewById(R.id.cd_sp_repartidor)

            val adaptadorSpRepartidor = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getRepartidoresNames())
            adaptadorSpRepartidor.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spRepartidores.adapter = adaptadorSpRepartidor

            when(heladoTitulo.text){
                ETipoHelado.CONO.toString() -> {
                    gusto03.isEnabled=false;
                    gusto04.isEnabled=false;
                }
                ETipoHelado.VASO.toString() -> {
                    gusto04.isEnabled=false;
                    val list = arrayListOf("crema de vainilla","chocolate fundido","-")
                    val adaptadorSpOpcionales = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)
                    adaptadorSpOpcionales.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                    spOpcionales.adapter = adaptadorSpOpcionales
                }
                ETipoHelado.KILO.toString() -> {
                    val list = arrayListOf("chocolate fundido","almendras","crema","-")
                    val adaptadorSpOpcionales = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)
                    adaptadorSpOpcionales.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                    spOpcionales.adapter = adaptadorSpOpcionales
                }
            }


            cargar.setOnClickListener {

                when(heladoTitulo.text){
                    ETipoHelado.CONO.toString() -> {
                        val gustosSel = arrayListOf(gusto01.text.toString(), gusto02.text.toString())
                        helado.gustos = gustosSel
                        guardarPedido(helado, cajaSel.selectedItemPosition, spRepartidores.selectedItemPosition)
                    }
                    ETipoHelado.VASO.toString() -> {
                        val gustosSel = arrayListOf(gusto01.text.toString(), gusto02.text.toString(), gusto03.text.toString(), spOpcionales.selectedItem.toString())
                        helado.gustos = gustosSel
                        guardarPedido(helado, cajaSel.selectedItemPosition, spRepartidores.selectedItemPosition)
                    }
                    ETipoHelado.KILO.toString() -> {
                        val gustosSel = arrayListOf(gusto01.text.toString(), gusto02.text.toString(), gusto03.text.toString(), gusto04.text.toString(), spOpcionales.selectedItem.toString())
                        helado.gustos = gustosSel
                        guardarPedido(helado, cajaSel.selectedItemPosition, spRepartidores.selectedItemPosition)
                    }
                }

                val intentCaja: Intent = Intent(this, MainActivity::class.java)
                intentCaja.putExtra("listPedidos", listPedidos)
                intentCaja.putExtra("listCajas", listCajas)
                startActivity(intentCaja)
            }

            layoutCaja.removeAllViews()
            layoutCaja.addView(viewDetalle)

        }
        else
        {
            Toast.makeText(this, "Cajero INVALIDO: Excede el numero de pedidos diarios.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getRepartidoresNames(): ArrayList<String> {
        var listRepNames: ArrayList<String> = ArrayList<String>()
        listRepartidores.forEach{ item -> listRepNames.add(item.nombre) }
        return listRepNames
    }

    private fun cajeroIsValid(id: Int): Boolean {
        var respuesta: Boolean = true
        for(i in listCajas){
            if(i.numero == id){
                respuesta = i.cantPedidos < i.maxPedidos
            }
        }
        return respuesta

    }

    private fun guardarPedido(helado: Helado, caja: Int, repartidor: Int){
        listCajas[caja].cantPedidos++
        listPedidos.add(Pedido(helado, listCajas[caja], listRepartidores[repartidor]))
    }

}