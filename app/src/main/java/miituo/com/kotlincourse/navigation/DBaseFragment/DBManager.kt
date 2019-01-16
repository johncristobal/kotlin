package miituo.com.kotlincourse.navigation.DBaseFragment

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.util.Log

class DBManager{

    val dbName = "notasdata"
    val dbTabla = "Notas"
    val columnaId = "Id"
    val columnaTitulo = "titulo"
    val columnaDescription = "description"
    val version = 1

    val sqlcrearTabla = "create table if not exists " + dbTabla +
            " ("+columnaId+" integer primary key autoincrement," +
            ""+columnaTitulo+" text not null," +
            ""+columnaDescription+" text not null"+
        ")"

    //instancia de dbhelper para acceder a la base
    var sqlDB: SQLiteDatabase?=null

    //crea instancia de dbhelper desde constructor
    constructor(contexto: Context){
        val db = DBHelper(contexto)
        sqlDB = db.writableDatabase
    }

    inner class DBHelper(contexto: Context) : SQLiteOpenHelper(contexto,dbName,null,version){
        var context:Context = contexto

        override fun onCreate(p0: SQLiteDatabase?) {
            p0!!.execSQL(sqlcrearTabla)
            Log.e("DBmanager","Database created")
        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            p0!!.execSQL("Drop table if exists "+dbTabla)
        }
    }

    fun insert(values: ContentValues):Long{
        val id = sqlDB!!.insert(dbTabla,"",values)
        return id.toLong()
    }

    fun query(projection: Array<String>,selection:String,selectionArgs:Array<String>,order:String): Cursor {
        //buildder
        var consulta = SQLiteQueryBuilder()

        //set table
        consulta.tables = dbTabla

        //set query
        val cursor = consulta.query(
            sqlDB,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            order)

        return cursor
    }

    fun borrar(selection: String,selectionArgs: Array<String>): Int {
        val contador = sqlDB!!.delete(dbTabla,selection,selectionArgs)
        return contador
    }
}