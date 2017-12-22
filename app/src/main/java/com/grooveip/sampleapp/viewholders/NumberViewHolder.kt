package com.grooveip.sampleapp.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.grooveip.R
import kotlinx.android.synthetic.main.viewholder_number.view.*

/**
 * Created by palburtus on 12/21/17.
 */
class NumberViewHolder(item:View) : RecyclerView.ViewHolder(item), View.OnClickListener {

    private var mRootView: View = item;

    init {
        item.setOnClickListener(this)
    }

    fun bind(number:String){
        var numberTextView = mRootView.findViewById<TextView>(R.id.number_text_view)
        numberTextView.text = number
    }

    override fun onClick(view: View) {

    }
}