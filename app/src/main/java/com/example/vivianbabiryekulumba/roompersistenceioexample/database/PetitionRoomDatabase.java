package com.example.vivianbabiryekulumba.roompersistenceioexample.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


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

            Petition petition = new Petition("First petition");
            petitionDAO.insert(petition);
            petition = new Petition("Second ApplicationPOJO");
            petitionDAO.insert(petition);
            return null;
        }
    }
}
