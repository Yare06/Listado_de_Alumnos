package com.example.listado_de_alumnos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listado_de_alumnos.databinding.ActivityMainNuevoBinding


class MainActivityNuevo : AppCompatActivity() {

    private lateinit var binding: ActivityMainNuevoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNuevoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener {
            //Pasamos los valores de los editText a variables
            val txtNom = binding.txtNombre.text
            val txtCue = binding.txtCuenta.text
            val txtCorr = binding.txtCorreo.text
            val txtImg = binding.txtImage.text

            //Creamos el Intent para pasarnos al MainActivity y mandamos por extras los valores
            val intento2 = Intent(this, MainActivity::class.java)

        }
    }
}
