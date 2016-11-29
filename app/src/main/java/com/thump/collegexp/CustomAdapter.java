package com.thump.collegexp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

class CustomAdapter extends ArrayAdapter<College> {

    private Context context;
    private List<College> moreColleges;

    CustomAdapter(Context context, int resource, ArrayList<College> objects) {
        super(context,resource, objects);

        this.context = context;
        this.moreColleges = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        College college = moreColleges.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView= inflater.inflate(R.layout.list_item, null);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        String image = college.getImageUrl();
        Ion.with(context)
                .load(image)
                .withBitmap()
                .placeholder(R.mipmap.error)
                .error(R.mipmap.error)
                .intoImageView(imageView);

        /*ImageView image = (ImageView) convertView.findViewById(R.id.imageView);
        int imageID = context.getResources().getIdentifier(college.getImageUrl(), "drawable", context.getPackageName());
        image.setImageResource(imageID);*/

        TextView tname = (TextView) convertView.findViewById(R.id.name);
        tname.setText(college.getName());

        TextView taddress = (TextView) convertView.findViewById(R.id.address);
        taddress.setText(college.getAddress());

        return convertView;
    }
}