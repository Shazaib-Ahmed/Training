package com.example.sampleproject_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonPlaceHolderApi extends AppCompatActivity {

    ProgressDialog progressDialog;
    TextView textResult;
    JsonPlaceHolderApiInterface jsonPlaceHolderApiInterface;
    RecyclerView recyclerView;
    PhotosAdapter photosAdapter;
    ArrayList<PhotoModelClass> data= new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_place_holder_api);

        recyclerView=findViewById(R.id.rv_result);
        textResult=findViewById(R.id.text_result);
        context=this;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog=new ProgressDialog(this);
        setTitle("Json Placeholder" +
                "");

        progressDialog.setTitle("Loading ..");
        progressDialog.setMessage("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApiInterface = retrofit.create(JsonPlaceHolderApiInterface.class);

        //getPosts1();
        getPhotos1();

    }

    private void getPhotos1()
    {
        Call<List<PhotoModelClass>> call =jsonPlaceHolderApiInterface.getPhotos();

        call.enqueue(new Callback<List<PhotoModelClass>>() {
            @Override
            public void onResponse(Call<List<PhotoModelClass>> call, Response<List<PhotoModelClass>> response) {
                if (!response.isSuccessful())
                {
                    data=new ArrayList<>(response.body());
                    return;
                }
                List<PhotoModelClass> photoModelClasses =response.body();
                for (PhotoModelClass photoModelClass : photoModelClasses)
                {
                    photosAdapter=new PhotosAdapter(context,photoModelClasses);
                    recyclerView.setAdapter(photosAdapter);

                    progressDialog.dismiss();
                }

            }



            @Override
            public void onFailure(Call<List<PhotoModelClass>> call, Throwable t) {

            }
        });
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
               textResult.setText(t.getMessage());
            }
        });
    }

}