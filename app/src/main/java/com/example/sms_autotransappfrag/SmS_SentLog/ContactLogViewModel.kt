package com.example.roomexample_yena

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ContactLogViewModel (application: Application) : AndroidViewModel(application) {

    private val reposityory = ContactLogReposityory(application)
    private val contactLog = reposityory.getAll()

    fun getAll() : LiveData<List<ContactLog>> {
        return this.contactLog
    }

    fun insert(contactLog :ContactLog){
        reposityory.insert(contactLog)
    }

    fun delete(contactLog : ContactLog){
        reposityory.delete(contactLog)
    }

    fun update(contactLog :  ContactLog){
        reposityory.update(contactLog)
    }

}