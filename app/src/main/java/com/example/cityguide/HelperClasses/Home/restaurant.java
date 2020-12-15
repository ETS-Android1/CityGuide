package com.example.cityguide.HelperClasses.Home;

public class restaurant {


    private String restName;
    private String image;
    private String description;
    private String address;
    private String Customercount;
    private  String starsTillNow;

    public restaurant()
    {

    }

    public restaurant(String restName, String image, String description, String address, String customercount, String starsTillNow) {
        this.restName = restName;
        this.image = image;
        this.description = description;
        this.address = address;
        Customercount = customercount;
        this.starsTillNow = starsTillNow;
    }

    public String getCustomercount() {
        return Customercount;
    }

    public void setCustomercount(String customercount) {
        Customercount = customercount;
    }

    public String getStarsTillNow() {
        return starsTillNow;
    }

    public void setStarsTillNow(String starsTillNow) {
        this.starsTillNow = starsTillNow;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
