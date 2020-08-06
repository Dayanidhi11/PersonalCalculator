package com.dayanidhi.personalcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.kaen.dagger.ExpressionParser


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    fun onClick(view: View) {
        val i: Intent
        if (view.id === R.id.btnStandardCalculator) {
            i = Intent(this, StandardCalActivity::class.java)
            startActivity(i)
        } else if (view.id === R.id.btnScientificCalculator) {
            i = Intent(this, ScientificCalActivity::class.java)
            startActivity(i)
        }
    }


}