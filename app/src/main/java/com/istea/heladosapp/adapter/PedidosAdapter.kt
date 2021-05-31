package com.istea.heladosapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.istea.heladosapp.R
import com.istea.heladosapp.model.Helado
import com.istea.heladosapp.model.Pedido

class PedidosAdapter(private val dataSet: ArrayList<Pedido>): RecyclerView.Adapter<PedidosAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val titulo: TextView
        val caja: TextView
        val precio: TextView
        val foto: ImageView
        val nombre: TextView
        val gustos: TextView

        init{
            titulo = view.findViewById(R.id.pi_t_titulo)
            caja = view.findViewById(R.id.pi_t_idCaja)
            precio = view.findViewById(R.id.pi_t_precio)
            foto = view.findViewById(R.id.pi_iv_fotoPedido)
            nombre = view.findViewById(R.id.pi_t_nombreReparidor)
            gustos = view.findViewById(R.id.pi_t_gustospedido)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidosAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pedido_item, parent,false)
        return PedidosAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: PedidosAdapter.ViewHolder, position: Int) {
        holder.titulo.text = dataSet[position].helado.tipo.toString()
        holder.caja.text = dataSet[position].caja.numero.toString()
        holder.precio.text = "$ " + dataSet[position].helado.precio.toString()
        var gustos = ""
        dataSet[position].helado.gustos.forEach {
            gustos += " " + it
        }
        holder.gustos.text = dataSet[position].helado.gustos.joinToString(separator = "-")
        holder.nombre.text = dataSet[position].repartidor.nombre.toString()
        holder.foto.setImageResource(dataSet[position].helado.foto)


/*        holder.comprar.setOnClickListener {
            listener(dataSet[position])
        }*/
    }

}