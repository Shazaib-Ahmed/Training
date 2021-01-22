package com.example.sampleproject_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;



public class JsonPlaceHolderApi extends AppCompatActivity {


    RecyclerView rvEndPoints;
    EndPointAdapter endPointAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_place_holder_api);


        rvEndPoints=findViewById(R.id.rv_endpoints);
        context=this;
        rvEndPoints.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        endPointAdapter=new EndPointAdapter(dataQueue(),JsonPlaceHolderApi.this);
        rvEndPoints.setAdapter(endPointAdapter);
        setTitle("Json Placeholder");
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

        return holder;
    }

    public void updateFragmentForPhotos() {
        PhotosFragment photosFragment =new PhotosFragment();
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.frame_layout123,photosFragment)
                .commit();
    }
    public void updateFragmentForPost() {
        PostFragment postFragment =new PostFragment();
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.frame_layout123,postFragment)
                .commit();
    }
    public void updateFragmentForComments() {
        CommentFragment commentFragment =new CommentFragment();
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.frame_layout123,commentFragment)
                .commit();
    }
    public void updateFragmentForAlbums() {
        AlbumFragment albumFragment =new AlbumFragment();
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.frame_layout123,albumFragment)
                .commit();
    }

}