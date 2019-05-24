package com.rickshory.vegnab.ui

import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rickshory.vegnab.R
import com.rickshory.vegnab.VisitsListOpts
import com.rickshory.vegnab.adapters.VisitsListAdapter
import com.rickshory.vegnab.viewmodels.VNRoomViewModel
import kotlinx.android.synthetic.main.fragment_visits.*
import org.koin.dsl.module.applicationContext

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentVisitsList.VisitsListInterface] interface
 * to handle interaction events.
 * Use the [FragmentVisitsList.newInstance] factory method to
 * create an instance of this fragment.
 */

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_VIS_LIST_OPTS = "visListOpts"

private lateinit var vnRoomViewModel: VNRoomViewModel

private lateinit var vlRecycView: RecyclerView
private lateinit var vlAdapter: RecyclerView.Adapter<*>
private lateinit var viewManager: RecyclerView.LayoutManager

class FragmentVisitsList : Fragment() {
    private val TAG = this::class.java.simpleName
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

        vnRoomViewModel = activity?.let {
            ViewModelProviders.of(this).get(VNRoomViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        vnRoomViewModel.allVis.observe(this, Observer {visits_list ->
            // update the cached copy of visits in the adapter
            visits_list?.let{vlAdapter.setVisits(it) }})
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: starts")
        // Inflate the layout for this fragment
        val fragView = inflater.inflate(R.layout.fragment_visits, container, false)
        vlRecycView = fragView.findViewById<RecyclerView>(R.id.visits_list)


        return fragView
    }


    //    // TODO: Rename method, update argument and hook method into UI event
//    fun onButtonPressed(uri: Uri) {
//        listener?.onGoClicked(uri)
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onAttach: starts")
        super.onViewCreated(view, savedInstanceState)
        vlRecycView.adapter = vlAdapter
        vlRecycView.layoutManager = LinearLayoutManager(context)
    }

    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach: starts")
        super.onAttach(context)
        vlAdapter = VisitsListAdapter(context)


        if (context is VisitsListInterface) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement VisitsListInterface")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // back button is up arrow
        if (listener is AppCompatActivity) {
            val actionbar = (listener as AppCompatActivity?)?.supportActionBar
            actionbar?.setDisplayHomeAsUpEnabled(true)
        }

        fab_new_visit.setOnClickListener { view ->
            //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            Log.d(TAG, "fab_new_visit action: start")
            listener?.visitsListOnGoClicked()
            Log.d(TAG, "fab_new_visit action: exit")
        }
    }

    override fun onDetach() {
        Log.d(TAG, "onDetach: starts")
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
    interface VisitsListInterface {
        // TODO: Update argument type and name
//        fun onGoClicked(uri: Uri)
        fun visitsListOnGoClicked()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param visit The visit to edit, or null to add a new visit
         * @return A new instance of fragment FragmentVisitAddEdit.
         */
        @JvmStatic
        fun newInstance(visitsListOpts: VisitsListOpts?) =
            FragmentVisitsList().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_VIS_LIST_OPTS, visitsListOpts)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }

}