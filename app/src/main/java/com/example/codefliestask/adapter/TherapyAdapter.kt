package com.example.codefliestask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.codefliestask.R
import com.example.codefliestask.databinding.ItemLayoutBinding
import com.example.codefliestask.model.Data

class TherapyAdapter(private val list : List<Data>):RecyclerView.Adapter<TherapyAdapter.NetworkAdapterViewHolder>(){

    class NetworkAdapterViewHolder(var binding : ItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetworkAdapterViewHolder {
        val binding : ItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout,
            parent,
            false
        )
        return NetworkAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NetworkAdapterViewHolder, position: Int) {
        val current = list[position]
        holder.itemView.apply {
            holder.binding.therapyName.text = current.name.toString()
//            Glide.with(this).load(current.image_path.toString()).into(holder.binding.therapyImage);

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}