package com.example.mythi_000.cricket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity  {
    final String myTag = "--->checkMAIN<---";
    private Button newPlayerS1;
    private Button viewPlayerS2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);
        newPlayerS1=(Button) findViewById(R.id.NewPlayer);
        Log.i(myTag, "Page-NewPlayer");
        newPlayerS1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent page1 = new Intent(MainActivity.this, NewPlayer.class);
                startActivity(page1);
                Log.d("--->checkMAIN<---", "ERROR: " + page1);

            }
        });


        viewPlayerS2=(Button) findViewById(R.id.ViewPlayer);
        Log.i(myTag, "Page-ViewPlayer");
        viewPlayerS2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent page2 = new Intent(MainActivity.this,PlayersList.class);
                startActivity(page2);
                Log.d("--->checkMAIN<---", "ERROR: " +page2);

            }
        });
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
