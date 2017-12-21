package com.wbmd.grooveip.sampleapp.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.wbmd.grooveip.R
import com.wbmd.grooveip.sampleapp.constants.Codes

/**
 * Created by palburtus on 12/21/17.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewNoResults = findViewById<TextView>(R.id.no_numbers_text_view);
        val buttonAdNumber = findViewById<Button>(R.id.add_number_button);
        buttonAdNumber.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivityForResult(intent, Codes.requestCodeGetNumber);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Codes.requestCodeGetNumber && resultCode == Codes.resultCodeGetNumberSuccess){

        }
    }
}