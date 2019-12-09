package com.ismin.opendataapp

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MapFragment.OnFragmentInteractionListener,
    InfoFragment.OnFragmentInteractionListener, ParkListFragment.OnFragmentInteractionListener {

    val data = ArrayList<Parking>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        newFrag()
    }
    override fun onFragmentInteraction(uri: Uri) {
        // TODO Implement
    }

    fun newFrag () {
        val fragmentList = ParkListFragment()

        val adapter = ViewAdapter(supportFragmentManager)
        adapter.addFragment(ParkListFragment(), "List")
        adapter.addFragment(MapFragment(), "GeMap")
        adapter.addFragment(InfoFragment(), "Info")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        val bundle = Bundle()
        bundle.putSerializable(TRANSMITT_PARKS_EXTRA_KEY, data)

        fragmentList.arguments = bundle

    }

}
