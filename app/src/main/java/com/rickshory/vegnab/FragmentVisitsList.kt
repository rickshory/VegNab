package com.rickshory.vegnab

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentVisitsList.VisitsListInterface] interface
 * to handle interaction events.
 * Use the [FragmentVisitsList.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "FragmentVisitsList"
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_VIS_LIST_OPTS = "visListOpts"

class FragmentVisitsList : Fragment() {
    private var visitsListOpts: VisitsListOpts? = null
    //    private var param2: String? = null
    private var listener: VisitsListInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: starts")
        super.onCreate(savedInstanceState)
        visitsListOpts = arguments?.getParcelable(ARG_VIS_LIST_OPTS)
//        arguments?.let {
//            visit = it.getString(ARG_VISIT)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: starts")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visits, container, false)
    }
}