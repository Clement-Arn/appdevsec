package fr.lateam.mybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity {

    private EditText input_digits;
    private TextView wrong_pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        wrong_pin = (TextView) findViewById(R.id.wrong_pin);

        input_digits = (EditText) findViewById(R.id.input_digits);
        input_digits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = input_digits.length();
                String digits = input_digits.getText().toString();

                //Hashage du PIN
                MessageDigest md = null;
                try {
                    md = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                md.update(digits.getBytes());
                byte[] digest = md.digest();
                StringBuffer sb = new StringBuffer();
                for(byte b : digest) {
                    sb.append(String.format("%02x", b & 0xff));
                }
                //Fin du hashage du PIN

                if (sb.toString().equals("64c5325dedfde0061e3d00ddaed2de7b5f0724819b3bdc99c062210b209f122a")==true) { // 14576485
                    Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(otherActivity);
                    finish();
                }
                else if(length == 8) {
                    input_digits.setText(null);
                    wrong_pin.setText("Wrong PIN");
                }
            }
        });

    }
}