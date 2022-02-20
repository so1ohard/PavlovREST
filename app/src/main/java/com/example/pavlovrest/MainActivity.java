package com.example.pavlovrest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button trBtn = (Button)findViewById(R.id.transformatorBtn);
        Button reactResistInd = (Button)findViewById(R.id.reactiveResistanceBtn);
        Button reactResistCap = (Button)findViewById(R.id.reactResistanceCapBtn);

        reactResistInd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReactiveResistanceInductionActivity.class);
                startActivity(intent);
            }
        });
        trBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, TransformatorActivity.class);
//                startActivity(intent);
//                if (isOnline()) {
//                    System.out.println("yes");
//                } else {
//                    System.out.println("no");
//                }

            }
        });
        reactResistCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReactiveResistanceCapacitor.class);
                startActivity(intent);
            }
        });

    }
    protected boolean isOnline() {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(cs);
        if (cm.getActiveNetworkInfo() == null) {
            return false;
        } else {
            return true;
        }
    };
}