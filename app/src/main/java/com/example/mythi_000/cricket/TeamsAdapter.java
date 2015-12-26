package com.example.mythi_000.cricket;

/**
 * Created by mythi_000 on 12/10/2015.
 */
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class TeamsAdapter extends ArrayAdapter<Team> {

    Context context;
    private ArrayList<Team> teams;


    public TeamsAdapter(Context context, int textViewResourceId, ArrayList<Team> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.teams = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.team, null);
        }
        Team o = teams.get(position);
        if (o != null) {

            TextView tvno = (TextView) v.findViewById(R.id.no);
            Log.d("--->JsonMAIN<---", "TeamsAdapter: " + tvno);
            TextView tvname = (TextView) v.findViewById(R.id.name);
            Log.d("--->JsonMAIN<---", "TeamsAdapter: " + tvname);



            tvno.setText(String.valueOf(o.getNo()));
            tvname.setText(String.valueOf(o.getName()));

        }
        return v;
    }
}
