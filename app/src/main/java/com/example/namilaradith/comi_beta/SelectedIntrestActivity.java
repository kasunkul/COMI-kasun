package com.example.namilaradith.comi_beta;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import Appclasses.ComiDB;
import Appclasses.Intrest;

/**
 * Created by dealwis on 4/27/16.
 */
public class SelectedIntrestActivity extends AppCompatActivity {


    public String ImageUrl;
    public Button Like_BUTTON;
    public Button join_BUTTON;
    public Intrest I;
    public TextView like_count;
    public  EditText caption_field;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_intrest);

        ImageUrl = getIntent().getExtras().getString("url");

        caption_field = (EditText) findViewById(R.id.selected_intrest_caption);
        ImageView Image_field = (ImageView) findViewById(R.id.selected_intrest_image);
        TextView uploaded_time = (TextView) findViewById(R.id.intrest_upload_time);
        Like_BUTTON = (Button) findViewById(R.id.selected_intrest_like_button);
        Like_BUTTON.setOnClickListener(buttonListener);
        join_BUTTON = (Button) findViewById(R.id.intrest_join_button);
        join_BUTTON.setOnClickListener(joinbuttonListener);

        like_count = (TextView) findViewById(R.id.selected_intrest_like_count);



        String idImage = ImageUrl.substring(ImageUrl.length() - 1);

        I = new Intrest();

        if(I.get_all_intrest_details(idImage) == 1){
            caption_field.setText(I.intrest_caption);
            uploaded_time.setText(I.intrest_shared_date);
        }
        else if(I.get_all_intrest_details(idImage) == 0){
            caption_field.setText("Failed to Load Caption of this Interest");
        }
        else{
            caption_field.setText("Server Error");
        }

        Bitmap bmp = null;
        try {
            URL url = new URL(ImageUrl);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image_field.setImageBitmap(bmp);


        String count = get_intrest_like_count();
        like_count.setText((count));


        get_like_status();
        get_join_status();
        get_intrest_type();
    }


    public void make_like(){

        //http://192.168.8.100/comi_server/?method=get_selected_image&id=
        String standardStringurl = "http://192.168.8.100/comi_server/?method=get_selected_image&id=";




        String temp = this.ImageUrl;


        int diff = temp.length() - standardStringurl.length();
        String intrest_id =  temp.substring(temp.length() - diff);

        ComiDB COMIDBhelper = new ComiDB(SelectedIntrestActivity.this);
        Cursor c = COMIDBhelper.getAllData(1);

        ArrayList<String> users = COMIDBhelper.getAlluser();


        c.moveToFirst();

        String user_id = c.getString(0);

        I.make_intrest_like(intrest_id, users.get(0));

        String count = get_intrest_like_count();
        like_count.setText((count));

        get_like_status();

    }


    public void join_activity_action(){

        //http://192.168.8.100/comi_server/?method=get_selected_image&id=
        String standardStringurl = "http://192.168.8.100/comi_server/?method=get_selected_image&id=";

        String temp = this.ImageUrl;

        int diff = temp.length() - standardStringurl.length();
        String intrest_id =  temp.substring(temp.length() - diff);

        ComiDB COMIDBhelper = new ComiDB(SelectedIntrestActivity.this);
        Cursor c = COMIDBhelper.getAllData(1);

        ArrayList<String> users = COMIDBhelper.getAlluser();


        c.moveToFirst();

        String user_id = c.getString(0);

        I.join_activity_action(intrest_id, users.get(0));

        get_join_status();

    }

    public String get_intrest_like_count() {
        //http://192.168.8.100/comi_server/?method=get_selected_image&id=
        String standardStringurl = "http://192.168.8.100/comi_server/?method=get_selected_image&id=";

        String temp = this.ImageUrl;


        int diff = temp.length() - standardStringurl.length();
        String intrest_id =  temp.substring(temp.length() - diff);

        String response = I.get_all_like_count(intrest_id);


        if (response.equals("-1")){
            return "-1";

        }
            else if(response.equals("-2")) {
            return "-2";
        }
        else{
            return response;
        }

    }


    public void get_intrest_type() {

        //http://192.168.8.100/comi_server/?method=get_selected_image&id=
        String standardStringurl = "http://192.168.8.100/comi_server/?method=get_selected_image&id=";




        String temp = this.ImageUrl;


        int diff = temp.length() - standardStringurl.length();
        String intrest_id =  temp.substring(temp.length() - diff);
        String response = I.get_intrest_type(intrest_id);


        if (response.equals("-1")){


        }
        else if(response.equals("-2")) {

        }
        else{

            if(response.equals("1")){
                join_BUTTON.setVisibility(View.VISIBLE);
                Like_BUTTON.setVisibility(View.VISIBLE);
            }
            else if(response.equals("2")){
                join_BUTTON.setVisibility(View.INVISIBLE);
                Like_BUTTON.setVisibility(View.VISIBLE);
            }else{

            }



        }

    }

    public void get_join_status() {
        //http://192.168.8.100/comi_server/?method=get_selected_image&id=
        String standardStringurl = "http://192.168.8.100/comi_server/?method=get_selected_image&id=";




        String temp = this.ImageUrl;


        int diff = temp.length() - standardStringurl.length();
        String intrest_id =  temp.substring(temp.length() - diff);

        ComiDB COMIDBhelper = new ComiDB(SelectedIntrestActivity.this);
        Cursor c = COMIDBhelper.getAllData(1);

        ArrayList<String> users = COMIDBhelper.getAlluser();


        c.moveToFirst();

        String user_id = c.getString(0);

        String response = I.get_join_status(intrest_id, users.get(0));

//        caption_field.setText(response);


        if(response.equals("1")){
            join_BUTTON.setText("UnJoin");
        }
        else if(response.equals("0")){
            join_BUTTON.setText("Join");
        }else{

        }

    }

    public void get_like_status() {

        //http://192.168.8.100/comi_server/?method=get_selected_image&id=
        String standardStringurl = "http://192.168.8.100/comi_server/?method=get_selected_image&id=";




        String temp = this.ImageUrl;


        int diff = temp.length() - standardStringurl.length();
        String intrest_id =  temp.substring(temp.length() - diff);

        ComiDB COMIDBhelper = new ComiDB(SelectedIntrestActivity.this);
        Cursor c = COMIDBhelper.getAllData(1);

        ArrayList<String> users = COMIDBhelper.getAlluser();


        c.moveToFirst();

        String user_id = c.getString(0);

        String response = I.get_like_status(intrest_id, users.get(0));

//        caption_field.setText(response);


        if(response.equals("1")){
            Like_BUTTON.setText("Unlike");
        }
        else if(response.equals("0")){
            Like_BUTTON.setText("Like");
        }else{

        }

    }



    View.OnClickListener buttonListener = new View.OnClickListener() {
        boolean clicked = false;
        int numClicks = 0;

        @Override
        public void onClick(View v) {
            make_like();
        }
    };

    View.OnClickListener joinbuttonListener = new View.OnClickListener() {
        boolean clicked = false;
        int numClicks = 0;

        @Override
        public void onClick(View v) {
            join_activity_action();
        }
    };


}
