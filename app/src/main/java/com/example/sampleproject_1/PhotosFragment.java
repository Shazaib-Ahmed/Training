package com.example.sampleproject_1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotosFragment extends Fragment
{

    ProgressDialog progressDialog;
    JsonPlaceHolderApiInterface jsonPlaceHolderApiInterface;
    RecyclerView recyclerView;
    PhotosAdapter photosAdapter;
    Context context;


    public PhotosFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_photos, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context=view.getContext().getApplicationContext();
        recyclerView =view.findViewById(R.id.rv_result22);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        progressDialog = ProgressDialog.show(getActivity(), "Loading...", "Please wait...", true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApiInterface = retrofit.create(JsonPlaceHolderApiInterface.class);

        getPhotos1();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    private void getPhotos1()
    {
        Call<List<PhotoModelClass>> call =jsonPlaceHolderApiInterface.getPhotos();

        call.enqueue(new Callback<List<PhotoModelClass>>() {
            @Override
            public void onResponse(Call<List<PhotoModelClass>> call, Response<List<PhotoModelClass>> response) {

                List<PhotoModelClass> photoModelClasses =response.body();
              progressDialog.dismiss();
                photosAdapter=new PhotosAdapter(context,photoModelClasses);
                recyclerView.setAdapter(photosAdapter);

            }


            @Override
            public void onFailure(Call<List<PhotoModelClass>> call, Throwable t) {
               progressDialog.dismiss();
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
