package com.example.vivianbabiryekulumba.roompersistenceioexample.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;


public class PetitionRepository {
    private PetitionDAO petitionDAO;
    private LiveData<List<Petition>> allPetitions;

    public PetitionRepository(Application application){
        PetitionRoomDatabase db = PetitionRoomDatabase.getDatabase(application);
        petitionDAO = db.petitionDAO();
        allPetitions = petitionDAO.getAllPetitions();
    }

    LiveData<List<Petition>> getAllPetitions(){
        return allPetitions;
    }

    public void insert(Petition petition){
        new insertAsyncTask(petitionDAO).execute(petition);
    }

    private static class insertAsyncTask extends AsyncTask <Petition, Void, Void>{

        private PetitionDAO asyncPetitionDao;

        public insertAsyncTask(PetitionDAO petitionDAO) {
            asyncPetitionDao = petitionDAO;
        }

        @Override
        protected Void doInBackground(final Petition... petitions) {
            asyncPetitionDao.insert(petitions[0]);
            return null;
        }
    }
}
