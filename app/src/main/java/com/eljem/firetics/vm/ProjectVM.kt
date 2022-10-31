package com.eljem.firetics.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eljem.firetics.model.data.DAO.AppDatabase
import com.eljem.firetics.model.entity.Project
import com.eljem.firetics.model.repository.ProjectRepo
import com.eljem.firetics.utils.MessageResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProjectVM(application : Application) : AndroidViewModel(application){
    private val projectRepo : ProjectRepo

    init {
        val dao = AppDatabase.getDatabase(application).projectDAO()
        projectRepo = ProjectRepo(dao)
    }

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

   fun insertProjectToDatabase(project: Project){
        viewModelScope.launch(Dispatchers.IO){
            projectRepo.insertProjectToDatabase(project)
        }
    }


    fun getAllProjects() :LiveData<List<Project>>{
        return projectRepo.getAllProjects()
    }

    fun getById(project: Project) :LiveData<Project>{
        return projectRepo.getById(project)
    }
}