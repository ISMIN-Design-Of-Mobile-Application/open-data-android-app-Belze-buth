package com.ismin.opendataapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [InfoFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */

class MapFragment : Fragment(), OnMapReadyCallback{

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var mMap: GoogleMap
    private lateinit var data: ArrayList<Parking>

    lateinit var positions : ArrayList<Geometry>

    override fun onStart() {
        super.onStart()
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //recupere les datas
        val serializableData = arguments?.getSerializable(TRANSMITT_PARKS_EXTRA_KEY)
        if (serializableData !=null){
            data = serializableData as ArrayList<Parking>
            for (el in data){
                positions.add(el.getPosition())
            }

        }


        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MapFragment.OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Niceand move the camera
        val nice = LatLng(7.2, 43.6)
        mMap.addMarker(MarkerOptions().position(nice).title("Marker in Nice"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nice))
        addItemsOnMap()
    }

    fun addItemsOnMap() {
        for ((name,coord) in positions) {
            val loc = LatLng(coord[0].toDouble(), coord[1].toDouble())
            mMap.addMarker(MarkerOptions().position(loc).title(name)).tag
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)

    }
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
}
