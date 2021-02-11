package fr.lateam.mybank;

import java.io.File;
import net.sqlcipher.database.SQLiteDatabase;
import android.app.Activity;
import android.os.Bundle;

public class Cipherdb extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeSQLCipher();
    }

    private void InitializeSQLCipher() {
        SQLiteDatabase.loadLibs(this);
        File databaseFile = getDatabasePath("demo.db");
        databaseFile.mkdirs();
        databaseFile.delete();
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "test123", null);
    }
}