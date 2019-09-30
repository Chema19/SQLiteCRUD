package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp.Adapter.ListPersonAdapter
import com.example.myapp.DBHelper.DBHelper
import com.example.myapp.Model.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    internal lateinit var  db:DBHelper
    internal var lstPersons:List<Person> = ArrayList<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)

        refreshData()

        //Event

        btn_add.setOnClickListener{
            val person = Person(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_email.text.toString()
            )
            db.addPerson(person)
            refreshData()
        }

        btn_update.setOnClickListener{
            val person = Person(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_email.text.toString()
            )
            db.updatePerson(person)
            refreshData()
        }


        btn_delete.setOnClickListener{
            val person = Person(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_email.text.toString()
            )
            db.deletePerson(person)
            refreshData()
        }


    }

    private fun refreshData()
    {
        lstPersons=db.allPerson
        val adapter=ListPersonAdapter(this@MainActivity,lstPersons,edt_id, edt_name,edt_email)
        list_persons.adapter=adapter
    }
}
