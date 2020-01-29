package com.example.roomexample_yena

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactLogDao{
    @Query("SELECT * FROM contactLog ORDER BY id ASC")
    fun getAll() : LiveData<List<ContactLog>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contactLog :ContactLog)

    @Delete
    fun delete(contactLog :ContactLog)

    @Update
    fun update(contactLog :ContactLog)

}