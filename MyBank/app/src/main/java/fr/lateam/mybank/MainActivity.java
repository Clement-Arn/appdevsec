package fr.lateam.mybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.sqlcipher.Cursor;
import net.sqlcipher.DatabaseUtils;
import net.sqlcipher.database.SQLiteDatabase;


import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Spinner account_choice;
    private Button add_button;
    private Button refresh_button;
    private int id_account;

    private TextView owner;
    private TextView amounts;
    private TextView ibans;

    RecyclerView recyclerView;
    List<Base> userListResponseData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        owner = (TextView) findViewById(R.id.owner);
        amounts = (TextView) findViewById(R.id.amounts);
        ibans = (TextView) findViewById(R.id.ibans);

        account_choice = (Spinner) findViewById(R.id.account_choice);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.account_choice, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        account_choice.setAdapter(adapter);
        getUserListData();

        account_choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { // Action spinner account_choice
                id_account = position; //ID de la selection 1, 2...
                String selection = account_choice.getSelectedItem().toString();
                display();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add_button = (Button) findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Action bouton add
                Intent otherActivity = new Intent(getApplicationContext(), NewAccount.class);
                startActivity(otherActivity);
                finish();
            }
        });

        refresh_button = (Button) findViewById(R.id.refresh_button);
        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Action bouton refresh
                File databaseFile = getDatabasePath("demo.db");
                SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "test123", null);
                databaseFile.delete();
                getUserListData();


            }
        });


    }

    protected void display() {
        SQLiteDatabase.loadLibs(this);
        File databaseFile = getDatabasePath("demo.db");
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "test123", null);
        Cursor cursor = database.rawQuery("select * from accounts", null);
        cursor.moveToFirst();
        cursor.getString(cursor.getColumnIndex("amount"));

        cursor.moveToPosition(id_account);

        amounts.setText(cursor.getString(cursor.getColumnIndex("amount")) + cursor.getString(cursor.getColumnIndex("currency")));
        owner.setText(cursor.getString(cursor.getColumnIndex("account_name")));
        ibans.setText(cursor.getString(cursor.getColumnIndex("iban")));

    }

    private void getUserListData() {

        (Api.getClient().getAccounts(1)).enqueue(new Callback<ArrayList<Base>>() {
            @Override
            public void onResponse(Call<ArrayList<Base>> call, Response<ArrayList<Base>> response) {

                userListResponseData = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<Base>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
        SQLiteDatabase.loadLibs(this);
        File databaseFile = getDatabasePath("demo.db");
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "test123", null);


        database.execSQL("create table if not exists accounts(id, account_name, amount, iban, currency)");
        database.execSQL("insert into accounts(id, account_name, amount, iban, currency) values(?, ?, ?, ?, ?)", new Object[]{"1","Home Loan Account","53.96","GL2892415196580170","$"});
        database.execSQL("insert into accounts(id, account_name, amount, iban, currency) values(?, ?, ?, ?, ?)", new Object[]{"2","Credit Card Account","992.09","PT70158480100744700099083","$"});
        database.execSQL("insert into accounts(id, account_name, amount, iban, currency) values(?, ?, ?, ?, ?)", new Object[]{"3","Money Market Account","539.27","LI050679356204LD61366","$"});
        database.execSQL("insert into accounts(id, account_name, amount, iban, currency) values(?, ?, ?, ?, ?)", new Object[]{"4","Money Market Account","680.07","TL772060985262332009033","$"});
    }


}

