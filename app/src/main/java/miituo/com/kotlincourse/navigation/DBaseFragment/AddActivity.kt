package miituo.com.kotlincourse.navigation.DBaseFragment

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*
import miituo.com.kotlincourse.R
import java.lang.Exception

class AddActivity : AppCompatActivity() {

    var idNota = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        try {
            val intent: Bundle = intent.extras
            idNota = intent.getInt("notaid")
            if (idNota != 0) {
                var titulo = intent.getString("titulo")
                var descripcion = intent.getString("descripcion")

                editTextTitulo.setText(titulo)
                editTextDescripcion.setText(descripcion)
            }
        }catch (e: Exception){
            Log.w("Eroror","Fail")
        }
    }

    fun addItem(view: View){

        val database = DBManager(this)
        val values = ContentValues()
        values.put("titulo",editTextTitulo.text.toString())
        values.put("description",editTextDescripcion.text.toString())

        //si id == 0, entonces es nota nueva
        if (idNota == 0){
            val id = database.insert(values)
            if (id > 0) {
                Toast.makeText(this, "Nota agregada :) ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al agregar nota :( ", Toast.LENGTH_SHORT).show()
            }
        }else{
            //caso que no, netonces vamos a actualizart una nota
            val selecionargs = arrayOf(idNota.toString())
            val id = database.actualizar(values,"Id=?",selecionargs)
            if (id > 0) {
                Toast.makeText(this, "Nota actualizada :) ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al actualizar nota :( ", Toast.LENGTH_SHORT).show()
            }
        }

        finish()
    }
}
