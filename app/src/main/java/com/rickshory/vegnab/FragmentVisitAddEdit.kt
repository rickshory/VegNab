package com.rickshory.vegnab

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_visit_add_edit.*

private const val TAG = "FragmentVisitAddEdit"
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_VISIT = "visit"
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentVisitAddEdit.VisitHeaderInterface] interface
 * to handle interaction events.
 * Use the [FragmentVisitAddEdit.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FragmentVisitAddEdit : Fragment() {
    private var visit: Visit? = null
//    private var param2: String? = null
    private var listener: VisitHeaderInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: starts")
        super.onCreate(savedInstanceState)
        visit = arguments?.getParcelable(ARG_VISIT)
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
        return inflater.inflate(R.layout.fragment_visit_add_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: starts")
        if (savedInstanceState == null) {
            val visit = visit // copy to local variable
            if (visit != null) {
                Log.d(TAG, "onViewCreated: editing Visit, ${visit.id}")
                vae_inp_name.setText(visit.name)
                vae_inp_notes.setText(visit.notes)
                vae_inp_location.setText(visit.location)
            } else {
                // no visit, so adding/edition a new one
                Log.d(TAG, "onViewCreated: no args, adding new rec")
            }
        }
    }

    private fun saveVisit() {
        // update record if at least one field has changed
        val values = ContentValues()
        val visit = visit
        
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    fun onButtonPressed(uri: Uri) {
//        listener?.onGoClicked(uri)
//    }

    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach: starts")
        super.onAttach(context)
        if (context is VisitHeaderInterface) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement VisitHeaderInterface")
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
    interface VisitHeaderInterface {
        // TODO: Update argument type and name
//        fun onGoClicked(uri: Uri)
        fun visHeaderOnGoClicked()
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
        fun newInstance(visit: Visit?) =
            FragmentVisitAddEdit().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_VISIT, visit)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

fun createFrag(visit: Visit) {
    val fragment = FragmentVisitAddEdit.newInstance(visit)
}
