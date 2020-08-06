package com.dayanidhi.personalcalculator

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import io.kaen.dagger.ExpressionParser
import kotlinx.android.synthetic.main.activity_scientific_cal.*


class StandardCalActivity : AppCompatActivity() {
    lateinit var etInput: EditText
    lateinit var tvResult: TextView
    lateinit var dbHelper:DBHelper




   // @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standard_cal)
        etInput = findViewById(R.id.etInput)
        tvResult = findViewById(R.id.tvResult)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
            etInput.setShowSoftInputOnFocus(false);
        } else { // API 11-20
            etInput.setTextIsSelectable(true);
        }
       dbHelper= DBHelper(this)


    }


    fun onClick(v: View) {
        when (v.id) {


            R.id.btn0 -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "0")
            }
            R.id.btn1 -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "1")
            }
            R.id.btn2 -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "2")
            }
            R.id.btn3 -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "3")
            }
            R.id.btn4 -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "4")
            }
            R.id.btn5 -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "5")
            }
            R.id.btn6 -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "6")
            }
            R.id.btn7 -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "7")
            }
            R.id.btn8 -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "8")
            }
            R.id.btn9 -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "9")
            }


            R.id.btnPoint -> {
                var position = etInput.selectionStart
                if (etInput.length() != 0) {
                    etInput.text.insert(position, ".")
                } else {
                    etInput.setText("0.")
                    etInput.setSelection(position+2)
                }
            }
            R.id.btnBrkt -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "()")
                etInput.setSelection(position + 1)

            }

            R.id.btnBackspace -> {
                var position = etInput.selectionStart
                var input = etInput.text.toString()
                var s1 = input.subSequence(0, position - 1)
                var s2 = input.subSequence(position, input.length)
                etInput.setText("$s1$s2")
                etInput.setSelection(position - 1)

            }
            R.id.btnC -> {
                etInput.setText("")
                tvResult.setText("")
            }
            R.id.btnAdd -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "+")

            }
            R.id.btnMinus -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "-")
            }
            R.id.btnDivide -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "/")
            }
            R.id.btnMul -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "*")
            }
            R.id.btnSqRoot -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "sqrt()")
                etInput.setSelection(position + 5)
            }
            R.id.btnHistory1-> {
                val i = Intent(this, HistoryActivity::class.java)
                i.putExtra("calcName", "STANDARD")
                startActivity(i)
            }

            R.id.btnEqual -> {
                val expression=etInput.text.toString()
                val parser = ExpressionParser()

                var result:String="0.0"
                try {

                    result = parser.evaluate(expression,8).toString()
                    tvResult.setText(result)
                    dbHelper.insert("STANDARD","$expression = $result")
                }catch (e:Exception){
                    Toast.makeText(this@StandardCalActivity,"Expression may be Invalid!! Please Check Again",Toast.LENGTH_SHORT).show()
                }



            }


        }


    }


}
