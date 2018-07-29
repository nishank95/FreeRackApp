package com.example.dell.freerackapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.dell.freerackapp.model.User;
import com.example.dell.freerackapp.utils.JsonUtils;
import com.example.dell.freerackapp.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends AppCompatActivity {

    final String BASE_URL = "http://ec2-34-215-161-202.us-west-2.compute.amazonaws.com/FreeRack/api/actions/retrieveUsers.php";
    @BindView(R.id.name_input)TextView userName;
    @BindView(R.id.password_input)TextView userPassword;
    @BindView(R.id.form_error)TextView userFormError;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        userFormError.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.signup_btn)
    public void onSignUpClick(View v){
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        this.startActivity(intent);
    }

    @OnClick(R.id.login_btn)
    public void onLoginClick(View v){
        if(checkInternetConnectivity()){
            if(validForm()){
                new FetchAuthTask().execute();
            }
            else{
                userFormError.setVisibility(View.VISIBLE);
                userFormError.setText("Ensure all fields are not empty!");
            }

        }
}

    private boolean validForm() {

    String uname = userName.getText().toString();
    String pass = userPassword.getText().toString();
    if(!uname.equals("") && uname != null){
        if(!pass.equals("") && pass != null){
            return true;
        }
    }
    return false;
    }

    public boolean checkInternetConnectivity(){
        //Check internet connection//
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    @SuppressLint("StaticFieldLeak")
    private class FetchAuthTask extends AsyncTask<Void,Void,List<User>> {

        @Override
        protected List<User> doInBackground(Void... voids) {

            String jsonResponse = null;

            try {
                URL url = new URL(BASE_URL);
                jsonResponse = NetworkUtils.httpConnectionResponse(url);
                Log.e("BG", url.toString());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return JsonUtils.fetchJsonUserResponse(jsonResponse);

        }

        @Override
        protected void onPostExecute(List<User> user) {
            if(user!= null){
                if(user.size() > 0){
                checkUserAuth(user);
                }
            }
//            else{
//                mvCastErrorTextView.setVisibility(View.VISIBLE);
//            }
        }
    }

    public void checkUserAuth(List<User> user){

        for(int i=0; i<user.size();i++){
            User userData = user.get(i);
//            Log.d("USER:   " ,userData.getUserEmail());
            if(checkUserExist(userData.getUserEmail())){
//                Log.d("USER" ,"USer Exists!");
            if(verifyPassword(userData.getUserPassword())){
                Log.d("USER" ,"User Authenticated!");
                        userFormError.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        this.startActivity(intent);
                        break;
            }
            }
            else{
//                Log.d("USER" ,"No User!");
                    userFormError.setVisibility(View.VISIBLE);
                  userFormError.setText("User Authentication Problem.");
            }
        }
    }

    private boolean verifyPassword(String password) {

        String md5Password = md5(userPassword.getText().toString());
        return password.equals(md5Password);
    }

    boolean checkUserExist(String username){
        if(username.equals(userName.getText().toString())){
            return true;
        }
        return false;
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


}
