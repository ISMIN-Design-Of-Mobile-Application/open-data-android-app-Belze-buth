package com.ismin.opendataapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ParkListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
class ParkListFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null


    lateinit  var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootview = inflater.inflate(R.layout.fragment_park_list, container, false)

        val data = arguments!!.getSerializable(TRANSMITT_PARKS_EXTRA_KEY)as Data

        recyclerView = rootview.findViewById<RecyclerView>(R.id.f_parkList_rcv_parking)


        val adapter = ParkAdapter(data)
        recyclerView.adapter = adapter

        recyclerView.adapter?.notifyDataSetChanged()

        val layourManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layourManager

        return rootview
    }

        // Inflate the layout for this fragment





    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

}
