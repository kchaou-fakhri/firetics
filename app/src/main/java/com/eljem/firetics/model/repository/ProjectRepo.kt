package com.eljem.firetics.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.eljem.firetics.model.data.remote.ProjectRemote
import com.eljem.firetics.model.entity.Project
import com.eljem.firetics.utils.MessageResult

class ProjectRepo {
    val projectRemote = ProjectRemote()

    fun validateConnectionWithFireStore(project: Project,context: Context): LiveData<MessageResult> {
        return projectRemote.validateConnectionWithFireStore(project, context)
    }

    fun validateConnectionWithRealtimeDatabase(project: Project,context: Context): LiveData<MessageResult> {
        return projectRemote.validateConnectionWithRealtimeDatabase(project, context)
    }

    fun validateConnectionWithFirebaseAuthentication(project: Project,context: Context): LiveData<MessageResult> {
        return projectRemote.validateConnectionWithFirebaseAuthentication(project, context)
    }
    fun validateConnectionWithFirebaseStorage(project: Project,context: Context): LiveData<MessageResult> {
        return projectRemote.validateConnectionWithFirebaseStorage(project, context)
    }
}