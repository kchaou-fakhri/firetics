package com.eljem.firetics.utils

import android.app.Activity
import android.app.AlertDialog
import com.eljem.firetics.R


class AlertNoConnection internal constructor(private val activity: Activity) {


        private lateinit var alertDialog : AlertDialog
        fun startLoadingAlert() {
            val builder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            builder.setView(inflater.inflate(R.layout.alert_no_connection, null))



            builder.setCancelable(false)

            alertDialog = builder.create()
            alertDialog.show()
        }

        fun dismissDialog() {
            alertDialog.cancel()

        }

}