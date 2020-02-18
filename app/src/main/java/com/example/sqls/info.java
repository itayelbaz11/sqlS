package com.example.sqls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.sqls.GRADES.TABLE_GRADES;
import static com.example.sqls.STUDENTS.KEY_ID;
import static com.example.sqls.STUDENTS.TABLE_STUDENTS;
import static com.example.sqls.GRADES.TABLE_GRADES;
import static com.example.sqls.STUDENTS.KEY_ID;
import static com.example.sqls.STUDENTS.TABLE_STUDENTS;

public class info extends AppCompatActivity implements AdapterView.OnItemClickListener{


    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;
    ListView lvT, lvR;
    ArrayAdapter adp, adp2;
    ArrayList<String> arr = new ArrayList<>();
    int c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        lvT = (ListView) findViewById(R.id.lvT);
        lvR = (ListView) findViewById(R.id.lvR);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        c = 0;


        lvT.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvT.setOnItemClickListener(this);
        lvR.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvR.setOnItemClickListener(this);

        String[] t = {TABLE_STUDENTS,TABLE_GRADES};
        adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, t);
        lvT.setAdapter(adp);

    }

    /**
     * This method shows the information and you can click on what you want to delete
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        if (parent == lvT) {
            arr = new ArrayList<>();
            c = position + 1;
            db = hlp.getReadableDatabase();
            if (c == 1) {
                crsr = db.query(TABLE_STUDENTS, null, null, null, null, null, null);
                int co1 = crsr.getColumnIndex(KEY_ID);
                int co2 = crsr.getColumnIndex(STUDENTS.NAME);
                int co3 = crsr.getColumnIndex(STUDENTS.ADDRESS);
                int co4 = crsr.getColumnIndex(STUDENTS.PHONE);
                int co5 = crsr.getColumnIndex(STUDENTS.HOME_P);
                int co6 = crsr.getColumnIndex(STUDENTS.MOM_NAME);
                int co7 = crsr.getColumnIndex(STUDENTS.MOM_NUM);
                int co8 = crsr.getColumnIndex(STUDENTS.DAD_NAME);
                int co9 = crsr.getColumnIndex(STUDENTS.DAD_NUM);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int key = crsr.getInt(co1);
                    String name = crsr.getString(co2);
                    String add = crsr.getString(co3);
                    int pho = crsr.getInt(co4);
                    int Hpho = crsr.getInt(co5);
                    String Mname = crsr.getString(co6);
                    int Mnum = crsr.getInt(co7);
                    String Dname = crsr.getString(co8);
                    int Dnum = crsr.getInt(co9);
                    String stUsers = "" + key + ", " + name + ", " + add + "," + pho + ", " + Hpho + ", " + Mname + ", " + Mnum + ", " + Dname + ", " + Dnum;
                    arr.add(stUsers);
                    crsr.moveToNext();
                }
            } else {
                crsr = db.query(TABLE_GRADES, null, null, null, null, null, null);
                int co1 = crsr.getColumnIndex(GRADES.KEY_ID);
                int co2 = crsr.getColumnIndex(GRADES.NAME);
                int co3 = crsr.getColumnIndex(GRADES.QUARTER);
                int co4 = crsr.getColumnIndex(GRADES.GRADE);
                int co5 = crsr.getColumnIndex(GRADES.SUBJECT);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int key = crsr.getInt(co1);
                    String name = crsr.getString(co2);
                    int qua = crsr.getInt(co3);
                    int grade = crsr.getInt(co4);
                    String stGrades = "" + key + "," + name + "," + qua + "," + grade;
                    arr.add(stGrades);
                    crsr.moveToNext();
                }

            }
            crsr.close();
            db.close();
            adp2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arr);
            lvR.setAdapter(adp2);
        }


        else {
            if (c == 1) {
                db.delete(TABLE_STUDENTS, KEY_ID + "=?", new String[]{Integer.toString(position + 1)});
            }
            else{
                db.delete(TABLE_GRADES, KEY_ID + "=?", new String[]{Integer.toString(position + 1)});
            }
            db.close();
            arr.remove(position);
            adp.notifyDataSetChanged();
        }


    }

    /**
     * These methods creates the menu
     * @param menu
     * @return
     */

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        String st = item.getTitle().toString();
        if (st.equals("main")){
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if(st.equals("sort")){
            Intent si = new Intent(this, sort.class);
            startActivity(si);
        }
        if (st.equals("creds")){
            Intent si = new Intent(this, credits.class);
            startActivity(si);
        }
        return true;
    }
}