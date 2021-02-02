package com.example.sampleproject_1.WaterReminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.sampleproject_1.R;
import com.example.sampleproject_1.WaterReminder.Adapter.TabAccessAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomePage extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabAccessAdapter tabsAccessAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        viewPager=(ViewPager)findViewById(R.id.main_tabs_pager);
        tabsAccessAdapter=new TabAccessAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsAccessAdapter);

        tabLayout=(TabLayout)findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}