package com.eljem.firetics.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Calendar


@Entity(tableName = "project")
data class Project(@PrimaryKey var name : String = "",
                   @ColumnInfo var projectID : String = "",
                   @ColumnInfo var apiKey : String ="",
                   @ColumnInfo var firestoreDB: String = "YES",
                   @ColumnInfo var realtimeDB : String = "NO",
                   @ColumnInfo var storage : String = "NO",
                   @ColumnInfo var authentication: String = "NO",
                    @ColumnInfo var status : String = "NO",
                    @ColumnInfo var created_at : String = Calendar.getInstance().apply {
                        LocalDateTime.of(
                            this.get(Calendar.YEAR),
                            this.get(Calendar.MONTH),
                            this.get(Calendar.DAY_OF_MONTH),
                            this.get(Calendar.HOUR_OF_DAY),
                            this.get(Calendar.MINUTE),
                            this.get(Calendar.SECOND))
                    }.time.toString().substring(4)
)
{

}
