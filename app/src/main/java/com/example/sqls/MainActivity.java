package com.example.sqls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    HelperDB hlp;
    EditText etNAME, etADD, etNUM, etHNUM, etMNAME, etMNUM, etDNAME, etDNUM,gname,gquarter,grade,gsub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        etNAME=findViewById(R.id.name);
        etADD=findViewById(R.id.adress);
        etNUM=findViewById(R.id.phone);
        etHNUM=findViewById(R.id.homenum);
        etMNAME=findViewById(R.id.mother);
        etMNUM=findViewById(R.id.mothernum);
        etDNAME=findViewById(R.id.father);
        etDNUM=findViewById(R.id.fathernum);
        gname=findViewById(R.id.name1);
        gquarter=findViewById(R.id.reva);
        grade=findViewById(R.id.grade);
        gsub=findViewById(R.id.subject);
    }


    /**
     * This mthod saves the information in the students edit texts on the data base
     * @param view
     */
    public void fstsub(View view) {
        String name, address, mName, dName;
        int num, Hnum, Mnum, Dnum;
        name = etNAME.getText().toString();
        address = etADD.getText().toString();
        num = Integer.parseInt(etNUM.getText().toString());
        Hnum = Integer.parseInt(etHNUM.getText().toString());
        mName = etMNAME.getText().toString();
        Mnum = Integer.parseInt(etMNUM.getText().toString());
        dName = etDNAME.getText().toString();
        Dnum = Integer.parseInt(etDNUM.getText().toString());

        ContentValues cv = new ContentValues();
        cv.put(STUDENTS.NAME, name);
        cv.put(STUDENTS.ADDRESS, address);
        cv.put(STUDENTS.PHONE, num);
        cv.put(STUDENTS.HOME_P, Hnum);
        cv.put(STUDENTS.MOM_NAME, mName);
        cv.put(STUDENTS.MOM_NUM, Mnum);
        cv.put(STUDENTS.DAD_NAME, dName);
        cv.put(STUDENTS.DAD_NUM, Dnum);

        db = hlp.getWritableDatabase();
        db.insert(STUDENTS.TABLE_STUDENTS, null, cv);
        db.close();
    }

    /**
     * This method saves the grades information on the data base
     * @param view
     */
    public void sndsub(View view) {
        String name,sub,g,q;


        name = gname.getText().toString();
        sub=gsub.getText().toString();
        g=grade.getText().toString();
        q=gquarter.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put(GRADES.GNAME, name);
        cv.put(GRADES.QUARTER,q);
        cv.put(GRADES.SUBJECT,sub);
        cv.put(GRADES.GRADE,g);

        db = hlp.getWritableDatabase();
        db.insert(STUDENTS.TABLE_STUDENTS, null, cv);
        db.close();
    }

    /**
     * These methods create the menu
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        String st = item.getTitle().toString();
        if (st.equals("info")){
            Intent si = new Intent(this, info.class);
            startActivity(si);
        }
        if(st.equals("sort")){
            Intent si = new Intent(this,sort.class);
            startActivity(si);
        }
        if (st.equals("creds")){
            Intent si = new Intent(this,credits.class);
            startActivity(si);
        }
        return true;
    }
}

