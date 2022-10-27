package com.eljem.firetics.model.data.remote


import android.content.ContentResolver
import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.AnyRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eljem.firetics.BuildConfig
import com.eljem.firetics.R
import com.eljem.firetics.model.entity.Project
import com.eljem.firetics.utils.MessageResult
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.*


class ProjectRemote {

    var createConnection = false



    fun validateConnectionWithFireStore(project: Project,context: Context): LiveData<MessageResult> {


        var multableLiveData = MutableLiveData<MessageResult>()

        try {
    val firebaseProject =  createConnection(project,context)


    val db = Firebase.firestore(firebaseProject)
    val user = hashMapOf(
        "first" to "Ada",
        "last" to "Lovelace",
        "born" to 1815
    )



    db.collection("fireticsTesting").add(user)
        .onSuccessTask { documentReference ->
            multableLiveData.value = MessageResult(true,"YES")
            db.collection("fireticsTesting").document(documentReference.id).delete()
        }
        .addOnFailureListener { e ->
            multableLiveData.value = MessageResult(false,"NO")
            Log.w(TAG, "Error adding document", e)
        }

}catch (e : Exception){
    multableLiveData.value = MessageResult(false,"NO")

}
        Thread(Runnable {

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                if (multableLiveData.value?.statu != true){
                    multableLiveData.value = MessageResult(false, "NO")
                }

            }, 8000) // It will wait 10 sec before updating UI
        }).start()


        return multableLiveData

    }

    fun validateConnectionWithRealtimeDatabase(project: Project,context: Context): LiveData<MessageResult> {

        var multableLiveData = MutableLiveData<MessageResult>()

        try {
        val firebaseProject =  createConnection(project,context)

        val db = Firebase.database(firebaseProject)
        val test = "hello"


    val id =   db.getReference("fireticsTesting").push().key
           var verif = db.getReference("fireticsTesting").child(id!!).setValue(test)
.addOnCompleteListener { task->

                if (task.isSuccessful) {
                    db.getReference("fireticsTesting").child(id!!).removeValue()

                    multableLiveData.value = MessageResult(true, "YES")
                } else {
                    multableLiveData.value = MessageResult(false, "NO")

                }
            }
            .addOnFailureListener { e ->
                multableLiveData.value = MessageResult(false,"NO")

                Log.w(TAG, "Error adding document", e)
            }



        }catch (e : FirebaseException){
        multableLiveData.value = MessageResult(false,"NO")
        }


        Thread(Runnable {

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                if (multableLiveData.value?.statu != true){
                    multableLiveData.value = MessageResult(false, "NO")
                }

            }, 8000) // It will wait 10 sec before updating UI
        }).start()

        return multableLiveData

    }

    fun validateConnectionWithFirebaseAuthentication(project: Project,context: Context): LiveData<MessageResult> {


        var multableLiveData = MutableLiveData<MessageResult>()

        try {
            val firebaseProject =  createConnection(project,context)

            val db = Firebase.auth(firebaseProject)

            db.createUserWithEmailAndPassword("firetics@fire.com", "firetics")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = db.currentUser!!
                        user.delete()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d(TAG, "User account deleted.")
                                }
                            }
                        multableLiveData.value = MessageResult(true, "YES")

                    }
                    else
                    {
                        multableLiveData.value = MessageResult(false, "NO")

                    }
                }
        }catch (e : Exception){
            multableLiveData.value = MessageResult(false, "NO")

        }
        Thread(Runnable {

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                if (multableLiveData.value?.statu != true){
                    multableLiveData.value = MessageResult(false, "NO")
                }

            }, 8000) // It will wait 10 sec before updating UI
        }).start()


        return multableLiveData

    }

    fun validateConnectionWithFirebaseStorage(project: Project,context: Context): LiveData<MessageResult> {


        var multableLiveData = MutableLiveData<MessageResult>()

        try {
        val firebaseProject =  createConnection(project,context)

        val db = Firebase.storage(firebaseProject).reference

        val ref = db.child("firetics")


            var uploadTask=  ref.putFile( getUriToDrawable(context, R.drawable.flames))

            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                        multableLiveData.value = MessageResult(false, "NO")
                    }
                }
                ref.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    multableLiveData.value = MessageResult(true, "YES")

                }
            }

        }catch (e : Exception){
            multableLiveData.value = MessageResult(false, "NO")
        }



        Thread(Runnable {

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                if (multableLiveData.value?.statu != true){
                    multableLiveData.value = MessageResult(false, "NO")
                }

            }, 8000) // It will wait 10 sec before updating UI
        }).start()
        return multableLiveData

    }

    private fun createConnection(project: Project, context: Context): FirebaseApp {

        if (!createConnection){


        val options = FirebaseOptions.Builder()
            .setProjectId(project.projectID)
            .setApplicationId(BuildConfig.APPLICATION_ID)
            .setApiKey(project.apiKey)
            .setStorageBucket(project.projectID+".appspot.com")
            .build()

        FirebaseApp.initializeApp(context /* Context */, options, project.name)
            createConnection = true
        }
       return FirebaseApp.getInstance(project.name)

    }

    fun getUriToDrawable(
       context: Context,
        drawableId: Int
    ): Uri {
        return Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://" + context.resources.getResourcePackageName(drawableId)
                    + '/' + context.resources.getResourceTypeName(drawableId)
                    + '/' + context.resources.getResourceEntryName(drawableId)
        )
    }
}