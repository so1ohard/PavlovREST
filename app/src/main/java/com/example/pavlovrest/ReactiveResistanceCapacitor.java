package com.example.pavlovrest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReactiveResistanceCapacitor extends AppCompatActivity {
    int check = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reactive_resistance_capacitor);

        final Button    calculateResistanceCapBtn   = (Button)findViewById(R.id.calculateResistanceCapBtn);
        final EditText  frequencyCapText            = (EditText)findViewById(R.id.frequencyCapNumber);
        final EditText  capacitorText               = (EditText)findViewById(R.id.CapNumber);
        final EditText  resultCap                   = (EditText)findViewById(R.id.resultCap);
        final Spinner   freqCapSpinner              = (Spinner)findViewById(R.id.freqCapSpinner);
        final Spinner   capSpinner                  = (Spinner)findViewById(R.id.capSpinner);
        final Spinner   resistCapSpinner            = (Spinner)findViewById(R.id.resistCapSpinner);

        final String[] lastResult = {""};
        resultCap.setEnabled(false);

        calculateResistanceCapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(frequencyCapText.getText().toString() == null || capacitorText.getText().toString() == null){
                    System.out.println("field is null");
                }
                else {
                    NetworkService.getInstance()
                            .getJsonApi()
                            .getReactResistCap(Integer.parseInt(frequencyCapText.getText().toString()), freqCapSpinner.getSelectedItemPosition(), Integer.parseInt(capacitorText.getText().toString()),capSpinner.getSelectedItemPosition())
                            .enqueue(new Callback<Post>() {
                                @Override
                                public void onResponse(Call<Post> call, Response<Post> response) {
                                    Post post = response.body();
                                    System.out.println("success");
                                    lastResult[0] = post.getValue();
                                    //String val = new DecimalFormat("#").format(lastResult[0]);
                                    BigDecimal bigDecimal = new BigDecimal(Double.toString(Double.parseDouble(lastResult[0]) / (Math.pow(1000, resistCapSpinner.getSelectedItemPosition()))));
                                    resultCap.setText(bigDecimal.toString());
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
        resistCapSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(check++ > 0 && !lastResult[0].isEmpty()) {
                    String formattedDouble = new DecimalFormat("#0.000000").format(Double.parseDouble(lastResult[0]) / (Math.pow(1000, i))).replace(",",".");
                    BigDecimal bigDecimal = new BigDecimal(formattedDouble);
                    resultCap.setText(bigDecimal.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}