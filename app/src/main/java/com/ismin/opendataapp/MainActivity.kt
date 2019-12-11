package com.ismin.opendataapp

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_park_list.*
import kotlinx.android.synthetic.main.fragment_park_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), MapFragment.OnFragmentInteractionListener,
    InfoFragment.OnFragmentInteractionListener, ParkListFragment.OnFragmentInteractionListener {

    private val parkingsList = Data( ArrayList<Parking>())
    private val coord=ArrayList<Float>()



    private val SERVER_BASE_URL = "https://opendata.nicecotedazur.org/"
    private var PARKING_ARGUMENTS_KEY = "PARKING_ARGUMENTS_KEY"


    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()

    val dataService = retrofit.create<ParkingService>(ParkingService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        coord.add(0.2f)
        coord.add(0.2f)
        val parking =  Parking(Geometry("point", coord),"parking1", "auto",1)
        parkingsList.docs.add(parking)

        getDataFromServer()
        setSupportActionBar(toolbar)
        putParkingList()
        newFrag()
    }

    override fun onFragmentInteraction(uri: Uri) {
        // TODO Implement
    }

    fun newFrag() {

        val adapter = ViewAdapter(supportFragmentManager)
        adapter.addFragment(ParkListFragment(), "List")
        adapter.addFragment(MapFragment(), "GeMap")
        adapter.addFragment(InfoFragment(), "Info")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

    }

    fun putParkingList(){

        val fragmentList = ParkListFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putSerializable(TRANSMITT_PARKS_EXTRA_KEY, parkingsList.docs)
        fragmentList.arguments = bundle
        fragmentTransaction.replace(R.id.a_main_rootview, fragmentList)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        fragmentTransaction.commit()


    }
    fun getDataFromServer() {

        dataService.getData()
            .enqueue(object : Callback<Data> {
                override fun onResponse(
                    call: Call<Data>,
                    response: Response<Data>
                ) {
                    val allParking = response.body()
                    parkingsList.docs += allParking!!.docs
                    putParkingList()
                    Log.v(
                        "message",
                        allParking!!.docs.size.toString() + "heysdjkfhksdjFSJKSDNFSDQFNSNCSN VSN?DSN?DSN???????????yyyyyyyyyyy"
                    )
// DO THIN
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                    Log.v("message", t.toString())
                }
            })
    }


}


