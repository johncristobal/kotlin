package miituo.com.kotlincourse.navigation.DBaseFragment

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*
import miituo.com.kotlincourse.R

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }

    fun addItem(view: View){

        val database = DBManager(this)
        val values = ContentValues()
        values.put("titulo",editTextTitulo.text.toString())
        values.put("description",editTextDescripcion.text.toString())

        val id = database.insert(values)
        if(id > 0){
            Toast.makeText(this,"Nota agregada :) ",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Error al agregar nota :( ",Toast.LENGTH_SHORT).show()
        }

        finish()
    }
}
