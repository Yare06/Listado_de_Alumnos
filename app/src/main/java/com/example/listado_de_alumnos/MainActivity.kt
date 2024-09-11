package com.example.listado_de_alumnos

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.listado_de_alumnos.databinding.ActivityMainBinding
import com.example.listado_de_alumnos.databinding.ItemPersonasBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ItemPersonasBinding
    private var data = ArrayList<Alumno>()
    private lateinit var rvAdapter: AlumnoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ItemPersonasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recycler_view_alumnos.layoutManayer = LinearLayoutManager(this)

        data.add(
            Alumno(
                nombre = "Sofía Amezcua",
                cuenta = "20187649",
                imagen = "https://i.pinimg.com/736x/71/26/eb/7126ebc5abf282c1cab3f50c1d796378.jpg",
                correo = "samezcua@ucol.mx"
            )
        )
        data.add(
            Alumno(
                nombre = "Luis Gónzalez",
                cuenta = "20168294",
                imagen = "https://i.pinimg.com/236x/9d/ca/a2/9dcaa2df2182900462fee9a948671feb.jpg",
                correo = "lgonzalez@ucol.mx"
            )
        )
        data.add(
            Alumno(
                nombre = "Dario Hernández",
                cuenta = "20187649",
                imagen = "https://i.pinimg.com/236x/f8/be/58/f8be58a30e08ac7e7f0fce3c0a4cd6fc.jpg",
                correo = "dhernandez@ucol.mx"
            )
        )
        data.add(
            Alumno(
                nombre = "Yarely Sandoval",
                cuenta = "20197853",
                imagen = "https://i.pinimg.com/236x/6e/5a/7a/6e5a7a389ed115a02668ae9c1ec2e4d2.jpg",
                correo = "ysandoval3@ucol.mx"
            )
        )
        data.add(
            Alumno(
                nombre = "Rosa Isais",
                cuenta = "20120921",
                imagen = "https://i.pinimg.com/236x/13/ad/7a/13ad7adf6bce22972fc4645c328f7a93.jpg",
                correo = "risais@ucol.mx",
            )
        )
        data.add(
            Alumno(
                nombre = "Sebastian Quinteros",
                cuenta = "20148202",
                imagen = "https://i.pinimg.com/236x/b3/ca/89/b3ca8940699237be15ac311756858378.jpg",
                correo = "squinteros@ucol.mx"
            )
        )

        rvAdapter = AlumnoAdapter(this, data, object : AlumnoAdapter.OptionsMenuClickListener {
            override fun onOptionsMenuClicked(position: Int) {
                itemOptionsMenu(position)
            }
            binding.faButton.setOnClickListener {
                val intento1 = Intent(this,MainActivityNuevo::class.java)
                //intento1.putExtra("valor1","Hola mundo")
                startActivity(intento1)
            }

            private fun itemOptionsMenu(position: Int) {
                val popupMenu =
                    PopupMenu(this, binding.recyclerVWalumnos[position].findViewById(R.id.textViewOptions)
                    )
                popupMenu.inflate(R.menu.option_menu)
                val intento2 = Intent(this, MainActivityNuevo::class.java)
                popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                    override fun onMenuItemClick(item: MenuItem?): Boolean {
                        when (item?.itemId) {
                            R.id.borrar -> {
                                val tmpAlumno = data[position]
                                data.remove(tmpAlumno)
                                rvAdapter.notifyDataSetChanged()
                                return true
                            }

                            R.id.editar -> {
                                val nombre = data[position].nombre
                                val cuenta = data[position].cuenta
                                val correo = data[position].cuenta
                                val imagen = data[position].imagen

                                val idAlumno: Int = position
                                intento2.putExtra("mensaje", "edit")
                                intento2.putExtra("nombre", "${nombre}")
                                intento2.putExtra("cuenta", "${cuenta}")
                                intento2.putExtra("correo", "${correo}")
                                intento2.putExtra("imagen", "${imagen}")

                                intento2.putExtra("idA", idAlumno)
                                startActivity(intento2)
                                return true
                            }
                        }
                        return false
                    }
                })
                popupMenu.show()
            }
        })
    }
    //Variable para recibir extras
    val parExtra = intent.extras
    val msje = parExtra?.getString("mensaje")
    val nombre = parExtra?.getString("nombre")
    val cuenta = parExtra?.getString("cuenta")
    val correo = parExtra?.getString("correo")
    val image = parExtra?.getString("image")

//Preguntamos se el mensaje es para nuevo alumno
    if (msje=="nuevo"){
        //Sacamos en una variable el total de elementos en nuestra lista
        val insertIndex: Int = data.count()
        //Usamos la variable insertIndex para indicar la posición del nuevo alumno
        data.add(insertIndex,
            Alumno(
                "${nombre}",
                "$cuenta}",
                "${correo}",
                "${image}"
            )
        )
        //Notificamos que se inserto un nuevo elemento en la lista
        rvAdapter.notifyItemInserted(insertIndex)
    }
}
