package com.androcid.zomato.activity.support;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.androcid.zomato.R;


/**
 *
 */
public class SupportActivity extends AppCompatActivity {

    private static final String TAG = SupportActivity.class.getSimpleName();
    private final Context context = SupportActivity.this;

    public static Intent getCallIntent(Context context) {
        Intent intent = new Intent(context, SupportActivity.class);
        return intent;
    }

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chat");
        toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white));
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


    public void onlineOrderClick(View view) {

        startActivity(SupportChatActivity.getCallIntent(context));
    }
}