package com.grooveip.api.sampleapp.activities

import android.app.ProgressDialog
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
import com.grooveip.api.sampleapp.extensions.getStringResource
import com.grooveip.api.sampleapp.extensions.setVisibilityGoneIfVisible
import com.grooveip.api.sampleapp.extensions.setVisibilityVisibleIfNotVisible
import com.grooveip.api.sdk.model.ReserveNumberResponse
import java.util.*

/**
 * Created by palburtus on 12/21/17.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mTextViewNoResults:TextView
    private lateinit var mRecyclerView:RecyclerView
    private lateinit var mAdapter:NumbersRecyclerAdapter
    private lateinit var mButtonAddNumber:Button;

    private var mNumberResponseMap = mutableMapOf<String, ReserveNumberResponse>()

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

        fetchNumbers()

        mButtonAddNumber = findViewById<Button>(R.id.add_number_button)
        mButtonAddNumber.setOnClickListener {
            mButtonAddNumber.isEnabled = false
            val intent = Intent(this, SearchActivity::class.java)
            startActivityForResult(intent, Codes.requestCodeGetNumber);
        }
    }

    override fun onResume() {
        super.onResume()
        mButtonAddNumber.isEnabled = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Codes.requestCodeGetNumber && resultCode == Codes.resultCodeGetNumberSuccess){

            val response:ReserveNumberResponse? = if (data != null) data.getParcelableExtra<ReserveNumberResponse>(BundleKeys.reserveNumberResponse) else null

            if (response != null) {
                addNumber(response)
            }
        }
    }

    private fun fetchNumbers(){

        var progressDialog = ProgressDialog.show(this,
                getStringResource(R.string.fetching_numbers_dialog_title), getStringResource(R.string.fetching_numbers_dialog_message), true)

        progressDialog.dismiss()
    }

    private fun addNumber(response: ReserveNumberResponse){

        mNumberResponseMap.put(response.phoneNumber, response)

        mTextViewNoResults.setVisibilityGoneIfVisible()
        mRecyclerView.setVisibilityVisibleIfNotVisible()

        mAdapter.mItems.add(response.phoneNumber)
        mAdapter.notifyDataSetChanged()
    }
}