package com.example.dell.freerackapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.params.HttpConnectionParams;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    final String BASE_URL = "http://ec2-34-215-161-202.us-west-2.compute.amazonaws.com/FreeRack/api/actions/createUsersDetails.php";
    @BindView(R.id.signup_btn)
    Button signup_btn;
    @BindView(R.id.name_input)
    EditText mName;
    @BindView(R.id.email_input)
    EditText mEmail;
    @BindView(R.id.phone_input)
    EditText mPhone;
    @BindView(R.id.password_input)
    EditText mPassword;
    @BindView(R.id.confirm_password_input)
    EditText mRePassword;
    @BindView(R.id.dob_input)
    EditText mDob;

    String name, lastname, mail, phone, password, dob, jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.signup_btn)
    void onProceedClick(View v) {
        setDetails();
        jsonString = formatDataAsJson();
        new CallAPI().execute(BASE_URL,jsonString);
    }

    private String formatDataAsJson() {
        final JSONObject object = new JSONObject();
        try {
//            object.put("fname",name);
//            object.put("lname",name);
//            object.put("dob",dob);
//            object.put("email",mail);
//            object.put("password",password);
//            object.put("contact_info",phone);
//

            object.put("fname", "sadsd");
            object.put("lname", "Asasd");
            object.put("dob", "2013-02-02");
            object.put("email", "asasda@gmail.com");
            object.put("password", password);
            object.put("contact_info", 48686888);


            return object.toString(1);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void setDetails() {
        name = mName.getText().toString();
        lastname = mName.getText().toString();
        mail = mEmail.getText().toString();
        phone = mPhone.getText().toString();
        password = mPassword.getText().toString();
        password = md5(password);
        dob = mDob.getText().toString();
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


    public class CallAPI extends AsyncTask<String, Void, Void> {

        public CallAPI() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... params) {
            String urlString = params[0]; // URL to call
            String data = params[1]; //data to post
//            OutputStream out = null;
//
//            try {
//                URL url = new URL(urlString);
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setDoInput(true);
//                urlConnection.setDoOutput(true);
//
//                out = new BufferedOutputStream(urlConnection.getOutputStream());
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
//                writer.write(data);
//                writer.flush();
//                writer.close();
//                out.close();
//                urlConnection.connect();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//            return null;
            OutputStream os = null;
            InputStream is = null;
            HttpURLConnection conn = null;
            try {
                //constants
                URL url = new URL(BASE_URL);

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /*milliseconds*/);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setFixedLengthStreamingMode(data.getBytes().length);

                //make some HTTP header nicety
                conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

                //open
                conn.connect();

                //setup send
                os = new BufferedOutputStream(conn.getOutputStream());
                os.write(data.getBytes());
                //clean up
                os.flush();

                //do somehting with response
                is = conn.getInputStream();
//                String contentAsString = readIt(is,len);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //clean up
                try {
                    assert os != null;
                    os.close();
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                conn.disconnect();
            }

            return null;
        }
    }

}