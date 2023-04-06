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
        var resultLabel = resources.getString(R.string.result_label)
        setContentView(R.layout.activity_result)

        textViewResultado = findViewById(R.id.textViewResultado)
        buttonVolver = findViewById(R.id.buttonVolver)

        val resultado = intent.getDoubleExtra("resultado", 0.0)

        "$resultLabel $resultado".also { textViewResultado.text = it }

        buttonVolver.setOnClickListener {
            finish()
        }
    }
}