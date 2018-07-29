package com.example.dell.freerackapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostProduct extends AppCompatActivity {

    @BindView(R.id.product_name)EditText name;
    @BindView(R.id.product_description)EditText desc;
    @BindView(R.id.product_category)EditText category;
    @BindView(R.id.productLocation)EditText location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_product);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.postBtn)
    void onPostClick(View v){
        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("name",name.getText().toString());
        i.putExtra("desc",desc.getText().toString());
        i.putExtra("cat",category.getText().toString());
        i.putExtra("loc",location.getText().toString());
        startActivity(i);
    }

}
