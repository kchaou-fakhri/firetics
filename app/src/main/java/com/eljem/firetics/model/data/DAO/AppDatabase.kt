package com.eljem.firetics.model.data.DAO

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eljem.firetics.model.data.local.IDAO.ProjectIDAO
import com.eljem.firetics.model.entity.Project

@Database(entities = [Project::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    abstract fun projectDAO() : ProjectIDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fireticsDB"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}