package com.dayanidhi.personalcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView


class HistoryActivity : AppCompatActivity() {
    lateinit var dbHelper: DBHelper
    lateinit var btnHistory:Button
    lateinit var lvHistory:ListView
    lateinit var adapter:ArrayAdapter<String>
    lateinit var calcName:String
    lateinit var list:ArrayList<String>
    val EmptyList= arrayOf("There is no history")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        btnHistory=findViewById(R.id.btnHistory1)
        lvHistory=findViewById(R.id.lvHistory)
        dbHelper=DBHelper(this)
        calcName=intent.getStringExtra("calcName")
        list=dbHelper.showHistory(calcName)

        adapter=if (!list.isEmpty()){
            ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                list
            )
        } else{

            ArrayAdapter(this, android.R.layout.simple_list_item_1, EmptyList)
        }
        lvHistory.setAdapter(adapter)


    }
    fun onClick(v: View?) {
        dbHelper.deleteRecords(calcName)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, EmptyList)
        lvHistory.setAdapter(adapter)
    }
}