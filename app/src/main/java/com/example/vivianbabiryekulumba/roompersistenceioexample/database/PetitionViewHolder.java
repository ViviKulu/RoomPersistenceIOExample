package com.example.vivianbabiryekulumba.roompersistenceioexample.database;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.vivianbabiryekulumba.roompersistenceioexample.R;

/**
 * Created by vivianbabiryekulumba on 6/27/18.
 */

public class PetitionViewHolder extends RecyclerView.ViewHolder {

    private TextView recycler_petition_tv;

    public PetitionViewHolder(View itemView) {
        super(itemView);
        recycler_petition_tv = itemView.findViewById(R.id.textView);
    }

    public void onBind(Petition petition) {
        recycler_petition_tv.setText(petition.getPetition());
    }
}
