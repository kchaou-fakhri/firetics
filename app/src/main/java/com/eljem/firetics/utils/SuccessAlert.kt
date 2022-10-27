package com.eljem.firetics.utils


import android.app.Activity
import android.app.AlertDialog
import android.widget.Button
import com.eljem.firetics.R
import com.google.android.material.button.MaterialButton


class SuccessAlert internal constructor(private val activity: Activity) {
    private lateinit var alertDialog : AlertDialog
    fun startLoadingAlert() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val view =   builder.setView(inflater.inflate(R.layout.alert_success, null))
        builder.setCancelable(false)

        alertDialog = builder.create()
        alertDialog.show()
        val btnDone: MaterialButton = alertDialog.findViewById(R.id.btn_done) as MaterialButton

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