package com.grooveip.sampleapp.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.grooveip.R
import com.grooveip.sampleapp.adapters.NumbersRecyclerAdapter

/**
 * Created by palburtus on 12/21/17.
 */
class SearchActivity : AppCompatActivity() {

    private lateinit var mRecyclerView:RecyclerView;
    private lateinit var mAdapter:NumbersRecyclerAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRecyclerView = findViewById(R.id.recycler_view)
        mRecyclerView.layoutManager = LinearLayoutManager(this);
        mAdapter = NumbersRecyclerAdapter()
        mAdapter.addItems(getNumbers())
        mRecyclerView.adapter = mAdapter
    }

    fun getNumbers(): MutableList<String>{

        var numbers = mutableListOf<String>()

        numbers.add("732-988-1021")
        numbers.add("845-989-1022")
        numbers.add("917-102-4567")

        return numbers;
    }
}