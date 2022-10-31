package com.eljem.firetics.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project")
data class Project(@PrimaryKey var name : String = "",
                   @ColumnInfo var projectID : String = "",
                   @ColumnInfo var apiKey : String ="",
                   @ColumnInfo var firestoreDB: String = "YES",
                   @ColumnInfo var realtimeDB : String = "NO",
                   @ColumnInfo var storage : String = "NO",
                   @ColumnInfo var authentication: String = "NO" )
{

}
