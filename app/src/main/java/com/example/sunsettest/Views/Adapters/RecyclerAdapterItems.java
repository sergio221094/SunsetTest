package com.example.sunsettest.Views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunsettest.Models.Items;
import com.example.sunsettest.Models.Stations;
import com.example.sunsettest.R;

import java.util.List;

public class RecyclerAdapterItems extends RecyclerView.Adapter<RecyclerAdapterItems.MyViewHolder> implements  View.OnClickListener {


    private List<Items> itemList;
    private Context context;
    private View.OnClickListener listener;

    public RecyclerAdapterItems(List<Items> itemList, Context context){
        this.itemList = itemList;
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
        Items stations = itemList.get(position);
        if(stations.getAddress().length()<=0){
            holder.description.setText("No tiene");
        }else{
            holder.description.setText(stations.getAddress());
        }

        if(stations.getName().length()<=0){
            holder.name.setText("No tiene");
        }else{
            holder.name.setText(stations.getName());
        }



    }

    @Override
    public int getItemCount() {
        return itemList.size();
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

    public void addStation(List<Items> items){
        for (Items itm : items){
            itemList.add(itm);
        }
        notifyDataSetChanged();
    }

}