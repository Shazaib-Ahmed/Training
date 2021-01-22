package com.example.sampleproject_1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

public class PostFragment extends Fragment
{

    ProgressDialog progressDialog;
    JsonPlaceHolderApiInterface jsonPlaceHolderApiInterface;
    RecyclerView recyclerView;
    PhotosAdapter photosAdapter;
    Context context;
    TextView textResult;


    public PostFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_post, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context=view.getContext().getApplicationContext();
        textResult =view.findViewById(R.id.textResult);



        progressDialog = ProgressDialog.show(getActivity(), "Loading...", "Please wait...", true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApiInterface = retrofit.create(JsonPlaceHolderApiInterface.class);

        getPosts1();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    private void getPosts1() {
        Call<List<PostModelClass>> call =jsonPlaceHolderApiInterface.getPosts();

        call.enqueue(new Callback<List<PostModelClass>>() {
            @Override
            public void onResponse(Call<List<PostModelClass>> call, Response<List<PostModelClass>> response) {
                if (!response.isSuccessful())
                {
                    textResult.setText("Code: "+response.code());
                    return;
                }
                List<PostModelClass> postModelClasses =response.body();
                for (PostModelClass postModelClass : postModelClasses)
                {
                    String content ="";
                    content +="ID: "+ postModelClass.getId() +"\n";
                    content +="User ID: "+ postModelClass.getUserId() +"\n";
                    content +="Title: "+ postModelClass.getTitle() +"\n";
                    content +="Text: "+ postModelClass.getText() +"\n"+"\n";

                    textResult.append(content);
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<PostModelClass>> call, Throwable t) {
                progressDialog.dismiss();
                textResult.setText(t.getMessage());
            }
        });
    }


}
