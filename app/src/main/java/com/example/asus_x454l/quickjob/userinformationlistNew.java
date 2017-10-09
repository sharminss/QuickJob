package com.example.asus_x454l.quickjob;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ASUS_X454L on 10/9/2017.
 */

public class userinformationlistNew extends ArrayAdapter<UserInformationNew> {

    private Activity context;
    private int resource;
    private List<UserInformationNew> listImage;

    public userinformationlistNew(@NonNull Activity context, @LayoutRes int resource, @NonNull List<UserInformationNew> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        listImage = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View v = inflater.inflate(resource, null);
        TextView tvName = (TextView) v.findViewById(R.id.textView11);
        TextView tvName2 = (TextView) v.findViewById(R.id.textView12);
        TextView tvName3 = (TextView) v.findViewById(R.id.textView17);
        ImageView img = (ImageView) v.findViewById(R.id.imageView5);

        tvName.setText(listImage.get(position).getName());

        tvName2.setText(listImage.get(position).getEmail());
        tvName3.setText(listImage.get(position).getPhone_num());
        Glide.with(context).load(listImage.get(position).getUrl()).into(img);

        return v;

    }
}
