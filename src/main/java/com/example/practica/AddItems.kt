package com.example.practica

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.lifecycleScope
import com.example.practica.databinding.ActivityAddItemsBinding
import com.example.practica.entities.Store
import com.example.practica.repository.StoreRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddItems : AppCompatActivity() {
    private lateinit var binding: ActivityAddItemsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addListener()
    }
    private fun addListener() {
        val repository = StoreRepository.getRepository(this)
        binding.btnAdd.setOnClickListener {
            hideKeyboard()
            with(binding) {
                if (etName.text.isBlank() || etCantidad.text.isBlank() || etPrecio.text.isBlank()) {
                    Snackbar.make(this.root, "Some fields are empty", Snackbar.LENGTH_SHORT).show()
                } else {
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            repository.insert(
                                Store(
                                    name = etName.text.toString(),
                                    cantidad = etCantidad.text.toString().toInt(),
                                    precio = etPrecio.text.toString().toInt()
                                )
                            )
                        }
                        onBackPressed()
                    }
                }
            }
        }
    }
    private fun hideKeyboard() {
        val manager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}