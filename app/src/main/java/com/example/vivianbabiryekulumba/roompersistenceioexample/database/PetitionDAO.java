package com.example.vivianbabiryekulumba.roompersistenceioexample.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface PetitionDAO {

    @Insert
    void insert(Petition petition);

    @Query("DELETE from petition_table")
    void deleteAll();

    @Query("SELECT * from petition_table")
    LiveData<List<Petition>> getAllPetitions();
}
