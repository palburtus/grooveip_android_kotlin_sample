package com.grooveip.sampleapp.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.grooveip.R
import com.grooveip.sampleapp.callbacks.ISelectItemEven
import kotlinx.android.synthetic.main.viewholder_number.view.*

/**
 * Created by palburtus on 12/21/17.
 */
class NumberViewHolder(item:View, selectItemEvent: ISelectItemEven<String>) : RecyclerView.ViewHolder(item), View.OnClickListener {

    private var mRootView: View = item;
    private var mSelectItemEvent: ISelectItemEven<String> = selectItemEvent
    private lateinit var mNumberTextView: TextView

    init {
        item.setOnClickListener(this)
        mNumberTextView = mRootView.findViewById<TextView>(R.id.number_text_view)
    }

    fun bind(number:String){

        mNumberTextView.text = number
    }

    override fun onClick(view: View) {
        mSelectItemEvent.onSelectItem(mNumberTextView.text.toString())
    }
}