package com.capstone.cultour.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.cultour.R
import com.capstone.cultour.data.dummy.WisataData
import com.capstone.cultour.databinding.ActivityMainBinding
import com.capstone.cultour.ui.adapter.ListWisataAdapter

private lateinit var binding : ActivityMainBinding
private lateinit var recyclerView: RecyclerView
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.rvPlace
        recyclerView.layoutManager = LinearLayoutManager(this)
        val wisataData = WisataData()
        recyclerView.adapter = ListWisataAdapter(wisataData.lokasiWisata)
    }
}