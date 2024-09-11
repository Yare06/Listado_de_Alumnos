package com.example.listado_de_alumnos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listado_de_alumnos.databinding.ItemPersonasBinding

class AlumnoAdapter(private val context: Context, private val ListAlumnos:List<Alumno>, private val optionsMenuClickListener: OptionsMenuClickListener):
    RecyclerView.Adapter<AlumnoAdapter.ViewHolder>() {

    interface OptionsMenuClickListener {
        fun onOptionsMenuClicked(position: Int)
    }
    inner class ViewHolder(val binding: ItemPersonasBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPersonasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlumnoAdapter.ViewHolder, position: Int) {
        with(holder) {
            with(ListAlumnos[position]) {
                Glide.with(context).load(this.imagen).into(binding.imgPersona)
                binding.tvNombre.text = this.nombre
                binding.tvCuenta.text = this.cuenta
                binding.tvCorreo.text = this.correo

                binding.textViewOptions.setOnClickListener{
                    optionsMenuClickListener.onOptionsMenuClicked(position)
                }
            }
        }
    }
    override fun getItemCount(): Int {
        return ListAlumnos.size
    }
}
