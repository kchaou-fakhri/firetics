package com.eljem.firetics.vm

import android.content.Context
import androidx.lifecycle.LiveData
import com.eljem.firetics.model.entity.Project
import com.eljem.firetics.model.repository.ProjectRepo

class ProjectVM {
    val projectRepo = ProjectRepo();

    fun connectToFireBase(project: Project,context: Context) : LiveData<Boolean> {
       return  projectRepo.connectToFireBase(project, context)
    }

}