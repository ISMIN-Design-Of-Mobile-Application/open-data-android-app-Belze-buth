package com.ismin.opendataapp

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_park_list.*
import kotlinx.android.synthetic.main.fragment_park_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.Menu
import android.view.MenuItem


class MainActivity : AppCompatActivity(),
    InfoFragment.OnFragmentInteractionListener, ParkListFragment.OnFragmentInteractionListener, MapFragment.OnFragmentInteractionListener {

    private val parkingsList = Data( ArrayList<Parking>())
    private var parkingsList1 = Data( ArrayList<Parking>())
    private val coord=ArrayList<Float>()

    lateinit  var recyclerView : RecyclerView

    private val SERVER_BASE_URL = "https://opendata.nicecotedazur.org/"

    private var TRANSMITT_PARKS_MAP_EXTRA_KEY="2"

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
        parkingsList1=parkingsList
        getDataFromServer()
        setSupportActionBar(toolbar)
        newFrag()


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                getDataFromServer()
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(this, "Mis à jour des données ", Toast.LENGTH_SHORT).show()
                true
            }
            // If we got here, the user's action was not recognized.
            else -> super.onOptionsItemSelected(item)
        }
    }



    override fun onFragmentInteraction(uri: Uri) {
        // TODO Implement
    }

    fun newFrag() {

        val adapter = ViewAdapter(supportFragmentManager)
        adapter.addFragment(ParkListFragment(), "List")
        adapter.addFragment(MapFragment(), "GeMap")
        adapter.addFragment(InfoFragment(), "Info")


        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {


            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {

                if( position==1){
                    //putParkingList(ArrayList<Parking>())
                    putParkingListMap()
                }
                if(position==2){
                    putParkingList(ArrayList<Parking>())
                }
                if(position==0)
                {putParkingList(parkingsList.docs)}

            }

        })
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

    }

    fun putParkingList(list :ArrayList<Parking> ){

        val fragmentList = ParkListFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putSerializable(TRANSMITT_PARKS_EXTRA_KEY, list)
        fragmentList.arguments = bundle
        fragmentTransaction.replace(R.id.a_main_rootview, fragmentList)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction.commit()

    }


    fun putParkingListMap(){

        val fragmentMap = MapFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putSerializable(TRANSMITT_PARKS_MAP_EXTRA_KEY, parkingsList.docs)
        fragmentMap.arguments = bundle
        fragmentTransaction.replace(R.id.a_main_rootview, fragmentMap)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
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
                    parkingsList.docs.clear()
                    parkingsList.docs += allParking!!.docs
                    putParkingList(parkingsList.docs)
                    //putParkingListMap()
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

    public fun partItemClicked(parkItem : Parking) {
        Toast.makeText(this, "Clicked: ${parkItem.NOM}", Toast.LENGTH_LONG).show()
        val showDetailActivityIntent = Intent(this, DetailPark::class.java)
        val extras = Bundle()
        extras.putString("NOM",parkItem.NOM)
        extras.putString("TYPE",parkItem.getType())
        showDetailActivityIntent.putExtras(extras)
        startActivity(showDetailActivityIntent)
    }
}

