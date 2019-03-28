package com.rickshory.vegnab

import androidx.fragment.app.Fragment
import android.os.Bundle
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
//VisitsListOpts
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_visits, container, false)
    }
}