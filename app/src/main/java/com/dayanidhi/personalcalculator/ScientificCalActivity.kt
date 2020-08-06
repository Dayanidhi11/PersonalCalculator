package com.dayanidhi.personalcalculator

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import io.kaen.dagger.ExpressionParser
import kotlinx.android.synthetic.main.activity_scientific_cal.*


var switchMode = 1


class ScientificCalActivity : AppCompatActivity() {
    lateinit var  dbHelper:DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scientific_cal)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
            etInput.setShowSoftInputOnFocus(false);
        } else { // API 11-20
            etInput.setTextIsSelectable(true);
        }
        dbHelper= DBHelper(this)

    }

    fun onClick(v: View) {


        when (v.id) {
            R.id.btnSwitch -> {
                when (switchMode) {
                    1 -> {
                        switchMode = 2
                        btnXPower2.setText(R.string.cube)
                        btnXPowerN.setText(R.string.tenpow)
                        btnLog.setText("ln")
                        btnSin.setText(R.string.sininv)
                        btnCos.setText(R.string.cosinv)
                        btnTan.setText(R.string.taninv)
                        btnSqrt.setText(R.string.cuberoot)
                        btnFactorial.setText(R.string.Mod)


                    }
                    2 -> {
                        switchMode = 3
                        btnXPower2.setText("x²")
                        btnXPowerN.setText(R.string.epown)
                        btnLog.setText("log")
                        btnSin.setText("sinh")
                        btnCos.setText("cosh")
                        btnTan.setText("tanh")
                        btnSqrt.setText("1/x")
                        btnFactorial.setText("x!")

                    }
                    else -> {
                        switchMode = 1
                        btnXPower2.setText("x²")
                        btnXPowerN.setText(R.string.xpown)
                        btnSin.setText("sin")
                        btnCos.setText("cos")
                        btnTan.setText("tan")
                        btnSqrt.setText(R.string.sqrt)


                    }
                }
            }

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
                if (position!=0){
                    var input = etInput.text.toString()
                    var s1 = input.subSequence(0, position - 1)
                    var s2 = input.subSequence(position, input.length)
                    etInput.setText("$s1$s2")
                    etInput.setSelection(position - 1)
                }


            }
            R.id.btnC -> {
                etInput.setText("")
                tvResult.setText("")
            }
            R.id.btnPlus -> {
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
            R.id.btnPi -> {
                var position = etInput.selectionStart
                etInput.text.insert(position, "PI")

            }
            R.id.btnXPower2 -> {
                var text = etInput.text.toString()
                var position = etInput.selectionStart
                var s1 = text.subSequence(0, position)
                var s2 = text.subSequence(position, text.length)
                if (switchMode == 1 || switchMode == 3) {

                    etInput.setText("${s1}^2${s2}")
                } else if (switchMode == 2) {
                    etInput.setText("${s1}^3${s2}")
                }
                etInput.setSelection(position+s2.length)

            }
            R.id.btnXPowerN -> {
                var text = etInput.text
                var position = etInput.selectionStart
                if (switchMode == 1) {
                    etInput.setText("${text}^")
                    etInput.setSelection(position+1)

                } else if (switchMode == 2) {
                    etInput.setText("${text}(10^)")
                    etInput.setSelection(position+4)

                } else {
                    etInput.setText("${text}(e^)")
                    etInput.setSelection(position+3)

                }
            }
            R.id.btnLog -> {
                var text = etInput.text
                var position = etInput.selectionStart
                if (switchMode == 1 || switchMode == 3) {
                    etInput.setText("${text}log()")
                    etInput.setSelection(position+4)

                } else if (switchMode == 2) {
                    etInput.setText("${text}ln()")
                    etInput.setSelection(position+3)

                }
            }
            R.id.btnFactorial -> {
                var text = etInput.text
                var position = etInput.selectionStart
                if (switchMode == 1 || switchMode == 3) {
                    etInput.setText("${text}!")

                } else if (switchMode == 2) {
                    etInput.setText("${text}%")

                }
                etInput.setSelection(position+1)
            }
            R.id.btnSin -> {
                var text = etInput.text
                var position = etInput.selectionStart
                if (switchMode == 1) {
                    etInput.setText("${text}sin()")
                    etInput.setSelection(position+4)

                } else if (switchMode == 2) {
                    etInput.setText("${text}asin()")
                    etInput.setSelection(position+5)


                } else {
                    etInput.setText("${text}sinh()")
                    etInput.setSelection(position+5)


                }
            }
            R.id.btnCos -> {
                var text = etInput.text
                var position = etInput.selectionStart
                if (switchMode == 1) {
                    etInput.setText("${text}cos()")
                    etInput.setSelection(position+4)

                } else if (switchMode == 2) {
                    etInput.setText("${text}acos()")
                    etInput.setSelection(position+5)


                } else {
                    etInput.setText("${text}cosh()")
                    etInput.setSelection(position+5)


                }
            }
            R.id.btnTan -> {
                var text = etInput.text
                var position = etInput.selectionStart
                if (switchMode == 1) {
                    etInput.setText("${text}tan()")
                    etInput.setSelection(position+4)

                } else if (switchMode == 2) {
                    etInput.setText("${text}atan()")
                    etInput.setSelection(position+5)


                } else {
                    etInput.setText("${text}tanh()")
                    etInput.setSelection(position+5)


                }
            }
            R.id.btnSqrt -> {
                var text = etInput.text
                var position = etInput.selectionStart
                if (switchMode == 1) {
                    etInput.setText("${text}sqrt()")
                    etInput.setSelection(position+5)

                } else if (switchMode == 2) {
                    etInput.setText("${text}^(1/3)")
                    etInput.setSelection(position+5)


                } else {
                    etInput.setText("${text}(1/)")
                    etInput.setSelection(position+3)


                }

            }
            R.id.btnHistory2->{

                    val i = Intent(this, HistoryActivity::class.java)
                    i.putExtra("calcName", "SCIENTIFIC")
                    startActivity(i)

            }
            R.id.btnEqual -> {
                val expression=etInput.text.toString()
                val parser = ExpressionParser()

                var result:String="0.0"
                try {

                     result = parser.evaluate(expression,8).toString()
                    tvResult.setText(result)
                    dbHelper.insert("SCIENTIFIC","$expression = $result")
                }catch (e:Exception){
                    Toast.makeText(this@ScientificCalActivity,"Expression may be Invalid!! Please Check Again",Toast.LENGTH_SHORT).show()
                }




            }


        }

    }
}


