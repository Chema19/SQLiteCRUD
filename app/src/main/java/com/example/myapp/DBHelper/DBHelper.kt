package com.example.myapp.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapp.Model.Person

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {

    companion object{
        private val DATABASE_VER=1
        private val DATABASE_NAME="myapp.db"

        //table
        private  val TABLE_NAME="Person"
        private val COL_ID="Id"
        private val COL_NAME="Name"
        private val COL_Email="Email"

    }

    override fun onCreate(db: SQLiteDatabase?) {
    val CREATE_TABLE_QUERY=("Create TABLE $TABLE_NAME" +
            "($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT,$COL_Email TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)

    }

  //CRUD

    var allPerson:List<Person>get(){
        val lstPersons = ArrayList<Person>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db= this.writableDatabase
        val cursor = db.rawQuery(selectQuery,null)

        if(cursor.moveToFirst())
        {
            do {
                val person = Person()
                person.id= cursor.getInt(cursor.getColumnIndex(COL_ID))
                person.name= cursor.getString(cursor.getColumnIndex(COL_NAME))
                person.email= cursor.getString(cursor.getColumnIndex(COL_Email))

                lstPersons.add(person)

            }while (cursor.moveToNext())
        }

        return lstPersons
    }
        set(value) {}


    fun addPerson(person: Person)
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID,person.id)
        values.put(COL_NAME,person.name)
        values.put(COL_Email,person.email)

        db.insert(TABLE_NAME,null,values)
        db.close()

    }


    fun updatePerson(person: Person)
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID,person.id)
        values.put(COL_NAME,person.name)
        values.put(COL_Email,person.email)

        db.update(TABLE_NAME,values,"$COL_ID=?", arrayOf(person.id.toString()))
        db.close()

    }

    fun deletePerson(person: Person)
    {
        val db = this.writableDatabase

        db.delete(TABLE_NAME,"$COL_ID=?", arrayOf(person.id.toString()))
        db.close()

    }



















}