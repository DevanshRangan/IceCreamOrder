package com.drstudios.icecreamorder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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



        cup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate.setEnabled(true);
                iceCreamImage.setImageResource(R.drawable.ice_cream_cup);
            }
        });

        cone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate.setEnabled(true);
                iceCreamImage.setImageResource(R.drawable.ice_cream_cone);
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantityText ++;
                quantity.setText(Integer.toString(quantityText));
            }
        });


        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(quantityText <= 0)){
                    quantityText --;
                    quantity.setText(Integer.toString(quantityText));
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
                } else {
                    totalCost = 3.99 * quantityText;
                    totalCostTextView.setText("$" + decimalFormat.format(totalCost));
                }
            }
        });



    }
}