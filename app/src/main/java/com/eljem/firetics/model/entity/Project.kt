package com.eljem.firetics.model.entity

data class Project(val name : String, val projectID : String,
                   val apiKey : String, var firestoreDB: String = "YES",
                    var realtimeDB : String = "NO",  var storage : String = "NO",
                   var authentication: String = "NO" )
{

}
