package com.example.financas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.financas.R
import com.example.financas.entity.MovimentEntity
import com.example.financas.enums.MovimentType
import com.example.financas.extensions.getFormattedCurrency

class BalanceAdapter(
    private val items: List<MovimentEntity>,
    private val listen: (item: MovimentEntity) -> Unit
) : RecyclerView.Adapter<BalanceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_balance, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name: AppCompatTextView = itemView.findViewById(R.id.name)
        private val description: AppCompatTextView = itemView.findViewById(R.id.description)
        private val value: AppCompatTextView = itemView.findViewById(R.id.value)
        private val viewType: View = itemView.findViewById(R.id.viewType)

        //Responsável por preencher os valores de cada item do RecyclerView
        fun bind(item: MovimentEntity){
            name.text = item.name
            description.text = item.description
            value.text = item.value.getFormattedCurrency()

            when(item.type){
                MovimentType.IN -> viewType.setBackgroundColor(ContextCompat.getColor(itemView.context, android.R.color.holo_green_dark))
                MovimentType.OUT -> viewType.setBackgroundColor(ContextCompat.getColor(itemView.context, android.R.color.holo_red_dark))
            }

            itemView.setOnClickListener { listen.invoke(item) }
        }
    }
}