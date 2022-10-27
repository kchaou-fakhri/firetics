package com.eljem.firetics.model.entity

data class Project(var name : String = "", var projectID : String = "",
                   var apiKey : String ="", var firestoreDB: String = "YES",
                    var realtimeDB : String = "NO",  var storage : String = "NO",
                   var authentication: String = "NO" )
{

}
