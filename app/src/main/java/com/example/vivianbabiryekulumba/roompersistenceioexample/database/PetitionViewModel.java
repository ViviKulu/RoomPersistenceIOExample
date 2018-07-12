package com.example.vivianbabiryekulumba.roompersistenceioexample.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;


public class PetitionViewModel extends AndroidViewModel {

    private PetitionRepository petitionRepository;
    private LiveData<List<Petition>> allPetitions;

    public PetitionViewModel(@NonNull Application application) {
        super(application);
        petitionRepository = new PetitionRepository(application);
        allPetitions = petitionRepository.getAllPetitions();
    }

    public LiveData<List<Petition>> getAllPetitions() {
        return allPetitions;
    }

    public void insert(Petition petition){
        petitionRepository.insert(petition);
    }
}
