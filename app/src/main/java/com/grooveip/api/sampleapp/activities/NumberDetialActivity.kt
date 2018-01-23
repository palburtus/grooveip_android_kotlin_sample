package com.grooveip.api.sampleapp.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.grooveip.api.R
import com.grooveip.api.sampleapp.constants.BundleKeys
import com.grooveip.api.sampleapp.extensions.formatPhoneNumber
import com.grooveip.api.sdk.model.ReserveNumberResponse
import com.grooveip.api.sdk.model.Sip
import android.graphics.Typeface
import android.text.style.StyleSpan
import android.text.SpannableStringBuilder
import android.view.MenuItem
import java.util.*


/**
 * Created by Patrick on 1/22/2018.
 */
class NumberDetialActivity : AppCompatActivity() {

    private lateinit var mTextViewPhoneNumber:TextView
    private lateinit var mTextViewNumberDetails:TextView
    private lateinit var mTextViewSipDetails:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_detail)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        mTextViewPhoneNumber = findViewById(R.id.text_view_phone_number)
        mTextViewNumberDetails = findViewById(R.id.text_view_number_details)
        mTextViewSipDetails = findViewById(R.id.text_view_sip_details)

        var reservedNumber = intent.getParcelableExtra<ReserveNumberResponse>(BundleKeys.reserveNumberResponse)

        supportActionBar!!.title = reservedNumber.phoneNumber.formatPhoneNumber()
        mTextViewPhoneNumber.text = reservedNumber.phoneNumber.formatPhoneNumber()
        setNumberDetailsTextView(reservedNumber)
        setSipDetailsTextView(reservedNumber.sip)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item != null) {
            if(item.itemId == android.R.id.home){
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setNumberDetailsTextView(reservedNumber: ReserveNumberResponse){
        mTextViewNumberDetails.text = "User ID : ${reservedNumber.userId}\nCreation Date : ${reservedNumber.creationDate}"
    }

    private fun setSipDetailsTextView(sip: Sip){
        mTextViewSipDetails.text = "Sip ID : ${sip.id}\nSip User Name : ${sip.userName}\nRealm : ${sip.realm}\nEndpoint Id : ${sip.endpointId}"
    }
}