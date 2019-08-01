package com.example.sunsettest.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sunsettest.Controllers.ApiClient;
import com.example.sunsettest.Controllers.ApiInterface;
import com.example.sunsettest.Models.DataResponse;
import com.example.sunsettest.Models.Items;
import com.example.sunsettest.Models.Stations;
import com.example.sunsettest.R;
import com.example.sunsettest.Views.Adapters.RecyclerAdapterStations;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  MainActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    List<Stations> stations;
    RecyclerView stationsRecyclerView;
    private RecyclerAdapterStations adapter;
    RelativeLayout progressBar;
    private int pastVisibleItem, visibleItemCount, totallItemCount, previous_total = 0;
    private LinearLayoutManager layoutManager;
    private boolean isLoading = true;
    private int desde, hasta;
    private int view_thersehold=10;
    private Context mContext=MainActivity.this;
    private static final int REQUEST = 112;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = ApiClient.getApiclient().create(ApiInterface.class);
        askPermission();
        getIfo();
        desde=0;
        hasta=10;
        stationsRecyclerView = findViewById(R.id.recyclerView_stations);
        progressBar = findViewById(R.id.progress_bar);
        layoutManager = new GridLayoutManager(this, 1);
        stationsRecyclerView.setHasFixedSize(true);
        stationsRecyclerView.setLayoutManager(layoutManager);


        progressBar.setVisibility(View.VISIBLE);



        stationsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = layoutManager.getChildCount();
                totallItemCount = layoutManager.getItemCount();
                pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition();

                if(dy>0){
                    if(isLoading){
                        if(totallItemCount>previous_total){
                            isLoading = false;
                            previous_total = totallItemCount;
                        }
                    }
                    if(!isLoading&&(totallItemCount-visibleItemCount)<=(pastVisibleItem+view_thersehold)){
                        desde=desde+10;
                        performPagination();
                        isLoading=true;
                    }
                }
            }
        });


    }


    private void askPermission(){

        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION};
            if (!hasPermissions(mContext, PERMISSIONS)) {
                ActivityCompat.requestPermissions((MainActivity) mContext, PERMISSIONS, REQUEST );
            } else {
                //call get location here
            }
        } else {
            //call get location here
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //call get location here
                } else {
                    Toast.makeText(mContext, "The app was not allowed to access your location", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void getIfo(){
        Call<DataResponse> call = apiInterface.getInfo();
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if(response.code() == 200){
                    progressBar.setVisibility(View.GONE);
                    final List<Stations> stations;
                    stations = response.body().getStations();
                    adapter = new RecyclerAdapterStations(stations, getApplicationContext());
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            List<Items> itemsSelected = new ArrayList<>(stations.get(stationsRecyclerView.getChildAdapterPosition(v)).getItems());

                            Intent intent = new Intent (MainActivity.this, DetailStationsItem.class);
                            intent.putExtra( "stationSelected", (ArrayList<Items>) itemsSelected);
                            startActivity(intent);

                        }
                    });

                    stationsRecyclerView.setAdapter(adapter);
                }else{
                    Toast.makeText(getApplicationContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void performPagination(){

        progressBar.setVisibility(View.VISIBLE);
        Call<DataResponse> call = apiInterface.getInfo();
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                try{
                    if(response.code() == 200){
                        List<Stations> stations = response.body().getStations();
                        adapter.addStation(stations);
                    }
                    progressBar.setVisibility(View.GONE);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {

            }
        });
    }
}