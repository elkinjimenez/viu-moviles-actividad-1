package com.co.calculator_viu

import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var textOperations: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textOperations = findViewById<TextView>(R.id.textOperations)

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            if (textOperations.text.toString().isNotEmpty()) {
                textOperations.text =
                    textOperations.text.dropLast(if (textOperations.text.last() == ' ') 3 else 1)
            } else {
                showToast("No hay nada que borrar")
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            textOperations.text = ""
        }

        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            textOperations = findViewById<TextView>(R.id.textOperations)
            try {
                var lastSplit = textOperations.text.toString().split(" ").last()
                //Checks that if the last value in the list is empty or is a point, it does not perform the mathematical operation.
                if (!(lastSplit == "" || lastSplit == ".")) {
                    if (textOperations.text.toString().isNotEmpty()) {
                        val expression = textOperations.text.toString().replace("x".toRegex(), "*")
                        val resp = evalExpression(expression)
                        // Change this to send it to another activity...
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra("resultado", resp)
                        startActivity(intent)
                    }
                } else {
                    showToast("Operación incompleta para procesar")
                }
            } catch (exception: ArithmeticException) {
                // Change this to send it to another activity...
                showToast("Error: ${exception.message.toString()}")
            }
        }

        findViewById<Button>(R.id.btn_language).setOnClickListener {
            val config = resources.configuration
            if (config.locale == Locale.US) {
                config.locale = Locale("es")
            } else {
                config.locale = Locale.US
            }
            resources.updateConfiguration(config, resources.displayMetrics)
            recreate()
        }
    }

    fun calculator(view: View) {
        var valueBtn: String = (view as Button).text.toString()
        var textOperations: TextView = findViewById<TextView>(R.id.textOperations)
        var lastSplit = textOperations.text.toString().split(" ").last()
        // Checks that if the button pressed is a mathematical operator and the last value of the list is empty or a point, it does not allow adding the typed field.
        if (isOperator(valueBtn) && (lastSplit == "" || lastSplit == ".")) {
            showToast("No se pueden agregar dos operadores seguidos")
            return
        }
        // Checks that if the button pressed is a point and the last value in the list is a point, it does not allow adding the typed field.
        if (valueBtn == "." && lastSplit.contains(".")) {
            showToast("El número ya tiene su separador de decimales")
            return
        }
        textOperations.text =
            textOperations.text.toString() + (if (isOperator(valueBtn)) " $valueBtn " else valueBtn)
    }

    private fun evalExpression(expression: String): Double {
        val expression = ExpressionBuilder(expression).build()
        return expression.evaluate()
    }

    private fun isOperator(value: String): Boolean {
        return value == "+" || value == "-" || value == "x" || value == "/"
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}