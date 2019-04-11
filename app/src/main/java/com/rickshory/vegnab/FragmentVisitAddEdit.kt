package com.rickshory.vegnab

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_visit_add_edit.*
import kotlinx.android.synthetic.main.fragment_visit_add_edit.view.*

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
class FragmentVisitAddEdit : Fragment(),
    android.view.View.OnFocusChangeListener {

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterTextChanged.invoke(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })
    }

    fun EditText.autoStore(tryToSaveVisitRecord: () -> Unit) {
        Log.d(TAG, "autoStore: starts")
        this.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d(TAG, "about to call saveVisit: $s")
                saveVisit()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })
    }
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
        val view = inflater.inflate(R.layout.fragment_visit_add_edit, container, false)

        //        vae_inp_name.autoStore()
        view.vae_inp_name.addTextChangedListener(object :TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { }
            //override fun beforeTextChanged(s: Editable?) { }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { }
            //override fun onTextChanged(s: Editable?) { }
            override fun afterTextChanged(s: Editable?) {
                saveVisit()
//                val content = s?.toString()
//                s?.error = if (content.length >= 6) null else "Minimum length = 6"

            }
        })
        return view
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

    /**
     * Called when the focus state of a view has changed.
     *
     * @param v The view whose state has changed.
     * @param hasFocus The new focus state of v.
     */
    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (!hasFocus) { // something lost focus
//            val values = ContentValues() // use later
            when (v?.id) {
                vae_inp_name.id -> {

                }
                vae_inp_location.id -> {

                }
                vae_inp_notes.id -> {

                }
            }
        }
    }
    private fun saveVisit() {
        // update record if at least one field has changed
        val values = ContentValues()
        val visit = visit
        if (visit == null) {
            Log.d(TAG, "saveVisit: adding new visit")
            if (vae_inp_name.text.isNotEmpty() && vae_inp_location.text.isNotEmpty()) {
                // improve the following validity tests
                if (vae_inp_name.text.toString().length >= Contract_Visits.Settings.VISIT_NAME_MIN_LENGTH) {
                    values.put(Contract_Visits.Columns.VISIT_NAME, vae_inp_name.text.toString())
                    values.put(Contract_Visits.Columns.VISIT_LOCATION, vae_inp_location.text.toString())
                    if (vae_inp_notes.text.isNotEmpty()) {
                        values.put(Contract_Visits.Columns.VISIT_NOTES, vae_inp_notes.text.toString())
                    }
                    // put defaults, such as timestamps, here
                    Log.d(TAG, "saveVisit: creating new record")
                    activity?.contentResolver?.insert(Contract_Visits.CONTENT_URI, values)
                } // end of validation
            }
        } else {
            Log.d(TAG, "saveVisit: updating existing visit")
            if (vae_inp_name.text.toString() != visit.name) {
                values.put(Contract_Visits.Columns.VISIT_NAME, vae_inp_name.text.toString())
            }
            if (vae_inp_location.text.toString() != visit.location) {
                values.put(Contract_Visits.Columns.VISIT_LOCATION, vae_inp_location.text.toString())
            }
            if (vae_inp_notes.text.toString() != visit.notes) {
                values.put(Contract_Visits.Columns.VISIT_NOTES, vae_inp_notes.text.toString())
            }
            if (values.size() != 0) {
                Log.d(TAG, "saveVisit: updating record")
                activity?.contentResolver?.update(Contract_Visits.buildUriFromId(visit.id),
                    values, null, null)
            }
        }
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

private fun EditText.addTextChangedListener(textWatcher: TextWatcher, function: () -> Unit) {

}

fun createFrag(visit: Visit) {
    val fragment = FragmentVisitAddEdit.newInstance(visit)
}
