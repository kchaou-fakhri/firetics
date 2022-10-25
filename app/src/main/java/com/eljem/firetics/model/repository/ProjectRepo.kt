package com.eljem.firetics.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.eljem.firetics.model.data.remote.ProjectRemote
import com.eljem.firetics.model.entity.Project

class ProjectRepo {
    val projectRemote = ProjectRemote()

    fun connectToFireBase(project: Project,context: Context): LiveData<Boolean> {
        return projectRemote.connectToFireBase(project, context)
    }
}