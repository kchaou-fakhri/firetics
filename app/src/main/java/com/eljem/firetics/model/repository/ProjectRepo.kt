package com.eljem.firetics.model.repository

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.eljem.firetics.model.data.DAO.AppDatabase
import com.eljem.firetics.model.data.local.IDAO.ProjectIDAO
import com.eljem.firetics.model.data.remote.ProjectRemote
import com.eljem.firetics.model.entity.Project
import com.eljem.firetics.utils.MessageResult

class ProjectRepo(private val database : ProjectIDAO) {
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


    suspend fun insertProjectToDatabase( project : Project){
        return database.insertProject(project)
    }

    suspend fun updateProject( project : Project){
        return database.updateProject(project)
    }

    fun getAllProjects() : LiveData<List<Project>>{
        return database.getAllProjects()
    }


    fun getById(project: Project) : LiveData<Project>{
        return database.getById(project.name, project.projectID, project.apiKey)
    }

}