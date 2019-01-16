package miituo.com.kotlincourse.navigation.DBaseFragment


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.modelobd.view.*
import miituo.com.kotlincourse.Model.Notas

import miituo.com.kotlincourse.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SecondFragment : Fragment() {

    var listaNotas = ArrayList<Notas>();

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //le decimos que tendra un menu diferente
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_second, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*listaNotas.add(Notas(1,"Titulo","Descripcion"))
        listaNotas.add(Notas(2,"Titulo","Descripcion"))
        listaNotas.add(Notas(3,"Titulo","Descripcion"))
        listaNotas.add(Notas(4,"Titulo","Descripcion"))*/

        query("%")

    }

    override fun onResume() {
        super.onResume()
        query("%")
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menuadd, menu);

        //definimos qiue se comportara como search view
        val buscar = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        //manager
        val manager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        buscar.setSearchableInfo(manager.getSearchableInfo(activity!!.componentName))
        //recuperamos texto cuando se de enter o buscar
        buscar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(activity!!,"Texto: "+query,Toast.LENGTH_LONG).show()
                query("%"+query+"%")
                return false
            }
        })

        buscar.setOnCloseListener(object: SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                query("%")
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId){
            R.id.addaction -> {
                var intent = Intent(activity!!,AddActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun query(titulo:String){
        var data = DBManager(activity!!)
        var columnas = arrayOf("Id","titulo","description")
        var selectionArgs = arrayOf(titulo)

        //hace query
        val cursor = data.query(columnas,"titulo like ? ",selectionArgs,"titulo")

        listaNotas.clear()

        //itera cursor
        if(cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex("Id"))
                val titulo = cursor.getString(cursor.getColumnIndex("titulo"))
                val description = cursor.getString(cursor.getColumnIndex("description"))

                listaNotas.add(Notas(id, titulo, description))
            }while(cursor.moveToNext())

            //adapter de la lista
            var adpter = notasAdapter(activity!!, listaNotas)

            //manager recycler
            listaVista.layoutManager = LinearLayoutManager(activity!!)
            listaVista.adapter = adpter
        }
    }

    inner class notasAdapter(context: Context, listaDeNotas: ArrayList<Notas>) : RecyclerView.Adapter<CustomHolder>() {

        var context = context
        var notas = listaDeNotas

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {

            var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            return CustomHolder(
                inflater.inflate(
                    R.layout.modelobd,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int {
            return notas.size
        }

        override fun onBindViewHolder(holder: CustomHolder, position: Int) {

            holder.title.text = notas[position].titulo
            holder.description.text = notas[position].description

            holder.imageborrar.setOnClickListener{
                val dbmanager = DBManager(this.context)
                val selectionArgs = arrayOf(notas[position].notaId.toString())

                dbmanager.borrar("Id=?",selectionArgs)

                query("%")
            }
        }

    }

    class CustomHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        //val tvAnimalType = view.tv_animal_type
        val title = view.textViewTitulo
        val description = view.textViewContenido

        val imageborrar = view.imageViewDelete
    }


}
