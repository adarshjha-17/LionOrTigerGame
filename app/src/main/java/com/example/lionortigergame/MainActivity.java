package com.example.lionortigergame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void imageTapped(View view){

        ImageView imageView = (ImageView) view;
        imageView.setTranslationX(-2000f);
        imageView.setImageResource(R.drawable.tiger);
        imageView.animate().translationXBy(2000f).alpha(1f).rotation(3600f).setDuration(500);


    }
}
