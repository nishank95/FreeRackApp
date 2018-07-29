package com.example.dell.freerackapp.model;

public class Product {

    String mProductName;
    String mProductDescription;

    public Product(String name, String desc){
        mProductName = name;
        mProductDescription = desc;
    }

    public String getmProductDescription() {
        return mProductDescription;
    }

    public String getmProductName() {
        return mProductName;
    }
}
