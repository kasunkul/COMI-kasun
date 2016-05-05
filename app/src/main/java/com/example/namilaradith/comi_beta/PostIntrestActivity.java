package com.example.namilaradith.comi_beta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import Appclasses.Intrest;
/**
 * Created by dealwis on 4/1/16.
 */
public class PostIntrestActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String UPLOAD_URL = "http://192.168.8.100:80/comi_server/upload_img_page.php";
    public static final String UPLOAD_KEY = "image";
    public static final String TAG = "MY MESSAGE";
    public Intrest user_intrest = null;


    private int PICK_IMAGE_REQUEST = 1;

    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonView;


    private EditText Intrest_name ;
    private EditText Intrest_caption;


    private ImageView imageView;

    private Bitmap bitmap;

    private Uri filePath;


    private Spinner selected_intrest_type;
    private Spinner selected_intrest;
    private TextView capacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_intrest_activity);

        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        capacity = (TextView) findViewById(R.id.max_capacity);



        imageView = (ImageView) findViewById(R.id.imageView);

        Intrest_name = (EditText) findViewById(R.id.IntrestName);
        Intrest_caption = (EditText) findViewById(R.id.IntrestCaption);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);


        Spinner intrest_sp = (Spinner) findViewById(R.id.intrest_spinner);
        Spinner intrest_type_sp = (Spinner) findViewById(R.id.intrest_type_spinner);

        ArrayAdapter<CharSequence> intrest_sp_adapter = ArrayAdapter.createFromResource(this,
                R.array.Intrests_array, android.R.layout.simple_spinner_item);


        ArrayAdapter<CharSequence> intrest_type_sp_adapter = ArrayAdapter.createFromResource(this,
                R.array.Intrests_type_array, android.R.layout.simple_spinner_item);


        intrest_sp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        intrest_type_sp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        intrest_sp.setAdapter(intrest_sp_adapter);

        intrest_type_sp.setAdapter(intrest_type_sp_adapter);

        intrest_type_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position == 0) {
                    capacity.setVisibility(View.INVISIBLE);
                }
                else if(position == 1){
                    capacity.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                capacity.setVisibility(View.INVISIBLE);
            }

        });

    }




    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public String getIntrestName(){

        String IntrestName = Intrest_name.getText().toString();
        return IntrestName;
    }

    public String getIntrestcaption(){

        String IntrestCaption = Intrest_caption.getText().toString();
        return IntrestCaption;
    }

    public String get_selected_intrest_type(){

        selected_intrest_type = (Spinner)findViewById(R.id.intrest_type_spinner);
        String type = selected_intrest_type.getSelectedItem().toString();
        return type;
    }

    public String get_capacity(){

        String type = capacity.getText().toString();
        return type;
    }

    public String get_selected_intrest(){


        selected_intrest = (Spinner)findViewById(R.id.intrest_spinner);
        String intrest = selected_intrest.getSelectedItem().toString();
        return intrest;
    }
    private void uploadImage(){

        class UploadImage extends AsyncTask<Bitmap,Void,String> {

            ProgressDialog loading;
            Intrest rh = new Intrest();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(PostIntrestActivity.this, "Uploading Image", "Please wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

//
//                HashMap<String,String> data = new HashMap<>();
//                data.put(UPLOAD_KEY, uploadImage);
//
//                String result = rh.sendPostRequest(UPLOAD_URL,data);

                user_intrest = new Intrest();

                String Intrestname = getIntrestName();
                String IntrestCaption = getIntrestcaption();

                String sel_int_type = get_selected_intrest_type();
                String sel_int =  get_selected_intrest();

                String capacity = get_capacity();

                String result = user_intrest.upload_user_shared_intrest(uploadImage,Intrestname,IntrestCaption,sel_int,sel_int_type,capacity);
                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            showFileChooser();
        }
        if(v == buttonUpload){
            uploadImage();
        }
        if(v == buttonView){
            viewImage();
        }
    }

    private void viewImage() {
        startActivity(new Intent(this, PostIntrestActivity.class));
    }





}
