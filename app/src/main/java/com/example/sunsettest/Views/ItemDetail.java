package com.example.sunsettest.Views;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunsettest.Models.Items;
import com.example.sunsettest.Models.Stations;
import com.example.sunsettest.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ItemDetail extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Items items;
    private TextView name, description, state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        name = findViewById(R.id.name_text_view);
        description = findViewById(R.id.description_text_view);
        state = findViewById(R.id.state_text_view);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle extras = getIntent().getExtras();

        items  = extras.getParcelable(DetailStationsItem.ITEM_SELECTED);
        name.setText(items.getName());
        description.setText(items.getDescription());
        state.setText(items.getPlaces_state());
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng location = new LatLng( Double.parseDouble(items.getLat()), Double.parseDouble(items.getLon()));
        mMap.addMarker(new MarkerOptions().position(location).title(items.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }
}
