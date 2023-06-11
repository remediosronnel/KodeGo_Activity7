package com.remedios.activity7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.remedios.activity7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
private lateinit var binding : ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val student = mutableListOf(
            Student(
                "Bubbles Casal",
                "BS Volcanizing",
                "College of Mechanics"
            ),
            Student(
                "Elpidio Dimagiba",
                "AB English major in Embalming",
                "College of Arts and Sciences"
            ),
            Student(
                "Ronnel Remedios",
                "BS Accountancy",
                "College of Business"
            ),
            Student(
                "Sylvia Cam Bu Ai",
                "AB Normal",
                "College of Medicine"
            ),
            Student(
                "Nana Butete",
                "MA in Witch craft",
                "College of Wizardry"
            ),
            Student(
                "Harry Pothead",
                "MA in Wizardry",
                "College of Wizardry"
            )
        )

        recyclerView = binding.wrapper
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DataAdapter(student, this)
    }
}