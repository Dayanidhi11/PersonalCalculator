package com.dayanidhi.personalcalculator

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class DBHelper(
    context: Context
) : SQLiteOpenHelper(context, database_Name,null, database_Version) {
    lateinit var db:SQLiteDatabase

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(create_Table)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
    fun insert(calcName:String,expression:String){
        db=writableDatabase
        val contentvalues=ContentValues()
        contentvalues.put(column1,calcName)
        contentvalues.put(column2,expression)
        db.insert(table_Name, null, contentvalues)
        db.close()


    }
    fun showHistory(calcName:String):ArrayList<String>{
        db=readableDatabase
        val cursor: Cursor
        var list=ArrayList<String>()
        val selectionArgs=arrayOf(calcName)
        cursor=db.rawQuery("select * from $table_Name where $column1 =?",selectionArgs )
        if (cursor.moveToFirst()){
            do {
                val expression: String = cursor.getString(1)
               list.add(expression)
            } while (cursor.moveToNext())
        }

        db.close()
        return list

    }
    fun deleteRecords(calcName: String) {
        db = writableDatabase
        val value = arrayOf(calcName)
        db.delete(table_Name,"$column1=?",value)
        db.close()
    }
    companion object {
        private const val database_Name = "HISTORY.DB"
        private const val database_Version = 1
        private const val table_Name = "history"
        private const val column1 = "calculator_name"
        private const val column2 = "expression"
        private const val create_Table =
            "CREATE TABLE $table_Name($column1 TEXT,$column2 TEXT);"
    }

}