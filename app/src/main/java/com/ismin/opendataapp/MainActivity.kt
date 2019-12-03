package com.ismin.opendataapp

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MapFragment.OnFragmentInteractionListener,
    InfoFragment.OnFragmentInteractionListener, ParkListFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val adapter = ViewAdapter(supportFragmentManager)
        adapter.addFragment(ParkListFragment(), "List")
        adapter.addFragment(MapFragment(), "GeMap")
        adapter.addFragment(InfoFragment(), "Info")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
    override fun onFragmentInteraction(uri: Uri) {
        // TODO Implement
    }
}
