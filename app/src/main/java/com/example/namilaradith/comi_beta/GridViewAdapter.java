package com.example.namilaradith.comi_beta;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {


    private ImageLoader imageLoader;


    private Context context;


    private ArrayList<String> images;
    private ArrayList<String> names;
    private ArrayList<String> ids;
    private ArrayList<String> captions;

    public GridViewAdapter (Context context, ArrayList<String> images, ArrayList<String> names,ArrayList<String> ids,ArrayList<String> captions){

        this.context = context;
        this.images = images;
        this.names = names;
        this.ids = ids;
        this.captions = captions;

    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public ArrayList<String> getALLIDS() {

        return this.ids;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        NetworkImageView networkImageView = new NetworkImageView(context);


        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(images.get(position), ImageLoader.getImageListener(networkImageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));




        networkImageView.setImageUrl(images.get(position), imageLoader);


        //Splitting String


        String retval[] = names.get(position).split("\\^", 2);

        String name = retval[0];
        String caption = retval[1];



        //Creating a textview to show the title
        TextView textView = new TextView(context);
        textView.setText(name);

        //Creating a textview to show the title
        TextView CaptiontextView = new TextView(context);
        CaptiontextView.setText(caption);


        //Scaling the imageview
        networkImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        networkImageView.setLayoutParams(new GridView.LayoutParams(200, 200));

        //Adding views to the layout
        linearLayout.addView(textView);
        linearLayout.addView(networkImageView);
        linearLayout.addView(CaptiontextView);


        return linearLayout;
    }

}
