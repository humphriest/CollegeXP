/*
package com.thump.collegexp;

*/
/**
 * Created by Tim on 29/11/2016.
*//*

 import android.app.Activity;
 import android.content.Context;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ArrayAdapter;
 import android.widget.ImageView;
 import android.widget.ListView;
 import android.widget.TextView;

 import com.koushikdutta.ion.Ion;

 import java.util.ArrayList;
 import java.util.List;


public class CourseAdapter extends ArrayAdapter<College> {

    private Context context;
    private List<College> moreColleges;

    public CourseAdapter(Context context, int resource, ArrayList<College> objects) {
        super(context, resource);

        this.context = context;
        this.moreColleges = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        College college = moreColleges.get(position);
        ArrayList<String> courses = college.getCourses();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView= inflater.inflate(R.layout.list_item, null);

        TextView tname = (TextView) convertView.findViewById(R.id.name);
        tname.setText(college.getName());

        for(int i=0;i<courses.size();i++) {
            TextView coursename = (TextView) convertView.findViewById(R.id.address);
            coursename.setText(courses.get(i));
        }

        return convertView;
    }
}
*/
