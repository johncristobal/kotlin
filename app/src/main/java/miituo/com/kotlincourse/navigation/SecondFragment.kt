package miituo.com.kotlincourse.navigation


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.modelobd.view.*
import miituo.com.kotlincourse.Model.Notas

import miituo.com.kotlincourse.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SecondFragment : Fragment() {

    var listaNotas = ArrayList<Notas>();

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listaNotas.add(Notas(1,"Titulo","Descripcion"))
        listaNotas.add(Notas(2,"Titulo","Descripcion"))
        listaNotas.add(Notas(3,"Titulo","Descripcion"))
        listaNotas.add(Notas(4,"Titulo","Descripcion"))

        var adpter = notasAdapter(activity!!,listaNotas)

        listaVista.layoutManager = LinearLayoutManager(activity!!)

        listaVista.adapter = adpter
    }



    class notasAdapter(context: Context, listaDeNotas: ArrayList<Notas>) : RecyclerView.Adapter<CustomHolder>() {

        var context = context
        var notas = listaDeNotas

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {

            var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            return CustomHolder(inflater.inflate(R.layout.modelobd,parent,false))
        }

        override fun getItemCount(): Int {
            return notas.size
        }

        override fun onBindViewHolder(holder: CustomHolder, position: Int) {

            holder.title.text = notas[position].titulo
            holder.description.text = notas[position].description
        }



    }

    class CustomHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        //val tvAnimalType = view.tv_animal_type
        val title = view.textViewTitulo
        val description = view.textViewContenido
    }


}
