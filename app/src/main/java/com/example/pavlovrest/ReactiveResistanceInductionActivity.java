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

public class ReactiveResistanceInductionActivity extends AppCompatActivity {
    int check = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reactive_resistance_induction);


        final Button    calculateResistanceBtn  = (Button)findViewById(R.id.calculateResistanceBtn);
        final EditText  frequencyText           = (EditText)findViewById(R.id.frequencyNumber);
        final EditText  inductionText           = (EditText)findViewById(R.id.inductionsNumber);
        final EditText  resultField             = (EditText)findViewById(R.id.resultField);
        final Spinner   freqSpinner             = (Spinner)findViewById(R.id.freqSpinner);
        final Spinner   indSpinner              = (Spinner)findViewById(R.id.indSpinner);
        final Spinner   resistSpinner           = (Spinner)findViewById(R.id.resistSpinner);


        final String[] lastResult = {""};
        resultField.setEnabled(false);

        calculateResistanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(frequencyText.getText().toString() == null || inductionText.getText().toString() == null){
                    System.out.println("field is null");
                }
                else {
                    NetworkService.getInstance()
                            .getJsonApi()
                            .getReactResistInd(Integer.parseInt(frequencyText.getText().toString()), freqSpinner.getSelectedItemPosition(), Integer.parseInt(inductionText.getText().toString()),indSpinner.getSelectedItemPosition())
                            .enqueue(new Callback<Post>() {
                                @Override
                                public void onResponse(Call<Post> call, Response<Post> response) {
                                    Post post = response.body();
                                    System.out.println("success");
                                    lastResult[0] = post.getValue();
                                    //String val = new DecimalFormat("#").format(lastResult[0]);
                                    BigDecimal bigDecimal = new BigDecimal(Double.toString(Double.parseDouble(lastResult[0]) / (Math.pow(1000, resistSpinner.getSelectedItemPosition()))));
                                    resultField.setText(bigDecimal.toString());
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
        resistSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(check++ > 0 && !lastResult[0].isEmpty()) {
                    String formattedDouble = new DecimalFormat("#0.0000").format(Double.parseDouble(lastResult[0]) / (Math.pow(1000, i))).replace(",",".");
                    BigDecimal bigDecimal = new BigDecimal(formattedDouble);
                    resultField.setText(bigDecimal.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}