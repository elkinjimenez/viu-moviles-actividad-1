package com.co.calculator_viu

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView
    private lateinit var btnShare: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var resultLabel = resources.getString(R.string.result_label)
        setContentView(R.layout.activity_result)

        textViewResult = findViewById(R.id.textViewResult)
        btnShare = findViewById(R.id.btnShare)

        val resultado = intent.getDoubleExtra("resultado", 0.0)

        "$resultLabel $resultado".toString().replace(".0", "").also { textViewResult.text = it }

        btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, textViewResult.text)
            startActivity(Intent.createChooser(intent, getString(R.string.btn_shared)))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}