package com.example.dell.freerackapp.utils;

import android.icu.lang.UScript;
import android.text.TextUtils;
import android.util.Log;

import com.example.dell.freerackapp.model.Product;
import com.example.dell.freerackapp.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static List<User> fetchJsonUserResponse(String jsonUser) {
        final String USER_ROOT = "";
        final String USER_EMAIL = "email";
        final String USER_PASSWORD = "password";

        if (TextUtils.isEmpty(jsonUser)) {
            Log.d("JSONUts","Json Empty");
            return null;
        }

        List<User> users = new ArrayList<User>();

        try {
            Log.d("String",jsonUser);
            JSONArray userResult = new JSONArray(jsonUser);

            for (int i = 0; i < userResult.length(); i++) {
                JSONObject movieObject = userResult.getJSONObject(i);
                String userEmail = movieObject.optString(USER_EMAIL);
                String userPassword = movieObject.optString(USER_PASSWORD);
                users.add(new User(userEmail, userPassword));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static List<Product> fetchJsonProductResponse(String jsonUser) {
        final String USER_ROOT = "";
        final String USER_NAME = "prod_name";
        final String USER_DESC = "prod_desc";

        if (TextUtils.isEmpty(jsonUser)) {
            Log.d("JSONUts","Json Empty");
            return null;
        }

        List<Product> product = new ArrayList<Product>();

        try {
            Log.d("String",jsonUser);
            JSONArray userResult = new JSONArray(jsonUser);

            for (int i = 0; i < userResult.length(); i++) {
                JSONObject movieObject = userResult.getJSONObject(i);
                String userEmail = movieObject.optString(USER_NAME);
                String userPassword = movieObject.optString(USER_DESC);
                product.add(new Product(userEmail, userPassword));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return product;
    }

}