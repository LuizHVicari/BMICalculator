package com.github.IMCCalculatorLuiz

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var etWeight : EditText
    private lateinit var etHeight : EditText
    private lateinit var tvResult : TextView
    private lateinit var btCalculate : Button
    private lateinit var btClear : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initElements()

    }

    private fun initElements() {
        etWeight = findViewById(R.id.etWeight)
        etHeight = findViewById(R.id.etHeight)
        tvResult = findViewById(R.id.tvResult)
        btCalculate = findViewById(R.id.btCalculate)
        btClear = findViewById(R.id.btClear)

        btCalculate.setOnClickListener {
            btCalculateOnClick()
        }

        btClear.setOnClickListener{
            btClearOnClick()
        }
    }

    private fun btClearOnClick() {
        etWeight.setText("")
        etHeight.setText("")
        tvResult.text = getString(R.string.value)
        etWeight.requestFocus()
        Toast.makeText(this, getString(R.string.restart_screen), Toast.LENGTH_SHORT).show()
    }

    private fun validateFieldNotEmpty(field : EditText): Boolean {
        if (field.text.toString().isEmpty()) {
            field.error = getString(R.string.not_null_field)
            field.requestFocus()
            return false
        }
        return true
    }


    private fun btCalculateOnClick() {
        if (validateFieldNotEmpty(etWeight) and validateFieldNotEmpty(etHeight)) {
            val weight = etWeight.text.toString().toDouble()
            val height = etHeight.text.toString().toDouble()
            var bmi = weight / height.pow(2)
            bmi *= if (!Locale.getDefault().equals("en")) 1 else 703

            tvResult.text = NumberFormat.getNumberInstance(Locale.getDefault()).format(bmi)
        }
    }
}