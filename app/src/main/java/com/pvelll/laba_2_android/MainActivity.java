package com.pvelll.laba_2_android;

import static java.lang.Math.E;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private boolean isFormula1;
    private double memory1;
    private double memory2;
    private double memory3;
    private double sum;
    private Button buttonFormula1;
    private Button buttonFormula2;
    private Button buttonMemory1;
    private Button buttonMemory2;
    private Button buttonMemory3;
    private Button buttonMemoryClear;
    private Button buttonMemoryPlus;
    private Button buttonSolve;
    private Button buttonClear;

    private ImageView formulaPic;

    private EditText editX;
    private EditText editY;
    private EditText editZ;

    private TextView resultText;
    private TextView memorySumText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isFormula1 = false;

        buttonFormula1 = findViewById(R.id.buttonFormula1);
        buttonFormula2 = findViewById(R.id.buttonFormula2);
        buttonMemory1 = findViewById(R.id.buttonMemory1);
        buttonMemory2 = findViewById(R.id.buttonMemory2);
        buttonMemory3 = findViewById(R.id.buttonMemory3);
        buttonMemoryClear = findViewById(R.id.buttonMemoryClear);
        buttonMemoryPlus = findViewById(R.id.buttonMemoryPlus);
        buttonSolve = findViewById(R.id.buttonSolve);
        buttonClear = findViewById(R.id.buttonClear);

        formulaPic = findViewById(R.id.formulaPic);

        editX = findViewById(R.id.editX);
        editY = findViewById(R.id.editY);
        editZ = findViewById(R.id.editZ);

        resultText = findViewById(R.id.resultText);
        memorySumText = findViewById(R.id.memorySumText);

        buttonFormula1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFormula1) {
                    formulaPic.setImageResource(R.drawable.formula_1);
                    isFormula1 = true;
                }
            }
        });

        buttonFormula2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFormula1) {
                    formulaPic.setImageResource(R.drawable.formula_2);
                    isFormula1 = false;
                }
            }
        });

        buttonSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double x = Double.parseDouble(editX.getText().toString());
                double y = Double.parseDouble(editY.getText().toString());
                double z = Double.parseDouble(editZ.getText().toString());
                if(isFormula1){
                    sum = formula1(x,y,z);
                } else {
                    sum = formula2(x,y,z);
                }

            }
        });
    }

    public Double formula1(Double x, Double y, Double z) {
        Toast.makeText(this, "y не может быть меньше нуля", Toast.LENGTH_SHORT).show();
        return (sin(log(y) + sin(PI * y * y)) * pow(x * x + sin(z) + pow(E, cos(z)), 0.25));
    }
    public Double formula2(Double x, Double y, Double z) {
        if (y == -1) {
            Toast.makeText(this, "y не должен быть равен -1", Toast.LENGTH_SHORT).show();
            return 0.0;
        }
        if ((cos(exp(y)) + exp(y * y) + sqrt(1 / x)) < 0) {
            Toast.makeText(this, "Выражение (cos(exp(y)) + exp(y*y) + sqrt(1/x) должно быть больше 0", Toast.LENGTH_SHORT).show();
            return 0.0;
        }
        return pow((cos(exp(y)) + log((1 + y) * (1 + y)) + sqrt(exp(cos(x)) + sin(PI * z) * sin(PI * z)) + sqrt(1 / x) + cos(y * y)), sin(z));
    }
}