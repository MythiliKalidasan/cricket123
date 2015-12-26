package com.example.mythi_000.cricket;

/**
 * Created by mythi_000 on 11/24/2015.
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlayersList extends AppCompatActivity {

    final String myTag = "--->JsonMAIN<---";
    private static final String DEBUG_TAG = "HttpExample";
    ArrayList<Team> teams = new ArrayList<Team>();
    ListView listview;
    Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);
        listview = (ListView) findViewById(R.id.listview);
        btnDownload = (Button) findViewById(R.id.btnDownload);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            btnDownload.setEnabled(true);
            Log.i(myTag, "CONNECTED");
        } else {
            btnDownload.setEnabled(false);
            Log.i(myTag, "NO CONNTN");
        }
    }

    public void buttonClickHandler(View view) {
        new DownloadWebpageTask(
                new AsyncResult() {
                    @Override
                    public void onResult(JSONObject object)
                    {
                        processJson(object);
                    }
                }).execute("https://spreadsheets.google.com/tq?key=1GizPVZX48stlACG8sxsBvxQL6UitFDRrLRh5tZF_glE");

        Log.i(myTag, "DownloadURL");
    }

    private void processJson(JSONObject object) {

        try {
            JSONArray rows = object.getJSONArray("rows");

            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                String no = columns.getJSONObject(0).getString("v");
                String name = columns.getJSONObject(1).getString("v");

                Team team = new Team(no, name);
                Log.d("--->JsonMAIN<---", "TABLEVALUE: " + team);
                teams.add(team);
            }

            final TeamsAdapter adapter = new TeamsAdapter(this, R.layout.team, teams);
            listview.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

