package com.example.assignment1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;

import java.util.ArrayList;

public class AdapterObservation extends RecyclerView.Adapter<AdapterObservation.ObservationViewHolder>{
    private Context context;
    private DbHelperHike DbHelperHike;
    private ArrayList<ModelObservation> obsList;

    public AdapterObservation(Context context, ArrayList<ModelObservation> observationsList)
    {
        this.context = context;
        this.obsList = observationsList;
        DbHelperHike = new DbHelperHike(context);
    }

    @NonNull
    @Override
    public ObservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemobs,parent,false);
        ObservationViewHolder vh = new ObservationViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ObservationViewHolder holder, int position) {
        ModelObservation model = obsList.get(position);

        //Get data
        String id = model.getId();
        String name = model.getName();
        String date = model.getDate();
        String comment = model.getComment();

        //Set data in view
        holder.OName.setText(name);
        holder.ODate.setText(date);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsObservation.class);
                intent.putExtra("OBSID", id);
                context.startActivity(intent);
            }
        });

        holder.update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create intent for editing
                Intent intent = new Intent(context,AddEditObservationActivity.class);
                intent.putExtra("isEditObservation",true);
                intent.putExtra("OBSERVATIONID",id);
                intent.putExtra("OBSERVATIONNAME",name);
                intent.putExtra("DATE",date);
                intent.putExtra("COMMENT",comment);
                context.startActivity(intent);
            }
        });

        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelperHike.DeleteObservation(id);
                ((DetailsHike)context).onResume();
            }
        });
    }

    @Override
    public int getItemCount() {
        return obsList.size();
    }

    class ObservationViewHolder extends RecyclerView.ViewHolder{
        TextView OName, ODate, delete_button, update_button;
        RelativeLayout relativeLayout;
        LinearLayout linearLayout;
        SwipeRevealLayout parentLayout;

        public ObservationViewHolder(@NonNull View v)
        {
            super(v);

            OName = v.findViewById(R.id.i_ObsName);
            ODate = v.findViewById(R.id.i_ObsDate);

            relativeLayout = v.findViewById(R.id.OBSmainLayout);
            parentLayout = v.findViewById(R.id.OBSparentLayout);

            update_button = v.findViewById(R.id.obsEdit);
            delete_button = v.findViewById(R.id.obsDelete);
        }
    }
}
