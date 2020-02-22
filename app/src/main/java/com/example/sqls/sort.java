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
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.sqls.GRADES.TABLE_GRADES;
import static com.example.sqls.STUDENTS.TABLE_STUDENTS;
import static com.example.sqls.GRADES.TABLE_GRADES;
import static com.example.sqls.STUDENTS.TABLE_STUDENTS;


public class sort extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lvT, lvS, lvSo;
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;

    ArrayList<String> tblRec, tblFiled;
    ArrayList<String> tbl = new ArrayList<>();
    ArrayAdapter<String> adpTables, adpFields, adpData;
    int table;
    String[] tables, fields;
    String tbl2sort;

    String[] columns = null;
    String selection = null;
    String[] selectionArgs = null;
    String groupBy = null;
    String having = null;
    String orderBy = null;
    String limit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        lvT=(ListView)findViewById(R.id.lvT);
        lvS=(ListView)findViewById(R.id.lvS);
        lvSo= (ListView) findViewById(R.id.lvSo);

        hlp=new HelperDB(this);
        db=hlp.getWritableDatabase();
        db.close();


        lvT.setOnItemClickListener(this);
        lvT.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvS.setOnItemClickListener(this);
        lvS.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        table=-1;

        tables= new String[]{TABLE_STUDENTS, TABLE_GRADES};
        ArrayAdapter<String> adpTables=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,tables);
        lvT.setAdapter(adpTables);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (lvT.equals(parent)) {
            table = position;
            if (table==0) {
                tbl2sort= TABLE_STUDENTS;
                fields= new String[]{STUDENTS.KEY_ID, STUDENTS.NAME};
            } else {
                tbl2sort= TABLE_GRADES;
                fields= new String[]{GRADES.KEY_ID, GRADES.GRADE};
            }
            ArrayAdapter<String> adpFields=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,fields);
            lvS.setAdapter(adpFields);
        } else if (lvS.equals(parent)) {
            if (table != -1) {
                tbl = new ArrayList<>();
                db=hlp.getReadableDatabase();
                orderBy=fields[position];

                if (table == 0) {
                    crsr=db.query(TABLE_STUDENTS, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
                    int co1 = crsr.getColumnIndex(STUDENTS.KEY_ID);
                    int co2 = crsr.getColumnIndex(STUDENTS.NAME);
                    int co3 = crsr.getColumnIndex(STUDENTS.ADDRESS);
                    int co4 = crsr.getColumnIndex(STUDENTS.PHONE);
                    int co5 = crsr.getColumnIndex(STUDENTS.HOME_P);
                    int co6 = crsr.getColumnIndex(STUDENTS.MOM_NAME);
                    int co7 = crsr.getColumnIndex(STUDENTS.MOM_NUM);
                    int co8 = crsr.getColumnIndex(STUDENTS.DAD_NAME);
                    int co9 = crsr.getColumnIndex(STUDENTS.DAD_NUM);

                    /**
                     * the information is sorted
                     */

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
                        tbl.add(stUsers);
                        crsr.moveToNext();
                    }
                } else {
                    crsr=db.query(TABLE_GRADES, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
                    int co1 = crsr.getColumnIndex(GRADES.KEY_ID);
                    int co2 = crsr.getColumnIndex(GRADES.GNAME);
                    int co3 = crsr.getColumnIndex(GRADES.QUARTER);
                    int co4 = crsr.getColumnIndex(GRADES.GRADE);
                    crsr.moveToFirst();
                    while (!crsr.isAfterLast()) {
                        int key = crsr.getInt(co1);
                        String name = crsr.getString(co2);
                        int qua = crsr.getInt(co3);
                        int grade = crsr.getInt(co4);
                        String stGrades = "" + key + "," + name + "," + qua + "," + grade;
                        tbl.add(stGrades);
                        crsr.moveToNext();
                    }
                }
                crsr.close();
                db.close();
                adpData = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tbl);
                lvSo.setAdapter(adpData);
            } else {
                Toast.makeText(this, "Choose table first", Toast.LENGTH_LONG).show();
            }
        }
    }
    /**
     * creates options menu
     * @return
     */


    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        String st = item.getTitle().toString();
        if (st.equals("main")) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if (st.equals("info")){
            Intent si = new Intent(this, info.class);
            startActivity(si);
        }

        if (st.equals("creds")){
            Intent si = new Intent(this, credits.class);
            startActivity(si);
        }
        return true;
    }

}