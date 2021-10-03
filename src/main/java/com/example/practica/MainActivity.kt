package com.example.practica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practica.databinding.ActivityAddItemsBinding
import com.example.practica.databinding.ActivityMainBinding

import com.example.practica.entities.Store
import com.example.practica.repository.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buildList()
        addListeners()
    }
    private fun buildList() {
// Get Repository
        val repository = StoreRepository.getRepository(this)
// Build Layout manager
        val layoutManager = GridLayoutManager(this, 1)
// Catch other thread
        lifecycleScope.launch {
            repository.allItems.collect { items ->

                binding.rvItems.apply {
                    adapter = ItemAdapter(items)
                    setLayoutManager(layoutManager)
                }

                var suma=0

                items.forEach {

                    suma+=it.precio*it.cantidad


                }

                binding.total.apply {
                    text="$ " +suma.toString()
                }
                binding.ettotal.apply {
                    text="TOTAL:"
                }
            }
        }
    }
    private fun addListeners() {
        binding.fbAdd.setOnClickListener {
            startActivity(Intent(this, AddItems::class.java))
        }

        }
    }
