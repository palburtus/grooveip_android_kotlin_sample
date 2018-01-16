package com.grooveip.api.sampleapp.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.grooveip.api.R
import com.grooveip.api.sampleapp.adapters.NumbersRecyclerAdapter
import com.grooveip.api.sdk.api.ApiClient
import com.grooveip.api.sampleapp.callbacks.ICallbackEvent
import com.grooveip.api.sampleapp.callbacks.ISelectItemEven
import com.grooveip.api.sampleapp.constants.BundleKeys
import com.grooveip.api.sampleapp.constants.Codes
import com.grooveip.api.sdk.extensions.toastShort
import com.grooveip.api.sdk.parsers.JsonStringListParser
import com.grooveip.api.sdk.tasks.HttpGetTask

/**
 * Created by palburtus on 12/21/17.
 */
class SearchActivity : AppCompatActivity() {

    private lateinit var mLastSearchedAreaCode:String
    private lateinit var mRecyclerView:RecyclerView
    private lateinit var mAdapter:NumbersRecyclerAdapter
    private lateinit var mProgresBar:ProgressBar
    private lateinit var mSearchNumbersButton:Button
    private lateinit var mAreaCodeEditText:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mRecyclerView = findViewById(R.id.recycler_view)
        mSearchNumbersButton = findViewById<Button>(R.id.button_search_numbers)
        mAreaCodeEditText = findViewById<EditText>(R.id.edit_text_area_code)
        mProgresBar = findViewById<ProgressBar>(R.id.progress_bar)

        mRecyclerView.layoutManager = LinearLayoutManager(this);
        mAdapter = NumbersRecyclerAdapter(object: ISelectItemEven<String>{
            override fun onSelectItem(item: String) {

                val task = HttpGetTask(object: ICallbackEvent<String, Exception>{

                    override fun onSuccess(obj: String) {

                        val intent = Intent()
                        intent.putExtra(BundleKeys.phoneNumber, item)
                        setResult(Codes.resultCodeGetNumberSuccess, intent)
                        finish()
                    }

                    override fun onError(obj: Exception) {
                        runOnUiThread({
                            mProgresBar.visibility = ProgressBar.GONE;
                            mRecyclerView.isEnabled = true
                            mAreaCodeEditText.isEnabled = true
                            applicationContext.toastShort("Error reserving number")
                        })
                    }
                })

                val apiRequest = ApiClient.buildSelectNumberRequest(item, mLastSearchedAreaCode);

                task.execute(apiRequest.url, buildReserveNumberJson(apiRequest.body))
            }
        })

        mRecyclerView.adapter = mAdapter

        mSearchNumbersButton.setOnClickListener {
            val areaCode = mAreaCodeEditText.text.toString()

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
        mProgresBar.visibility = ProgressBar.VISIBLE;
        mRecyclerView.isEnabled = false
        mAreaCodeEditText.isEnabled = false


        mLastSearchedAreaCode = areaCode

        val task = HttpGetTask(object: ICallbackEvent<String, Exception>{

            override fun onSuccess(obj: String) {

                val parser = JsonStringListParser(obj)
                val numbers = parser.parser()
                mProgresBar.visibility = ProgressBar.GONE;
                mRecyclerView.isEnabled = true
                mAreaCodeEditText.isEnabled = true
                mAdapter.setItems(numbers)
                mAdapter.notifyDataSetChanged()
            }

            override fun onError(obj: Exception) {
                runOnUiThread({
                    mProgresBar.visibility = ProgressBar.GONE;
                    mRecyclerView.isEnabled = true
                    mAreaCodeEditText.isEnabled = true
                    applicationContext.toastShort("Error searching for numbers")
                })
            }
        })
        task.execute(ApiClient.buildSearchNumbersUrl(areaCode))
    }

    private fun buildReserveNumberJson(params: Array<String>) : String{
        var sb = StringBuilder()

        sb.append("{")

        sb.append("\"CustomerId\":")
        sb.append(params[0])
        sb.append("\" ,")

        sb.append("\"PhoneNumber\":")
        sb.append(params[1])
        sb.append("\" ,")

        sb.append("\"AreaCode\":")
        sb.append(params[2])
        sb.append("\" ,")

        sb.append("\"RequestId\":")
        sb.append(params[3])
        sb.append("\" ,")

        sb.append("\"Hash\":")
        sb.append(params[4])
        sb.append("\"")

        sb.append("}")

        return sb.toString()
    }
}