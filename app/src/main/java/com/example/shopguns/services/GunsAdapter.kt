package com.example.shopguns.services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopguns.databinding.ItemGunBinding
import com.example.shopguns.models.Gun

class GunsAdapter(private val gunsList: List<Gun>, private val clickListener: (Int) -> Unit) : RecyclerView.Adapter<GunsAdapter.GunViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GunViewHolder {
        val binding = ItemGunBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GunViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GunViewHolder, position: Int) {
        val currentGun = gunsList[position]
        holder.binding.gun = currentGun // оголошення змінної gun в макеті

        holder.itemView.setOnClickListener {
            clickListener(position)
        }
    }

    override fun getItemCount() = gunsList.size

    inner class GunViewHolder(val binding: ItemGunBinding) : RecyclerView.ViewHolder(binding.root)
}