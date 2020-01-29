package com.example.roomexample_yena

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
class Contact (
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name =  "imgReceiver")
    var imgReceiver: Int?,

    @ColumnInfo(name =  "receiveNumber")
    var receiveNumber: String,

    @ColumnInfo(name =  "receiveName")
    var receiveName: String,

    @ColumnInfo(name =  "imgRotate")
    var imgRotate: Int?,

    @ColumnInfo(name =  "imgTransfer")
    var imgTransfer: Int?,

    @ColumnInfo(name =  "transNumber")
    var transNumber: String,

    @ColumnInfo(name =  "transName")
    var transName: String,

    @ColumnInfo(name =  "initial")
    var initial: Char
){
    //constructor() : this(null, "","",'\u0000')
    constructor() : this(null, null,"","",null,
        null,"","",'\u0000'
    )
}