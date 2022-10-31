package com.eljem.firetics.utils


import android.app.Activity
import android.app.AlertDialog
import android.widget.Button
import android.widget.TextView
import com.eljem.firetics.R
import com.google.android.material.button.MaterialButton


class FalureAlert internal constructor(private val activity: Activity, private val errortext : String,
            private val titleError : String) {
    private lateinit var alertDialog : AlertDialog
    fun startLoadingAlert() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val view =   builder.setView(inflater.inflate(R.layout.alert_failure, null))
        builder.setCancelable(false)

        alertDialog = builder.create()
        alertDialog.show()
        val btnDone: MaterialButton = alertDialog.findViewById(R.id.btn_done) as MaterialButton
        val txtError : TextView = alertDialog.findViewById<TextView?>(R.id.text_error)
        txtError.text =errortext

        val txtTitleError : TextView = alertDialog.findViewById<TextView?>(R.id.title_error)
        txtTitleError.text =titleError
        btnDone.setOnClickListener {
            dismissDialog()
        }


    }

    fun dismissDialog() {
        alertDialog.cancel()

    }

    fun showDialog() {
        alertDialog.show()

    }
}