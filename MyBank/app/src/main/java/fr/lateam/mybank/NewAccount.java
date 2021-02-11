package fr.lateam.mybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewAccount extends AppCompatActivity {

    private Button cancel_button;
    private Button add_account_button;
    private EditText input_firstname;
    private EditText input_lastname;
    private EditText input_accountname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        
        input_firstname = (EditText) findViewById(R.id.input_firstname);
        input_lastname = (EditText) findViewById(R.id.input_lastname);
        input_accountname = (EditText) findViewById(R.id.input_accountname);

        cancel_button = (Button) findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Action bouton cancel
                Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });
        add_account_button = (Button) findViewById(R.id.add_account_button);
        add_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Action bouton add_account
                String firstname = input_firstname.getText().toString();
                String lastname = input_lastname.getText().toString();
                String accountname = input_accountname.getText().toString();
                Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }
}