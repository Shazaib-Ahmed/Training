package com.example.sampleproject_1.WaterReminder.Dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.sampleproject_1.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog : BottomSheetDialogFragment() {
    private var bottomSheetListener: BottomSheetListener? = null
    var textView100ml: TextView? = null
    var textView200ml: TextView? = null
    var textView300ml: TextView? = null
    var textView400ml: TextView? = null
    var textView500ml: TextView? = null
    var textView800ml: TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.bottom_sheet_layout, container, false)
        textView100ml = v.findViewById(R.id.ml100_relative_layout)
        textView200ml = v.findViewById(R.id.ml200_relative_layout)
        textView300ml = v.findViewById(R.id.ml300_relative_layout)
        textView400ml = v.findViewById(R.id.ml400_relative_layout)
        textView500ml = v.findViewById(R.id.ml500_relative_layout)
        textView800ml = v.findViewById(R.id.ml800_relative_layout)

        textView100ml!!.setOnClickListener(View.OnClickListener {
            Toast.makeText(context!!.applicationContext, "100 ml touch", Toast.LENGTH_SHORT).show()
            dismiss()
        })
        textView200ml!!.setOnClickListener(View.OnClickListener {
            Toast.makeText(context!!.applicationContext, "200 ml touch", Toast.LENGTH_SHORT).show()
            dismiss()
        })
        textView300ml!!.setOnClickListener(View.OnClickListener {
            Toast.makeText(context!!.applicationContext, "300 ml touch", Toast.LENGTH_SHORT).show()
            dismiss()
        })
        textView400ml!!.setOnClickListener(View.OnClickListener {
            Toast.makeText(context!!.applicationContext, "400 ml touch", Toast.LENGTH_SHORT).show()
            dismiss()
        })
        textView500ml!!.setOnClickListener(View.OnClickListener {
            Toast.makeText(context!!.applicationContext, "500 ml touch", Toast.LENGTH_SHORT).show()
            dismiss()
        })
        textView800ml!!.setOnClickListener(View.OnClickListener {
            Toast.makeText(context!!.applicationContext, "800 ml touch", Toast.LENGTH_SHORT).show()
            dismiss()
        })
        return v
    }

    interface BottomSheetListener {
        fun onButtonClicked(text: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bottomSheetListener = try {
            context as BottomSheetListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString()
                    + " must implement BottomSheetListener")
        }
    }
}