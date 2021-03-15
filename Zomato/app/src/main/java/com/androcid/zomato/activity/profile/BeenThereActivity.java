package com.androcid.zomato.activity.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androcid.zomato.R;
import com.androcid.zomato.activity.PlaceDetailActivity;
import com.androcid.zomato.model.RestaurantItem;
import com.androcid.zomato.model.room.UserLocation;
import com.androcid.zomato.preference.LocationPreference;
import com.androcid.zomato.retro.RestaurantResponse;
import com.androcid.zomato.retro.RetroInterface;
import com.androcid.zomato.util.Constant;
import com.androcid.zomato.view.adapter.PlacesRateAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class BeenThereActivity extends AppCompatActivity {

    private static final String TAG = BeenThereActivity.class.getSimpleName();
    private final Context context = BeenThereActivity.this;

    public static Intent getCallIntent(Context context, String user_id) {
        Intent intent = new Intent(context, BeenThereActivity.class);
        intent.putExtra(Constant.USER_ID, user_id);
        return intent;
    }

    String user_id;

    RecyclerView recyclerView;
    List<RestaurantItem> allList;
    PlacesRateAdapter allAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_been_there);

        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Been There");
        toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white));

        user_id = getIntent().getStringExtra(Constant.USER_ID);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        allList = new ArrayList<>();
        allAdapter = new PlacesRateAdapter(context, allList);
        allAdapter.setClickListener(new PlacesRateAdapter.ClickListener() {
            @Override
            public void onItemClickListener(View v, int pos) {

                RestaurantItem item = allList.get(pos);
                startActivity(PlaceDetailActivity.getCallIntent(context, item));
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(allAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getBennThereRestaurants();
    }

    private void getBennThereRestaurants() {

        UserLocation location = LocationPreference.getUserLocation(context);
        RetroInterface.getZomatoRestApi().getBeenThereRestaurants(
                user_id + "",
                location.getLatitude() + "",
                location.getLongitude() + "",
                new Callback<RestaurantResponse>() {
                    @Override
                    public void success(RestaurantResponse restaurantResponse, Response response) {
                        if (restaurantResponse != null && restaurantResponse.isSuccess()) {
                            allList = restaurantResponse.getItems();
                            allAdapter.refresh(allList);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                }
        );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
