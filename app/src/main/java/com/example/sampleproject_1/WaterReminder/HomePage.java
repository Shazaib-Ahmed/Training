package com.example.sampleproject_1.WaterReminder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.sampleproject_1.R;
import com.example.sampleproject_1.WaterReminder.Adapter.TabAccessAdapter;
import com.example.sampleproject_1.WaterReminder.Dialog.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

public class HomePage extends AppCompatActivity implements BottomSheetDialog.BottomSheetListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabAccessAdapter tabsAccessAdapter;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        actionBar = getSupportActionBar();
        actionBar.setTitle("Home Page");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#3F51B5"));
        actionBar.setBackgroundDrawable(colorDrawable);

        viewPager = (ViewPager) findViewById(R.id.main_tabs_pager);
        tabsAccessAdapter = new TabAccessAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsAccessAdapter);

        tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onButtonClicked(String text) {

    }
}