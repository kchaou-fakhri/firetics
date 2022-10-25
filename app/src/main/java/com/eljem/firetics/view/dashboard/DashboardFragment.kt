package com.eljem.firetics.view.dashboard


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eljem.firetics.BuildConfig
import com.eljem.firetics.R
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.core.FirestoreClient
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import java.io.FileInputStream


class DashboardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        connectToFireBase()
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    fun connectToFireBase(){
        val options1 = FirebaseOptions.Builder()
            .setProjectId("madina-27539")
            .setApplicationId(BuildConfig.APPLICATION_ID)
            .setApiKey("AIzaSyDiqRyQathvWiN7PQ3BtCVL1hmybrkVJWs")

            .build()

        val options2 = FirebaseOptions.Builder()
            .setProjectId("e-learning-cd263")
            .setApplicationId(BuildConfig.APPLICATION_ID)
            .setApiKey("AIzaSyDD5w1xBL-DNcn_F4kEzkSaVRvFipkQRVg") // setDatabaseURL(...)
            // setStorageBucket(...)
            .build()
        // [END firebase_options]


        // [START firebase_secondary]
        // Initialize with secondary app
        // [END firebase_options]


        // [START firebase_secondary]
        // Initialize with secondary app
        FirebaseApp.initializeApp(requireContext() /* Context */, options1, "first")
        FirebaseApp.initializeApp(requireContext() /* Context */, options2, "secondary")


        val first = FirebaseApp.getInstance("first")
        val secondary = FirebaseApp.getInstance("secondary")


        val db = Firebase.firestore(secondary)

//        val user = hashMapOf(
//            "first" to "Ada",
//            "last" to "Lovelace",
//            "born" to 1815
//        )
//        db.collection("ussssssssers")
//            .add(user)
//            .addOnSuccessListener { documentReference ->
//                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.w(TAG, "Error adding document", e)
//            }



    }
}