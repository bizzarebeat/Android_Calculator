package il.ac.tcb.st.calculator;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText operandOne;
    EditText operandTwo;
    TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spnOperator);
        operandOne = findViewById(R.id.editOp1);
        operandOne.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        operandTwo = findViewById(R.id.editOp2);
        operandTwo.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        resultText = findViewById(R.id.txtResult);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.operators, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void calc(View view){
        try {
            int operand1 = Integer.parseInt(operandOne.getText().toString());
            int operand2 = Integer.parseInt(operandTwo.getText().toString());
            String operator = spinner.getSelectedItem().toString();
            int result = 0;

            switch (operator){
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 == 0){
                        throw new ArithmeticException("Division by zero");
                    }
                    result = operand1 / operand2;
                    break;
                case "^":
                    result = (int) Math.pow(operand1,operand2);
                    break;

            }
            resultText.setText("Result: " + result);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid operands", Toast.LENGTH_SHORT).show();
        } catch (ArithmeticException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}