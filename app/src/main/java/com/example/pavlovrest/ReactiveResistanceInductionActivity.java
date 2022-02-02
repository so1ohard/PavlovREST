package com.example.pavlovrest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReactiveResistanceInductionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reactive_resistance_induction);

        final Button calculateResistanceBtn = (Button)findViewById(R.id.calculateResistanceBtn);
        final EditText frequencyText = (EditText)findViewById(R.id.frequencyNumber);
        final EditText inductionText = (EditText)findViewById(R.id.inductionsNumber);


        calculateResistanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(frequencyText.getText().toString() == null || inductionText.getText().toString() == null){
                    System.out.println("field is null");

                }
                else {
                    NetworkService.getInstance()
                            .getJsonApi()
                            .getReactResistInd(Integer.parseInt(frequencyText.getText().toString()), Integer.parseInt(inductionText.getText().toString()))
                            .enqueue(new Callback<Post>() {
                                @Override
                                public void onResponse(Call<Post> call, Response<Post> response) {
                                    Post post = response.body();
                                    System.out.println("success");
                                    post.getValue()
                                }

                                @Override
                                public void onFailure(Call<Post> call, Throwable t) {
                                    System.out.println("error");
                                    t.printStackTrace();
                                }
                            });
                }
            }
        });
    }
}