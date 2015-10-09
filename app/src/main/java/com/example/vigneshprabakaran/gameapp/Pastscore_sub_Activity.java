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
 * Created by vigneshprabakaran on 07-09-2015.
 */
public class Pastscore_sub_Activity extends BaseAdapter{
    Context context;
    LayoutInflater inflater;
    HashMap<String, String> map = new HashMap<String, String>();
    ArrayList<HashMap<String, String>> data;
    public Pastscore_sub_Activity(Context context, ArrayList<HashMap<String, String>> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        System.out.println("ashok data--->"+data);

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
           // System.out.println("ask"+data.get(position));
       //    System.out.println("end" + data.get(position).get("lession_time"));

            vi = inflater.inflate(R.layout.pastscore_sub_layout, parent, false);
            // Get the position
            holder = new ViewHolder();

            holder.Lession_name = (TextView) vi.findViewById(R.id.sub_lession_name);
            holder.Lession_rank = (TextView) vi.findViewById(R.id.sub_rank);
            holder.Lession_date = (TextView) vi.findViewById(R.id.sub_Date);
            holder.Lession_time = (TextView) vi.findViewById(R.id.sub_time);
            vi.setTag(holder);
        }
            else{
                holder=(ViewHolder)vi.getTag();
            }

      map=data.get(position);
    //    System.out.println("bU" + map);

    //  System.out.println("ashok map---->" + map.get("lession_time"));

        holder.Lession_rank.setText(map.get(Pastscore_Activity.LESSION_RANK));
        holder.Lession_name.setText(data.get(position).get(Pastscore_Activity.LESSION_NAME));
            holder.Lession_date.setText(map.get(Pastscore_Activity.LESSION_DATE));
            holder.Lession_time.setText(map.get(Pastscore_Activity.LESSION_TIME));
       // System.out.println(map.get("lession_name") + "   " + map.get("lession_date"));
       // System.out.println("name="+holder.Lession_name.getText()+"rank="+ holder.Lession_rank.getText()+"date"+holder.Lession_date.getText()+"time="+holder.Lession_date.getText());
        return vi;
    }
    private class ViewHolder{
        TextView Lession_name;
        TextView Lession_rank;
        TextView Lession_date;
        TextView Lession_time;

    }

}
