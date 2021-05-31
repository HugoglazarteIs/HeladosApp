package com.istea.heladosapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.istea.heladosapp.R
import com.istea.heladosapp.model.Pedido
import com.istea.heladosapp.model.Repartidor

class RepartidoresAdapter(private val dataSet: ArrayList<Repartidor>): RecyclerView.Adapter<RepartidoresAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nombre: TextView
        val foto: ImageView

        init{
            nombre = view.findViewById(R.id.ri_t_nombre)
            foto = view.findViewById(R.id.ri_iv_foto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepartidoresAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repartidor_item, parent,false)
        return RepartidoresAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: RepartidoresAdapter.ViewHolder, position: Int) {
        holder.nombre.text = dataSet[position].nombre.toString()
        holder.foto.setImageResource(dataSet[position].foto)
    }


}