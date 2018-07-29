package com.example.dell.freerackapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    CarouselView carouselView;
    @BindView(R.id.product_name)TextView pName;
    @BindView(R.id.product_location)TextView pLoc;


    int[] sampleImages = {R.drawable.i1,R.drawable.i2,R.drawable.i3};

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        String name= getIntent().getStringExtra("name");
        String loc= getIntent().getStringExtra("loc");
        pName.setText(name);
        pLoc.setText(loc);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

}


