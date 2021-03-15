package com.androcid.zomato.activity.friend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androcid.zomato.R;
import com.androcid.zomato.model.User;
import com.androcid.zomato.view.adapter.FriendAdapter;

import java.util.ArrayList;
import java.util.List;


public class FriendZomatoActivity extends AppCompatActivity {

    private static final String TAG = FriendZomatoActivity.class.getSimpleName();
    private final Context context = FriendZomatoActivity.this;

    public static Intent getCallIntent(Context context) {
        Intent intent = new Intent(context, FriendZomatoActivity.class);
        return intent;
    }

    RecyclerView recyclerView;
    List<User> allList;
    FriendAdapter allAdapter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_search);

        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Find friends on Zomato");
        toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        allList = new ArrayList<>();
        allAdapter = new FriendAdapter(context, allList);
        /*allAdapter.setClickListener(new FriendAdapter.ClickListener() {
            @Override
            public void onItemClickListener(View v, int pos) {

            }

            @Override
            public void onFriendListener(int pos, boolean isFollowing) {

            }
        });*/

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(allAdapter);

        setList();
    }

    private void setList() {

        allAdapter.refresh(allList);

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
