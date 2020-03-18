package com.example.financas.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financas.R
import com.example.financas.enums.MovimentType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_moviment_type.*

class MovimentTypeDialog : BottomSheetDialogFragment() {

    var listener: MovimentTypeListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_moviment_type, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        options.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = MovimentTypeAdapter(MovimentType.values().toList()) {
                listener?.onMovimentSelected(it)
                dismiss()
            }
        }
    }

    interface MovimentTypeListener {

        fun onMovimentSelected(movimentType: MovimentType)
    }

    companion object {

        const val TAG = "TAG"

        fun newInstance() = MovimentTypeDialog()
    }
}