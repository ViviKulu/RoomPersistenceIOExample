package com.example.vivianbabiryekulumba.roompersistenceioexample.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "petition_table")
public class Petition {

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "petition")
    private String petition;

    public Petition(@NonNull String petition) {
        this.petition = petition;
    }

    @NonNull
    public String getPetition() {
        return petition;
    }

    public void setPetition(@NonNull String petition) {
        this.petition = petition;
    }
}
