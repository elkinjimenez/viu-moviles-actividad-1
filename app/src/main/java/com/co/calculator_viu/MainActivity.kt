package com.co.calculator_viu

import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            var textOperations: TextView = findViewById<TextView>(R.id.textOperations)
            textOperations.text = ""
        }
    }

    fun calculator(view: View) {
        var valueBtn: String = (view as Button).text.toString()
        println("Value button: $valueBtn")
        if(valueBtn== "+" || valueBtn== "-" || valueBtn== "x" || valueBtn== "/"){
            valueBtn = " $valueBtn "
        }
        var textOperations: TextView = findViewById<TextView>(R.id.textOperations)
        val actualText = textOperations.text.toString()
        textOperations.text = actualText+ valueBtn
    }

}