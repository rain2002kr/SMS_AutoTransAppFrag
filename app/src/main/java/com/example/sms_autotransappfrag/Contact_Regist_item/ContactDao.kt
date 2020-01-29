package com.example.roomexample_yena

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao{
    @Query("SELECT * FROM contact ORDER BY id ASC")
    fun getAll() : LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact :Contact)

    @Delete
    fun delete(contact :Contact)

    @Update
    fun update(contact :Contact)

}