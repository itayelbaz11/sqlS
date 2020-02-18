package com.example.sqls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }

    /**
     * creates the menu.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * when the button "main" is clicked we move to the main activity
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();
        if (st.equals("main")) {
            finish();
        }
        return true;
    }

}