package com.example.roomexample_yena

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class
ContactViewModel (application: Application) : AndroidViewModel(application) {

    private val reposityory = ContactReposityory(application)
    private val contacts = reposityory.getAll()

    fun getAll() : LiveData<List<Contact>> {
        return this.contacts
    }

    fun insert(contact :Contact){
        reposityory.insert(contact)
    }

    fun delete(contact : Contact){
        reposityory.delete(contact)
    }

    fun update(contact :  Contact){
        reposityory.update(contact)
    }

}