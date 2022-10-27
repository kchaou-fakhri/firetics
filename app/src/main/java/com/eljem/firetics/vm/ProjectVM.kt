package com.eljem.firetics.vm

import android.content.Context
import androidx.lifecycle.LiveData
import com.eljem.firetics.model.entity.Project
import com.eljem.firetics.model.repository.ProjectRepo
import com.eljem.firetics.utils.MessageResult

class ProjectVM {
    val projectRepo = ProjectRepo();

    fun validateConnectionWithFireStore(project: Project,context: Context): LiveData<MessageResult> {
        return projectRepo.validateConnectionWithFireStore(project, context)
    }

    fun validateConnectionWithRealtimeDatabase(project: Project,context: Context): LiveData<MessageResult> {
        return projectRepo.validateConnectionWithRealtimeDatabase(project, context)
    }

    fun validateConnectionWithFirebaseAuthentication(project: Project,context: Context): LiveData<MessageResult> {
        return projectRepo.validateConnectionWithFirebaseAuthentication(project, context)
    }

    fun validateConnectionWithFirebaseStorage(project: Project,context: Context): LiveData<MessageResult> {
        return projectRepo.validateConnectionWithFirebaseStorage(project, context)
    }
}