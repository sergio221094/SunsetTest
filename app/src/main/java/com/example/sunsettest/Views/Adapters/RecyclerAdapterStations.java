package com.example.sunsettest.Views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sunsettest.Models.Stations;
import com.example.sunsettest.R;

import java.util.List;

public class RecyclerAdapterStations extends RecyclerView.Adapter<RecyclerAdapterStations.MyViewHolder> implements  View.OnClickListener {


    private List<Stations> stationsList;
    private Context context;
    private View.OnClickListener listener;

    public RecyclerAdapterStations(List<Stations> stationsList, Context context){
        this.stationsList = stationsList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Stations stations = stationsList.get(position);

        if(stations.getDesc().length()<=0){
            holder.description.setText("No tiene");
        }else{
            holder.description.setText(stations.getDesc());
        }

        if(stations.getName().length()<=0){
            holder.name.setText("No tiene");
        }else{
            holder.name.setText(stations.getName());
        }
    }

    @Override
    public int getItemCount() {
        return stationsList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_text_view);
            description = itemView.findViewById(R.id.description_text_view);
        }
    }

    public void addStation(List<Stations> stations){
        for (Stations stn : stations){
            stationsList.add(stn);
        }
        notifyDataSetChanged();
    }

}