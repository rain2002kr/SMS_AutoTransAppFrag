package com.example.roomexample_yena

import android.app.Application
import androidx.lifecycle.LiveData
import java.lang.Exception

class ContactLogReposityory (application: Application) {

    private val contactLogDatabase = ContactLogDatabase.getInstance(application)!!
    private val contactLogDao : ContactLogDao = contactLogDatabase.contactLogDao()
    private val contactLog : LiveData<List<ContactLog>> = contactLogDao.getAll()

    fun getAll() : LiveData<List<ContactLog>> {
        return contactLog
    }

    fun insert(contactLog : ContactLog){
        try {
            val thread = Thread(Runnable {
                contactLogDao.insert(contactLog)
            })
            thread.start()
        } catch (e : Exception){e.printStackTrace()}
    }

    fun delete(contactLog: ContactLog) {
        try {
            val thread = Thread(Runnable {
                contactLogDao.delete(contactLog)
            })
            thread.start()
        } catch (e : Exception){e.printStackTrace()}

    }
    fun update(contactLog: ContactLog) {
        try {
            val thread = Thread(Runnable {
                contactLogDao.update(contactLog)
            })
            thread.start()
        } catch (e : Exception){e.printStackTrace()}

    }


}