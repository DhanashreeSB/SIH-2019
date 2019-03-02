package com.example.shyam.fly;

public class placesTourism{

    private String placeName;
    private String placeDescription;
    private int placeImage;

    public placesTourism(String placeName, String placeDescription, int placeImage) {
        this.placeName = placeName;
        this.placeDescription = placeDescription;
        this.placeImage = placeImage;
    }

    public String getplaceName() {
        return placeName;
    }

    public String getplaceDescription() {
        return placeDescription;
    }

    public int getplaceImage() {
        return placeImage;
    }
}
