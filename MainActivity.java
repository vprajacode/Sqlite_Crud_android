package com.example.sqlitecr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ntxt,ctxt,etxt;
    Button insertbtn,updatebtn,deletebtn,showbtn;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ntxt=findViewById(R.id.namebox);
        ctxt=findViewById(R.id.contactbox);
        etxt=findViewById(R.id.emailbox);
        insertbtn=findViewById(R.id.insertbtn);
        updatebtn=findViewById(R.id.updatebtn);
        deletebtn=findViewById(R.id.deletebtn);
        showbtn=findViewById(R.id.Viewbtn);
        DB= new DBHelper(this);

        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =ntxt.getText().toString();
                String contact =ctxt.getText().toString();
                String email =etxt.getText().toString();
                Boolean checkdata= DB.insertdata(name,contact,email);

                if(checkdata==true)
                {
                    Toast.makeText(MainActivity.this, "New data added", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Not added", Toast.LENGTH_SHORT).show();
                }

            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =ntxt.getText().toString();
                String contact =ctxt.getText().toString();
                String email =etxt.getText().toString();
                Boolean checkdata= DB.updatedata(name,contact,email);

                if(checkdata==true)
                {
                    Toast.makeText(MainActivity.this, "data updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "not updated", Toast.LENGTH_SHORT).show();
                }

            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =ntxt.getText().toString();
                Boolean checkdata= DB.deletedata(name);

                if(checkdata==true)
                {
                    Toast.makeText(MainActivity.this, "data deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "not deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res =DB.getdata();
                if(res.getCount()==0)
                {
                    Toast.makeText(MainActivity.this, "No data Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer sb=new StringBuffer();
                while(res.moveToNext())
                {
                    sb.append("Name "+res.getString(0)+"\n");
                    sb.append("Contact "+res.getString(1)+"\n");
                    sb.append("email "+res.getString(2)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("entrties");
                builder.setMessage(sb.toString());
                builder.show();

            }
        });



    }
}