package com.eljem.firetics.model.data.remote

import android.app.Application
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eljem.firetics.BuildConfig
import com.eljem.firetics.model.entity.Project
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProjectRemote {
    fun connectToFireBase(project: Project,context: Context): LiveData<Boolean> {

        var multableLiveData = MutableLiveData<Boolean>()
        multableLiveData.value = false
        val options = FirebaseOptions.Builder()
            .setProjectId(project.projectID)
            .setApplicationId(BuildConfig.APPLICATION_ID)
            .setApiKey(project.apiKey)
            .build()

        FirebaseApp.initializeApp(context /* Context */, options, project.name)
        val first = FirebaseApp.getInstance(project.name)

        val db = Firebase.firestore(first)
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )
        db.collection("test").add(user)
            .addOnSuccessListener { documentReference ->
                multableLiveData.value = true
                db.collection("test").document(documentReference.id).delete()
            }
            .addOnFailureListener { e ->
                multableLiveData.value = false

                Log.w(TAG, "Error adding document", e)
            }


        return multableLiveData

    }
}