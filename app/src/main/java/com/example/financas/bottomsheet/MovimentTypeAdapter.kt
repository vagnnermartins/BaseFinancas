package com.example.financas.bottomsheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.financas.R
import com.example.financas.enums.MovimentType

class MovimentTypeAdapter(
    val items: List<MovimentType>,
    val listener: (item: MovimentType) -> Unit
) : RecyclerView.Adapter<MovimentTypeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_dialog_moviment_type, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image: AppCompatImageView = view.findViewById(R.id.image)
        val text: AppCompatTextView = view.findViewById(R.id.text)

        //Responsável por preencher os valores de cada item do RecyclerView
        fun bind(item: MovimentType){
            image.setImageResource(item.image)
            text.text = item.value
            itemView.setOnClickListener {
                listener.invoke(item)
            }
        }
    }
}