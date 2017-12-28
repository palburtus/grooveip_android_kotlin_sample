package com.grooveip.sampleapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.grooveip.R
import com.grooveip.sampleapp.callbacks.ISelectItemEven
import com.grooveip.sampleapp.extensions.inflate
import com.grooveip.sampleapp.viewholders.NumberViewHolder

/**
 * Created by palburtus on 12/21/17.
 */
class NumbersRecyclerAdapter(private val selectItemEvent: ISelectItemEven<String>) : RecyclerView.Adapter<NumberViewHolder>() {

    var mItems = mutableListOf<String>();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val view = parent.inflate(R.layout.viewholder_number);
        return NumberViewHolder(view, selectItemEvent);
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(mItems.get(position))
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun addItems(items:List<String>){
        mItems.addAll(items)
    }

    fun setItems(items:List<String>){
        mItems.clear()
        mItems.addAll(items)
    }
}