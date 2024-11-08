package com.example.t5a1_benavent_vicent

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.t5a1_benavent_vicent.databinding.ItemComidaBinding

class ComidaAdapter(private val comida: List<Comida>, private val listener: OnClickListener) : RecyclerView.Adapter<ComidaAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_comida, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemComida = comida[position]
        with(holder) {
            setListener(itemComida)
            binding.tvOrder.text = (position + 1).toString()
            binding.tvName.text = itemComida.name
            binding.tvCountry.text = itemComida.pais
            Glide.with(context)
                .load(itemComida.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgPhoto)
        }
    }

    override fun getItemCount(): Int = comida.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemComidaBinding.bind(view)

        fun setListener(itemComida: Comida) {
            // Configurar el click listener para abrir la actividad y pasar la URL
            binding.root.setOnClickListener {
                val intent = Intent(context, ImageView::class.java)
                intent.putExtra("URL", itemComida.wiki)
                context.startActivity(intent)

                // También informar al listener
                listener.onClick(itemComida)
            }
        }
    }
}
