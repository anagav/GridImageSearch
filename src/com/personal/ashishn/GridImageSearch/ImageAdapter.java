package com.personal.ashishn.GridImageSearch;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    ArrayList<String> mThumbIds;


    public void clear() {
        mThumbIds.clear();
    }


    public ImageAdapter(Context c, ArrayList<String> urls) {
        mContext = c;
        mThumbIds = urls;

    }

    public void setData(ArrayList<String> mThumbIds) {
        this.mThumbIds = mThumbIds;
        this.notifyDataSetChanged();
    }

    public int getCount() {
        return mThumbIds.size();
    }

    public Object getItem(int position) {
        return mThumbIds.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }

        System.out.println("This is called===========");

        Picasso.with(mContext).load(mThumbIds.get(position)).into(imageView);
        return imageView;
    }

}
