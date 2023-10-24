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
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    private Button buttonMemoryClear;
    private Button buttonMemoryPlus;
    private Button buttonSolve;
    private Button buttonClear;
    private CheckBox checkMemory1;
    private CheckBox checkMemory2;
    private CheckBox checkMemory3;
    private ImageView formulaPic;

    private EditText editX;
    private EditText editY;
    private EditText editZ;

    private TextView resultText;
    private TextView memorySumText;
    private int checkedCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isFormula1 = true;
        buttonFormula1 = findViewById(R.id.buttonFormula1);
        buttonFormula2 = findViewById(R.id.buttonFormula2);
        buttonMemoryClear = findViewById(R.id.buttonMemoryClear);
        buttonMemoryPlus = findViewById(R.id.buttonMemoryPlus);
        buttonSolve = findViewById(R.id.buttonSolve);
        buttonClear = findViewById(R.id.buttonClear);

        checkMemory1 = findViewById(R.id.checkMemory1);
        checkMemory2 = findViewById(R.id.checkMemory2);
        checkMemory3 = findViewById(R.id.checkMemory3);

        formulaPic = findViewById(R.id.formulaPic);

        editX = findViewById(R.id.editX);
        editY = findViewById(R.id.editY);
        editZ = findViewById(R.id.editZ);

        resultText = findViewById(R.id.resultText);
        memorySumText = findViewById(R.id.memorySumText);

        CheckBox[] checkBoxes = {checkMemory1, checkMemory2, checkMemory3};
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkedCount++;
                } else {
                    checkedCount--;
                }
                if (checkedCount > 1) {
                    buttonView.setChecked(false);
                    Toast.makeText(MainActivity.this, "Вы можете выбрать только одну память", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkMemory1.isChecked()) {
                        memorySumText.setText(Double.toString(memory1));
                    }
                    if (checkMemory2.isChecked()) {
                        memorySumText.setText(Double.toString(memory2));
                    }
                    if (checkMemory3.isChecked()) {
                        memorySumText.setText(Double.toString(memory3));
                    }
                }
            }
        };

        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnCheckedChangeListener(listener);
        }



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
                if(editX.getText().toString().equals("") || editY.getText().toString().equals("") || editZ.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Введите X Y Z", Toast.LENGTH_SHORT).show();
                }else{
                if(isFormula1){
                    sum = formula1(Double.parseDouble(editX.getText().toString()),
                            Double.parseDouble(editY.getText().toString()),
                            Double.parseDouble(editZ.getText().toString()));
                } else {
                    sum = formula2(Double.parseDouble(editX.getText().toString()),
                            Double.parseDouble(editY.getText().toString()),
                            Double.parseDouble(editZ.getText().toString()));
                }
                resultText.setText(Double.toString(sum));
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultText.setText("0");
                sum = 0;
                editX.setText("");
                editY.setText("");
                editZ.setText("");
            }
        });

        buttonMemoryClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memory1 = 0;
                memory2 = 0;
                memory3 = 0;
                memorySumText.setText("0");
                checkMemory1.setChecked(false);
                checkMemory2.setChecked(false);
                checkMemory3.setChecked(false);
            }
        });

        buttonMemoryPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sum != 0) {
                    if (checkMemory1.isChecked()) {
                        memory1 += sum;
                        memorySumText.setText(Double.toString(memory1));
                    }
                    if (checkMemory2.isChecked()) {
                        memory2 += sum;
                        memorySumText.setText(Double.toString(memory2));
                    }
                    if (checkMemory3.isChecked()) {
                        memory3 += sum;
                        memorySumText.setText(Double.toString(memory3));
                    }
                }
            }
        });

    }

    public Double formula1(Double x, Double y, Double z) {
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