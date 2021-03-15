package com.androcid.zomato.activity.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LifecycleRegistryOwner;

import com.androcid.zomato.R;

import static com.androcid.zomato.util.Unbinding.unbindDrawables;


/**
 * Created by Androcid-6 on 15-02-2017.
 */

public class ClearBaseActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private final Context context = ClearBaseActivity.this;
    private static final String TAG = ClearBaseActivity.class.getSimpleName();

    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unbindDrawables(findViewById(R.id.root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
