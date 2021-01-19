package com.example.sampleproject_1;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment
{
    Button addWater;

    public HomeFragment()
    {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    int progress=0;

    ProgressBar progressBar;
    TextView progressText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Button addWater =v.findViewById(R.id.add_water);
        progressText = v.findViewById(R.id.progress_text);
        progressBar=v.findViewById(R.id.progress_bar);
        addWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (progress<=90)
                {
                    progress+=10;
                    updateProgressBar();


                }
           }
        });
return v;

    }
private void updateProgressBar()
{
    progressBar.setProgress(progress);

}
}
