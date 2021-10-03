package com.example.practica

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica.databinding.ItemStoreBinding
import com.example.practica.entities.Store
import com.example.practica.repository.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Suppress("MemberVisibilityCanBePrivate")
class ItemAdapter (private val list: List<Store>):
RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){


    class ItemViewHolder (val binding: ItemStoreBinding) :
            RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemStoreBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val repository = StoreRepository.getRepository(holder.binding.root.context)

        holder.binding.btnDeletes.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO){
                repository.deleteById(list[position].id)
            }
        }
        with(holder.binding){
            tvCantidad.text="x"+list[position].cantidad.toString()
            tvName.text=list[position].name
            tvPrecio.text="$"+list[position].precio.toString()

        }
    }

    override fun getItemCount(): Int = list.size


}