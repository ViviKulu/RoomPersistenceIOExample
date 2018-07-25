package com.example.vivianbabiryekulumba.roompersistenceioexample.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import static android.support.constraint.Constraints.TAG;


@Database(entities = Petition.class, version = 1, exportSchema = false)
public abstract class PetitionRoomDatabase extends RoomDatabase{

    public abstract PetitionDAO petitionDAO();
    private static PetitionRoomDatabase INSTANCE;

    public static PetitionRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (PetitionRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PetitionRoomDatabase.class, "petition_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{

        private PetitionDAO petitionDAO;

        public PopulateDbAsync(PetitionRoomDatabase instance) {
            petitionDAO = instance.petitionDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            petitionDAO.deleteAll();
            Log.d(TAG, "doInBackground: " + petitionDAO.getAllPetitions().toString());
            Petition petition = new Petition(petitionDAO.getAllPetitions().toString());
            petitionDAO.insert(petition);
            return null;
        }
    }
}
