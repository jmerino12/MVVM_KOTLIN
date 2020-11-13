package com.jmb.mvvm_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jmb.mvvm_kotlin.databinding.ItemRowBinding
import de.hdodenhof.circleimageview.CircleImageView

class MainAdapter(private val context:Context):RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    private var dataList = mutableListOf<Usuario>()

    fun setListData(data:MutableList<Usuario>){
        dataList = data
    }

    //Agregando viewBinding al reciclyview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val viewBinding  = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = dataList[position]
        holder.bindView(user)
    }

    override fun getItemCount(): Int {
        return if (dataList.size>0){
            dataList.size
        }else{
            0
        }
    }
       //Inner class su ventaja es que estar dentro de la clase, esta es una clase hija de MainAdapter, cunaod su intancia muera tambien la
        //instancia su clase hija, ayuda al perfomance

    inner class MainViewHolder(private val viewBinding:ItemRowBinding):RecyclerView.ViewHolder(viewBinding.root) {
           /*private lateinit var circleImage:CircleImageView
           private lateinit var title:TextView
           private lateinit var description:TextView*/ //USANDO VIEWBIDING  -> se agrega private val para acceder
           fun bindView(user:Usuario){
                viewBinding.txtTitle.text = user.nombre
               viewBinding.txtDesc.text = user.descripcion
               Glide.with(context).load(user.imageUrl).into(viewBinding.circleImageView)
               /*title = itemView.findViewById(R.id.txt_title)
               description = itemView.findViewById(R.id.txt_desc)
               Glide.with(context).load(user.imageUrl).into(circleImage)
               title.text = user.nombre
               description.text = user.descripcion*/

        }
    }
}