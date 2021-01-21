package com.example.sampleproject_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


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
    RecyclerView recyclerView,rvEndPoints;
    PhotosAdapter photosAdapter;
    EndPointAdapter endPointAdapter;
    Context context;
    String data[] = {"hello","hey"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_place_holder_api);

        //recyclerView=findViewById(R.id.rv_result);
        rvEndPoints=findViewById(R.id.rv_endpoints);
        //textResult=findViewById(R.id.text_result);
        context=this;
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvEndPoints.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        endPointAdapter=new EndPointAdapter(dataQueue(),JsonPlaceHolderApi.this);
        rvEndPoints.setAdapter(endPointAdapter);
        progressDialog=new ProgressDialog(this);
        setTitle("Json Placeholder");

       /* progressDialog.setTitle("Loading ..");
        progressDialog.setMessage("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApiInterface = retrofit.create(JsonPlaceHolderApiInterface.class);*/

        //getPosts1();
        //getPhotos1();


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
                Toast.makeText(JsonPlaceHolderApi.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

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

    public ArrayList<EndPointModel> dataQueue()
    {
        ArrayList<EndPointModel> holder =new ArrayList<>();

        EndPointModel ob1 = new EndPointModel();
        ob1.setEndpointText("Post");
        holder.add(ob1);

        EndPointModel ob2 = new EndPointModel();
        ob2.setEndpointText("Photos");
        holder.add(ob2);

        EndPointModel ob3 = new EndPointModel();
        ob3.setEndpointText("Comments");
        holder.add(ob3);

        EndPointModel ob4 = new EndPointModel();
        ob4.setEndpointText("Album");
        holder.add(ob4);

        EndPointModel ob5 = new EndPointModel();
        ob5.setEndpointText("Album12345678");
        holder.add(ob5);

        return holder;
    }

}