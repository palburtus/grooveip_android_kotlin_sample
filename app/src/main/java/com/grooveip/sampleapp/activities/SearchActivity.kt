package com.grooveip.sampleapp.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import com.grooveip.R
import com.grooveip.sampleapp.adapters.NumbersRecyclerAdapter
import com.grooveip.sdk.api.ApiClient
import com.grooveip.sampleapp.callbacks.ICallbackEvent
import com.grooveip.sampleapp.callbacks.ISelectItemEven
import com.grooveip.sampleapp.constants.BundleKeys
import com.grooveip.sampleapp.constants.Codes
import com.grooveip.sdk.extensions.toastShort
import com.grooveip.sdk.parsers.JsonStringListParser
import com.grooveip.sdk.tasks.HttpGetTask

/**
 * Created by palburtus on 12/21/17.
 */
class SearchActivity : AppCompatActivity() {

    private lateinit var mRecyclerView:RecyclerView;
    private lateinit var mAdapter:NumbersRecyclerAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mRecyclerView = findViewById(R.id.recycler_view)
        mRecyclerView.layoutManager = LinearLayoutManager(this);
        mAdapter = NumbersRecyclerAdapter(object: ISelectItemEven<String>{
            override fun onSelectItem(item: String) {
                val intent = Intent()
                intent.putExtra(BundleKeys.url, item)
                setResult(Codes.resultCodeGetNumberSuccess, intent)
                finish()
            }
        })
        mRecyclerView.adapter = mAdapter

        var areaCodeEditText = findViewById<EditText>(R.id.edit_text_area_code)
        var searchNumbersButton = findViewById<Button>(R.id.button_search_numbers)
        searchNumbersButton.setOnClickListener {
            val areaCode = areaCodeEditText.text.toString()

            if(areaCode.length == 3) {
                getNumbers(areaCode);
            }else{
                runOnUiThread({
                    applicationContext.toastShort("Area code must be three numbers")
                })
            }
        }

    }

    private fun getNumbers(areaCode: String){

        val task = HttpGetTask(object: ICallbackEvent<String, Exception>{

            override fun onSuccess(obj: String) {

                val parser = JsonStringListParser(obj)
                val numbers = parser.parser()

                mAdapter.setItems(numbers)
                mAdapter.notifyDataSetChanged()
            }

            override fun onError(obj: Exception) {
                runOnUiThread({
                    applicationContext.toastShort("Error searching for numbers")
                })
            }
        })
        task.execute(ApiClient.buildSearchNumbersUrl(areaCode))
    }
}