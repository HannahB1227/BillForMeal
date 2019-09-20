package edu.uga.cs.billformeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/*
    This class is for the main activity and provides the background actions for the application.
 */
public class MainActivity extends AppCompatActivity {

    TextView total;
    EditText price, num;
    Button ten, fifteen, eighteen;
    ButtonClickListener b;

    @Override
    /*
        This method is called when the Activity is created
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize buttons and text objects
        total = findViewById(R.id.textView);
        price = findViewById(R.id.textView4);
        num = findViewById(R.id.textView6);
        ten = (Button) findViewById(R.id.button);
        fifteen = (Button) findViewById(R.id.button2);
        eighteen = (Button) findViewById(R.id.button3);

        //Add action listener for buttons
        b = new ButtonClickListener();
        ten.setOnClickListener(b);
        fifteen.setOnClickListener(b);
        eighteen.setOnClickListener(b);
    } //onCreate

    /*
        This class acts as the action listener for the three tip buttons.
     */
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        /*
            This method performs the response to the user clicking a button.
            @param v the button which was clicked
         */
        public void onClick(View v) {

            double totalCost = 0, tip = 0, mealCost = 0;
            int people = 0;

            try { //Attempt to get values from user input
                mealCost = Double.parseDouble(price.getText().toString());
                people = Integer.parseInt(num.getText().toString());

                if ((mealCost <= 0) | (people <= 0) | (!price.getText().toString().contains("."))) { //Invalid entries by user
                    Toast.makeText(getApplicationContext(),"Invalid number entry", Toast.LENGTH_SHORT).show();
                    return;
                } //if
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(),"Invalid number entry", Toast.LENGTH_SHORT).show();
                return;
            } //try-catch

            if (v.getId() == R.id.button) { //10% tip
                tip = 0.1;
            } //if
            else if (v.getId() == R.id.button2) { //15% tip
                tip = 0.15;
            } //else-if
            else { //18% tip
                tip = 0.18;
            } //else

            //Calculate total cost of bill
            totalCost = (mealCost + (mealCost * tip)) / people;
            DecimalFormat df = new DecimalFormat("########.00");
            total.setText("$" + df.format(totalCost));
        } //onClick

    } //ButtonListener
} //MainActivity
