package com.drstudios.icecreamorder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button calculate;
    ImageView up, down, iceCreamImage;
    TextView totalCostTextView;
    EditText quantity;
    RadioButton cup, cone;
    int quantityText = 0;
    double totalCost = 0;
    DecimalFormat decimalFormat;
    public static final String prefName = "SharedPrefsFile";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculate = findViewById(R.id.calculateBtn);
        up = findViewById(R.id.upBtn);
        iceCreamImage = findViewById(R.id.iceCreamImageView);
        down = findViewById(R.id.downBtn);
        cup = findViewById(R.id.cupRadio);
        cone = findViewById(R.id.coneRadio);
        quantity = findViewById(R.id.quantityEditTxt);
        totalCostTextView = findViewById(R.id.totalCostTextView);
        decimalFormat = new DecimalFormat("0.00");

        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = getSharedPreferences(prefName, MODE_PRIVATE).edit();

        SharedPreferences preferences = getSharedPreferences(prefName, MODE_PRIVATE);
        String selectedRadio = preferences.getString("SelectedRadio", "null");
        if (selectedRadio.equals("Cup")){
            cup.setSelected(true);
            calculate.setEnabled(true);
        } else if (selectedRadio.equals("Cone")){
            cone.setSelected(true);
            calculate.setEnabled(true);
        }

        int getQuantity = preferences.getInt("Quantity", 0);
        quantity.setText(Integer.toString(getQuantity));
        quantityText = getQuantity;

        String total = preferences.getString("Total", "$0.00");
        totalCostTextView.setText(total);





        cup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate.setEnabled(true);
                iceCreamImage.setImageResource(R.drawable.ice_cream_cup);
                editor.putString("SelectedRadio", "Cup");
                editor.apply();
            }
        });

        cone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate.setEnabled(true);
                iceCreamImage.setImageResource(R.drawable.ice_cream_cone);
                editor.putString("SelectedRadio", "Cone");
                editor.apply();
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantityText ++;
                quantity.setText(Integer.toString(quantityText));
                editor.putInt("Quantity", quantityText);
                editor.apply();
            }
        });


        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(quantityText <= 0)){
                    quantityText --;
                    quantity.setText(Integer.toString(quantityText));
                    editor.putInt("Quantity", quantityText);
                    editor.apply();
                } else
                    Toast.makeText(getApplicationContext(), "Quantity cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (cup.isChecked()){
                    totalCost = 3.49 * quantityText;
                    totalCostTextView.setText("$" + decimalFormat.format(totalCost));
                    editor.putString("Total", totalCostTextView.getText().toString());
                    editor.apply();
                } else {
                    totalCost = 3.99 * quantityText;
                    totalCostTextView.setText("$" + decimalFormat.format(totalCost));
                    editor.putString("Total", totalCostTextView.getText().toString());
                    editor.apply();
                }
            }
        });



    }
}