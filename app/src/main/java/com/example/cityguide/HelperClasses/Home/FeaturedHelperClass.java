package com.example.cityguide.HelperClasses.Home;

public class FeaturedHelperClass {
    int image;
    int rating;
    String title,decription;

    public FeaturedHelperClass(int image, int rating, String title, String decription) {
        this.image = image;
        this.rating = rating;
        this.title = title;
        this.decription = decription;
    }

    public int getImage() {
        return image;
    }

    public int getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDecription() {
        return decription;
    }
}
