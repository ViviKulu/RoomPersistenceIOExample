package com.example.vivianbabiryekulumba.roompersistenceioexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vivianbabiryekulumba.roompersistenceioexample.database.Petition;
import com.example.vivianbabiryekulumba.roompersistenceioexample.database.PetitionRepository;

import java.io.FileOutputStream;

public class NewPetitionActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    EditText editText;
    Button submitBtn;
    Button saveBtn;
    Button viewBtn;
    Petition petition;
    PetitionRepository petitionRepository;
    String petitionData;
    String file = "petition.txt";
    private String TAG = "NewActivity.class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_petition);

        editText = findViewById(R.id.type_petition);

        submitBtn = findViewById(R.id.button_submit);
        saveBtn = findViewById(R.id.button_save);
        viewBtn = findViewById(R.id.button_view);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petitionData = editText.getText().toString();
                Log.d(TAG, "onClick: " + petitionData);
                try{
                    FileOutputStream fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE);
                    fileOutputStream.write(petitionData.getBytes());
                    fileOutputStream.close();
                    Log.d(TAG, "onClick: " + fileOutputStream);
                    Toast.makeText(getApplicationContext(), "Petition written to file!", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petition = new Petition(file);
                petitionRepository = new PetitionRepository(getApplication());
                petitionRepository.insert(petition);
                Log.d(TAG, "onClick: " + petition);
                Toast.makeText(getApplicationContext(), "Petition saved to database!", Toast.LENGTH_SHORT).show();
            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewPetitionActivity.this, RecyclerPetitionActivity.class);
                startActivity(intent);
            }
        });
    }
}
