package com.example.roomexample_yena

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactLog")
class ContactLog (
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name =  "receiveName")
    var receiveName: String,

    @ColumnInfo(name =  "receiveTime")
    var receiveTime: String?,

    @ColumnInfo(name =  "receiveNumber")
    var receiveNumber: String,

    @ColumnInfo(name =  "message")
    var message: String?,

    @ColumnInfo(name =  "transName")
    var transName: String,

    @ColumnInfo(name =  "transTime")
    var transTime: String?,

    @ColumnInfo(name =  "transNumber")
    var transNumber: String

){
    constructor() : this(null, "","","","",
                                    "","",""
    )
}