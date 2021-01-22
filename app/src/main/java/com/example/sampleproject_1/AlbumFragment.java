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

public class AlbumFragment extends Fragment
{

    JsonPlaceHolderApiInterface jsonPlaceHolderApiInterface;
    RecyclerView recyclerView;
    AlbumAdapter albumAdapter;
    Context context;
    ProgressDialog progressDialog;


    public AlbumFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_albums, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context=view.getContext().getApplicationContext();
        recyclerView =view.findViewById(R.id.rv_albums);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        progressDialog = ProgressDialog.show(getActivity(), "Loading...", "Please wait...", true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApiInterface = retrofit.create(JsonPlaceHolderApiInterface.class);

        getAlbum();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    private void getAlbum()
    {
        Call<List<AlbumModelClass>> call =jsonPlaceHolderApiInterface.getAlbums();

        call.enqueue(new Callback<List<AlbumModelClass>>() {
            @Override
            public void onResponse(Call<List<AlbumModelClass>> call, Response<List<AlbumModelClass>> response) {

                List<AlbumModelClass> albumModelClasses =response.body();
                progressDialog.dismiss();
                albumAdapter=new AlbumAdapter(context,albumModelClasses);
                recyclerView.setAdapter(albumAdapter);

            }


            @Override
            public void onFailure(Call<List<AlbumModelClass>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
