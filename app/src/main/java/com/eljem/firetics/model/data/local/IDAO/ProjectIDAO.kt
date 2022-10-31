package com.eljem.firetics.model.data.local.IDAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eljem.firetics.model.entity.Project

@Dao
interface ProjectIDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProject(project: Project)

    @Delete
    fun deleteProject(project: Project)

    @Query("SELECT * FROM project")
    fun getAllProjects() : LiveData<List<Project>>

    @Query("SELECT * FROM project WHERE name = :name OR projectID = :id OR apiKey = :apikey")
    fun getById(name : String, id : String, apikey : String) : LiveData<Project>
}