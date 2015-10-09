package com.example.vigneshprabakaran.gameapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vigneshprabakaran on 09-09-2015.
 */
public class Leaderboard_sub_Activity extends BaseAdapter{
    Context context;
    LayoutInflater inflater;HashMap<String, String> map = new HashMap<String, String>();
    ArrayList<HashMap<String, String>> data;
    Leaderbord_Activity leaderbord_activity=new Leaderbord_Activity();
    public Leaderboard_sub_Activity(Context context, ArrayList<HashMap<String, String>> arraylist1) {




        this.context = context;
        data = arraylist1;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        View vi=convertView;
        if(convertView==null) {
            System.out.println("ask"+data.get(position));
        //    System.out.println("end" + data.get(position).get(N));

            vi = inflater.inflate(R.layout.leaderboard_sub_layout, parent, false);
            // Get the position
            holder = new ViewHolder();

            holder.Leader_name = (TextView) vi.findViewById(R.id.leader_name);
            holder.Leader_rank = (TextView) vi.findViewById(R.id.leader_rank);

            holder.Leader_time = (TextView) vi.findViewById(R.id.leader_time);
            vi.setTag(holder);
        }
        else{
            holder=(ViewHolder)vi.getTag();
        }

        map=data.get(position);
        System.out.println("bU" + map);

        System.out.println("ashok map---->" + map.get("lession_time"));

        holder.Leader_name.setText(map.get(leaderbord_activity.NAME));
        holder.Leader_rank.setText(data.get(position).get(leaderbord_activity.RANK));

        holder.Leader_time.setText(map.get(leaderbord_activity.TIME));
     //   System.out.println(map.get("lession_name") + "   " + map.get("lession_date"));
      //  System.out.println("name="+holder.Lession_name.getText()+"rank="+ holder.Lession_rank.getText()+"date"+"time=";
        return vi;
    }
    private class ViewHolder{
        TextView Leader_name;
        TextView Leader_rank;
        TextView Leader_time;

    }

}


