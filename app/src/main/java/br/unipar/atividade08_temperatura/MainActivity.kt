package br.unipar.atividade08_temperatura

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTemperatura: EditText
    private lateinit var radioCelsiusParaFahrenheit: RadioButton
    private lateinit var radioFahrenheitParaCelsius: RadioButton
    private lateinit var buttonConverter: Button
    private lateinit var textViewResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTemperatura = findViewById(R.id.editTextTemperatura)
        radioCelsiusParaFahrenheit = findViewById(R.id.radioCelsiusParaFahrenheit)
        radioFahrenheitParaCelsius = findViewById(R.id.radioFahrenheitParaCelsius)
        buttonConverter = findViewById(R.id.buttonConverter)
        textViewResultado = findViewById(R.id.textViewResultado)

        buttonConverter.setOnClickListener {
            converterTemperatura()
        }
    }

    private fun converterTemperatura() {
        val temperaturaStr = editTextTemperatura.text.toString()

        if (temperaturaStr.isEmpty()) {
            Toast.makeText(this, "Por favor, insira a temperatura!", Toast.LENGTH_SHORT).show()
            return
        }

        val temperatura = temperaturaStr.toDoubleOrNull()

        if (temperatura == null) {
            Toast.makeText(this, "Por favor, insira um número válido!", Toast.LENGTH_SHORT).show()
            return
        }

        val resultado = when {
            radioCelsiusParaFahrenheit.isChecked -> {
                celsiusParaFahrenheit(temperatura)
            }
            radioFahrenheitParaCelsius.isChecked -> {
                fahrenheitParaCelsius(temperatura)
            }
            else -> {
                Toast.makeText(this, "Por favor, selecione uma conversão!", Toast.LENGTH_SHORT).show()
                return
            }
        }

        textViewResultado.text = "Resultado: %.2f".format(resultado)
    }

    private fun celsiusParaFahrenheit(celsius: Double): Double {
        return (celsius * 9 / 5) + 32
    }

    private fun fahrenheitParaCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }
}
