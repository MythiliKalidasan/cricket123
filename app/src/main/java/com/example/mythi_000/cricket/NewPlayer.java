package com.example.mythi_000.cricket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLEncoder;

/**
 * Created by mythi_000 on 11/22/2015.
 */

public class NewPlayer extends AppCompatActivity
{
final String myTag = "CHECKDATA";
public String No;
public String Name;
public EditText t1;
public EditText t2;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            t1 = (EditText) findViewById(R.id.editText);
            t2 = (EditText) findViewById(R.id.editText1);
            Button submitbtn = (Button) findViewById(R.id.button);
            Log.i(myTag, "OnCreate()");

            submitbtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    No = t1.getText().toString();
                    Name = t2.getText().toString();
                    Log.d("CHECKDATA", "DATA: " +No);
                    Log.d("CHECKDATA", "DATA: " +Name);
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            postData();

                        }
                    });
                    t.start();
                    Toast.makeText(getBaseContext(), "SENT", Toast.LENGTH_SHORT).show();


                }

            });


        }

        public void postData() {

            HttpRequest mReq = new HttpRequest();
            String fileurl = "https://docs.google.com/forms/d/1cWQJsmGw-5MMvSEDxGxuQ939Xv94GM4oILazJg6J8JM/formResponse";
            String data = "entry.1581232643=" + URLEncoder.encode(No) + "&" + "entry.1181358226=" + URLEncoder.encode(Name);
            String response = mReq.sendPost(fileurl, data);
            Log.i(myTag, "postData()");

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;


        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
}
