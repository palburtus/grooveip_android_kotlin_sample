package com.grooveip.api.sampleapp.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.TextView
import com.grooveip.api.R
import com.grooveip.api.sampleapp.adapters.NumbersRecyclerAdapter
import com.grooveip.api.sampleapp.callbacks.ISelectItemEven
import com.grooveip.api.sampleapp.constants.BundleKeys
import com.grooveip.api.sampleapp.constants.Codes
import com.grooveip.api.sampleapp.extensions.setVisibilityGoneIfVisible
import com.grooveip.api.sampleapp.extensions.setVisibilityVisibleIfNotVisible

/**
 * Created by palburtus on 12/21/17.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mTextViewNoResults:TextView
    private lateinit var mRecyclerView:RecyclerView
    private lateinit var mAdapter:NumbersRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTextViewNoResults = findViewById<TextView>(R.id.no_numbers_text_view)

        mRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        mRecyclerView.layoutManager = LinearLayoutManager(this);
        mAdapter = NumbersRecyclerAdapter(object: ISelectItemEven<String> {
            override fun onSelectItem(item: String) {
                //TODO open some activity that displays this number's details
            }
        })
        mRecyclerView.adapter = mAdapter


        val buttonAdNumber = findViewById<Button>(R.id.add_number_button)
        buttonAdNumber.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivityForResult(intent, Codes.requestCodeGetNumber);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Codes.requestCodeGetNumber && resultCode == Codes.resultCodeGetNumberSuccess){

            val number:String? = if (data != null) data.getStringExtra(BundleKeys.phoneNumber) else null

            if (number != null) {
                addNumber(number)
            }
        }
    }

    private fun addNumber(number: String){

        mTextViewNoResults.setVisibilityGoneIfVisible()
        mRecyclerView.setVisibilityVisibleIfNotVisible()

        mAdapter.mItems.add(number)
        mAdapter.notifyDataSetChanged()
    }
}