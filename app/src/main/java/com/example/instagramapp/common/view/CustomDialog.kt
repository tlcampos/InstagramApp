package com.example.instagramapp.common.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.instagramapp.R
import com.example.instagramapp.databinding.DialogCustomBinding

class CustomDialog(context: Context) : Dialog(context) {

    private lateinit var dialogLinearLayout: LinearLayout
    private lateinit var dialogBinding: DialogCustomBinding
    private lateinit var txtButtons: Array<TextView>
    private lateinit var txtTitle: TextView
    private var titleId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogBinding = DialogCustomBinding.inflate(layoutInflater)
        setContentView(dialogBinding.root)

        dialogLinearLayout = dialogBinding.dialogContainer
        txtTitle = dialogBinding.dialogTitle
    }

    override fun setTitle(titleId: Int) {
        this.titleId = titleId
    }

    fun addButton(vararg texts: Int, listener: View.OnClickListener){
        txtButtons = Array(texts.size){
            TextView(context)
        }
        texts.forEachIndexed { index, txtId ->
            txtButtons[index].id = txtId
            txtButtons[index].setText(txtId)
            txtButtons[index].setOnClickListener {
                listener.onClick(it)
                dismiss()
            }
        }
    }

    override fun show() {
        super.show()

        titleId.let {
            txtTitle.setText(it!!)
        }

        for (textView in txtButtons) {
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(30,50,30,50)

            dialogLinearLayout.addView(textView, layoutParams)
        }
    }
}