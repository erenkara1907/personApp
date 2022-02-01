package com.erenkara.personsapp.room

import androidx.room.*
import com.erenkara.personsapp.entity.Person
import com.erenkara.personsapp.entity.PersonDb

@Dao
interface PersonDao {

    @Query("select * from person")
    suspend fun getAll() : List<PersonDb>

    @Query("select * from person where name like '%' || :searchWord || '%'")
    suspend fun searchPerson(searchWord : String) : List<PersonDb>

    @Insert
    suspend fun add(person: PersonDb)

    @Update
    suspend fun update(person: PersonDb)

    @Delete
    suspend fun delete(person: PersonDb)
}