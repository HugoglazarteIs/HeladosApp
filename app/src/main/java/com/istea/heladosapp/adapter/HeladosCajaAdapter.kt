package com.istea.heladosapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.istea.heladosapp.R
import com.istea.heladosapp.model.Helado

class HeladosCajaAdapter(private val dataSet: ArrayList<Helado>, private val listener: (Helado) -> Unit): RecyclerView.Adapter<HeladosCajaAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val titulo: TextView
        val desc: TextView
        val precio: TextView
        val foto: ImageView
        val comprar: Button

        init{
            titulo = view.findViewById(R.id.t_item_titulo)
            desc = view.findViewById(R.id.t_item_desc)
            precio = view.findViewById(R.id.t_item_precio)
            foto = view.findViewById(R.id.iv_item_foto)
            comprar = view.findViewById(R.id.b_item_comprar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.helado_item, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titulo.text = dataSet[position].tipo.toString()
        holder.desc.text = dataSet[position].desc
        holder.precio.text = dataSet[position].precio.toString()
        holder.foto.setImageResource(dataSet[position].foto)


        holder.comprar.setOnClickListener {
            listener(dataSet[position])
        }
    }

}