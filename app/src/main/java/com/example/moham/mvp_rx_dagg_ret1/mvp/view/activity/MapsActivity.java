package com.example.moham.mvp_rx_dagg_ret1.mvp.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.moham.mvp_rx_dagg_ret1.PermissionUtils;
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
    Button callBtn ,smsBtn,emailBtn;
    Toolbar toolbar;
    RecyclerView.LayoutManager  manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComoponents();

        toolbar.inflateMenu(R.menu.main_menu);
         manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);

        presenter = new MainActivityPresenter(this);
        presenter.loadFriends();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void initializeComoponents() {
        recyclerView = findViewById(R.id.recycler);
        callBtn = findViewById(R.id.call_btn);
        smsBtn = findViewById(R.id.sms_btn);
        emailBtn = findViewById(R.id.mail_btn);
        toolbar = findViewById(R.id.toolbar);
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
        recyclerView.scrollToPosition(1);
        LatLng latLng = new LatLng(friend.getLocation()[0], friend.getLocation()[1]);
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng).title(friend.getFirst_name() + " Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onCall(ResponseFriends friends) {
        if (!PermissionUtils.isCallProvided(this)) {
            return;
        }
        Toast.makeText(this, "calling " + friends.getFirst_name(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + friends.getCall()));
        startActivity(intent);

    }

    @Override
    public void onSms(String number) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:" + number));
        startActivity(intent);
    }

    @Override
    public void onEmail(String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        startActivity(emailIntent);
    }


    public void call(View view) {
        PermissionUtils.callPermission(this);
        presenter.onCallClicked();

    }

    public void sendEmail(View view) {
        presenter.onEmailClicked();
    }

    public void sendSms(View view) {
        presenter.onSmsClicked();
    }
}
