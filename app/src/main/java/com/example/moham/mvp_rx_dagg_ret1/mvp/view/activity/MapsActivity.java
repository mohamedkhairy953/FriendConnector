package com.example.moham.mvp_rx_dagg_ret1.mvp.view.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

import com.example.moham.mvp_rx_dagg_ret1.R;
import com.example.moham.mvp_rx_dagg_ret1.model.ResponseFriends;
import com.example.moham.mvp_rx_dagg_ret1.mvp.adapter.RcycleAdapter;
import com.example.moham.mvp_rx_dagg_ret1.mvp.presenter.fetch_friends.MainActivityPresenter;
import com.example.moham.mvp_rx_dagg_ret1.mvp.view.ImainView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ImainView {

    private GoogleMap mMap;
    private MainActivityPresenter presenter;

    RecyclerView recyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main_menu);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        presenter = new MainActivityPresenter(this);
        presenter.loadFriends();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onFriendsLoadSucces(List<ResponseFriends> list) {
        presenter.setFriendsList(list);
        RcycleAdapter adapter = new RcycleAdapter(presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFriendsLoadFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemClicked(Button btn, ResponseFriends friend) {
        recyclerView.smoothScrollBy(200,0);
        LatLng latLng = new LatLng(friend.getLocation()[0], friend.getLocation()[1]);
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng).title(friend.getFirst_name() + " Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

}
