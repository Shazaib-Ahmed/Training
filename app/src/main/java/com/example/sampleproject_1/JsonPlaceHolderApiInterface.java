package com.example.sampleproject_1;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApiInterface
    {
        @GET("posts")
        Call<List<PostModelClass>> getPosts();

        @GET("photos")
        Call<List<PhotoModelClass>> getPhotos();

        @GET("comments")
        Call<List<CommentModelClass>> getComments();

        @GET("albums")
        Call<List<AlbumModelClass>> getAlbums();

}
