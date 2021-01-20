package com.example.sampleproject_1;

class PhotoModelClass   {

    private int albumId;
    private int id;
    private String url;
    private String thumbnailUrl;
    private String title;



    /*@SerializedName("body")
    private String text;*/

    public int getAlbumId() {
        return albumId;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }
}
