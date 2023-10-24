package com.example.assignment1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;

import java.util.ArrayList;
import java.util.List;

public class AdapterHike extends RecyclerView.Adapter<AdapterHike.HikeViewHolder>{

    private Context context;
    private ArrayList<ModelHike> hikeList;
    private DbHelperHike DbHelperHike;
    public AdapterHike(Context context, ArrayList<ModelHike> hikeList)
    {
        this.context = context;
        this.hikeList = hikeList;
        DbHelperHike = new DbHelperHike(context);
    }

    @NonNull
    @Override
    public HikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new HikeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HikeViewHolder holder, int position) {

        ModelHike model = hikeList.get(position);

        //Get data
        String id = model.getId();
        String name = model.getName();
        String location = model.getLocation();
        String date = model.getDate();
        String duration = model.getDuration();
        String length = model.getLength();
        String difficulty = model.getDifficulty();
        String fquantity = model.getFQuantity();
        String description = model.getDescription();

        holder.HName.setText(name);
        holder.HLocation.setText(location);
        holder.HDate.setText(date);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsHike.class);
                intent.putExtra("HIKEID", id);
                context.startActivity(intent);
            }
        });

        holder.update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create intent for editing
                Intent intent = new Intent(context,AddEditHike.class);
                intent.putExtra("isEditMode",true);
                intent.putExtra("ID",id);
                intent.putExtra("NAME",name);
                intent.putExtra("LOCATION",location);
                intent.putExtra("DATE",date);
                intent.putExtra("DURATION",duration);
                intent.putExtra("LENGTH",length);
                intent.putExtra("DIFFICULTY",difficulty);
                intent.putExtra("FRIQUANTITY",fquantity);
                intent.putExtra("DESCRIPTION",description);

                context.startActivity(intent);
            }
        });

        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelperHike.deleteHike(id);
                ((MainActivity)context).onResume();
            }
        });
    }

    @Override
    public int getItemCount() {
        return hikeList.size();
    }

    class HikeViewHolder extends RecyclerView.ViewHolder{
        TextView HId, HName, HLocation, HDate, delete_button, update_button;
        RelativeLayout relativeLayout;
        SwipeRevealLayout parentLayout;
        public HikeViewHolder(@NonNull View v)
        {
            super(v);

            HName = v.findViewById(R.id.i_HikeName);
            HLocation = v.findViewById(R.id.i_HikeLocation);
            HDate = v.findViewById(R.id.i_HikeDate);

            relativeLayout = v.findViewById(R.id.mainLayout);
            parentLayout = v.findViewById(R.id.parentLayout);

            update_button = v.findViewById(R.id.hikeEdit);
            delete_button = v.findViewById(R.id.hikeDelete);
        }
    }
}
