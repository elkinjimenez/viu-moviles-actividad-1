package com.co.calculator_viu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    private lateinit var textViewResultado: TextView
    private lateinit var buttonVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        textViewResultado = findViewById(R.id.textViewResultado)
        buttonVolver = findViewById(R.id.buttonVolver)

        val resultado = intent.getDoubleExtra("resultado", 0.0)

        textViewResultado.text = "Resultado: $resultado"

        buttonVolver.setOnClickListener {
            finish()
        }
    }
}