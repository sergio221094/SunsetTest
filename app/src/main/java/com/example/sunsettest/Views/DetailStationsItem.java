package com.example.sunsettest.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sunsettest.Controllers.ApiClient;
import com.example.sunsettest.Controllers.ApiInterface;
import com.example.sunsettest.Models.Items;
import com.example.sunsettest.R;
import com.example.sunsettest.Views.Adapters.RecyclerAdapterItems;

import java.util.ArrayList;
import java.util.List;
public class DetailStationsItem extends AppCompatActivity {

    private ApiInterface apiInterface;
    List<Items> items;
    RecyclerView itemsRecyclerView;
    private RecyclerAdapterItems adapter;
    private LinearLayoutManager layoutManager;
    public static final String ITEM_SELECTED = "itemSelected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_stations_item);


        apiInterface = ApiClient.getApiclient().create(ApiInterface.class);
        itemsRecyclerView = findViewById(R.id.recyclerView_items);
        layoutManager = new GridLayoutManager(this, 1);
        itemsRecyclerView.setHasFixedSize(true);
        itemsRecyclerView.setLayoutManager(layoutManager);

        getItems();
    }

    private void getItems(){
        items = (List<Items>) getIntent().getSerializableExtra("stationSelected");
        adapter = new RecyclerAdapterItems(items, getApplicationContext());
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Items itemSelected = items.get(itemsRecyclerView.getChildAdapterPosition(v));
                Intent intent = new Intent (DetailStationsItem.this, ItemDetail.class);
                intent.putExtra( ITEM_SELECTED, itemSelected);
                startActivity(intent);
            }
        });
        itemsRecyclerView.setAdapter(adapter);
    }
}