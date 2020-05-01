package com.akinseye.ndif_yemmanuel.handout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CustomAdapterCovidTips extends BaseAdapter {

    Context context;
    String[] tit;
    String[] con;
    String[] sou;
    String[] ic;


    private LayoutInflater mInflater;

    public CustomAdapterCovidTips(Context context, String[] tit, String[] con, String[] sou, String[] ico) {
        this.context = context;
        this.tit = tit;
        this.con = con;
        this.sou = sou;
        this.ic = ico;
        mInflater = LayoutInflater.from(context);


    }
    @Override
    public int getCount() {
        return tit.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        convertView = mInflater.inflate(R.layout.custom_covid_tips, null);

        ImageView ico = convertView.findViewById(R.id.icon);
        TextView titl = convertView.findViewById(R.id.title);
        TextView sourc = convertView.findViewById(R.id.source);

        titl.setText(tit[i]);
        sourc.setText(sou[i]);
        Picasso.get().load(ic[i]).into(ico);



        return convertView;
    }
}
