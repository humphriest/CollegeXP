package com.thump.collegexp;

import java.util.ArrayList;

public class College {

    int id;
    String name;
    String address;
    ArrayList<String> courses;
    String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public College(){}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/**/
    public College(int id, String name, String address, ArrayList<String> courses, String imageUrl){
        this.id = id;
        this.name = name;
        this.address = address;
        this.courses = courses;
        this.imageUrl = imageUrl;
    }
}