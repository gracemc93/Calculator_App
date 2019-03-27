package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.editText);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.newNumber);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonDot = findViewById(R.id.dot);
        Button buttonEquals = findViewById(R.id.equals);
        Button buttonDivide = findViewById(R.id.divide);
        Button buttonMultipy = findViewById(R.id.multiply);
        Button buttonMinus = findViewById(R.id.minus);
        Button buttonPlus = findViewById(R.id.add);

        ArrayList<Button> buttonList = new ArrayList<Button>();
        ArrayList<Button> operandList = new ArrayList<Button>();
        buttonList.add(button0);
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        buttonList.add(button5);
        buttonList.add(button6);
        buttonList.add(button7);
        buttonList.add(button8);
        buttonList.add(button9);
        operandList.add(buttonDot);
        operandList.add(buttonEquals);
        operandList.add(buttonDivide);
        operandList.add(buttonMultipy);
        operandList.add(buttonMinus);
        operandList.add(buttonPlus);

        OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                newNumber.append(b.getText().toString());

            }
    };

        View.OnClickListener opListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                if(value.length() != 0){
                    performOperation(value, op);
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };
        for(Button button : buttonList)
        {
            button.setOnClickListener(listener);
        }

        for(Button button : operandList)
        {
            button.setOnClickListener(opListener);
        }

    }

    private void performOperation(String value, String operation)
    {
        if (null == operand1){
            operand1 = Double.valueOf(value);
        }
        else {
            operand2 = Double.valueOf(value);

            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            switch (pendingOperation) {
                case "=":
                    operand1 = operand2;
                    break;
                case "/":
                    if (operand2 == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= operand2;
                    }
                    break;
                case "*":
                    operand1 *= operand2;
                    break;
                case "-":
                    operand1 -= operand2;
                    break;
                case "+":
                    operand1 += operand2;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }

}
