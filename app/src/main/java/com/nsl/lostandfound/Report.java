package com.nsl.lostandfound;
//
//package com.example.soorya.mreport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class Report extends ActionBarActivity implements View.OnClickListener {
    EditText NameEt, DescriptionEt, ColorEt, LengthEt, WidthEt, LocationEt, EmailEt;
    public static final String UPLOAD_URL = "http://andromeda.nitc.ac.in/~m150035ca/image.php";
    public static final String UPLOAD_KEY = "image";
    public static final String TAG = "MY MESSAGE";
    public static String uploadImage="";
    private int PICK_IMAGE_REQUEST = 1;
    private boolean flag;
    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonView;

    private ImageView imageView;

    private Bitmap bitmap;

    private Uri filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        NameEt = (EditText)findViewById(R.id.etName);
        DescriptionEt = (EditText)findViewById(R.id.etDes);
        ColorEt = (EditText)findViewById(R.id.etColor);
        LengthEt = (EditText)findViewById(R.id.etLength);
        WidthEt = (EditText)findViewById(R.id.etWidth);
        LocationEt = (EditText)findViewById(R.id.etLocation);
        EmailEt = (EditText)findViewById(R.id.etEmail);

        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonView = (Button) findViewById(R.id.buttonViewImage);

        imageView = (ImageView) findViewById(R.id.imageView);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            showFileChooser();
            flag=true;

        }
        if(v == buttonUpload){
            uploadImage();
        }
    }

    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String>{

            ProgressDialog loading;
          //  RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Report.this, "Uploading Image", "Please wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                uploadImage = getStringImage(bitmap);

                //HashMap<String,String> data = new HashMap<>();
                //data.put(UPLOAD_KEY, uploadImage);
                String result="";
                //  String result = rh.sendPostRequest(UPLOAD_URL,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

    public void OnLogin(View view) {
        if (flag == true) {
            uploadImage();
        } else
            uploadImage = "";

        String name = NameEt.getText().toString();
        String description = DescriptionEt.getText().toString();
        String color = ColorEt.getText().toString();

//        if(color =="black" || color=="blue"|| color=="white"|| color=="green"|| color=="red"){

        String length = LengthEt.getText().toString();
        String width = WidthEt.getText().toString();
        String location = LocationEt.getText().toString();
        String email = EmailEt.getText().toString();
        Bundle extras = getIntent().getExtras();
        email = extras.getString("email");
        String type = "login";
        //Toast.makeText(this, color, Toast.LENGTH_LONG).show();
        color=color.trim();
        if(color.compareTo("red")==0 || color.compareTo("blue")==0 || color.compareTo("green")==0|| color.compareTo("white")==0|| color.compareTo("black")==0){
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, name, description, color, length, width, location, email, uploadImage);
   }
        else{
            Toast.makeText(this,"Enter color from the given list",Toast.LENGTH_LONG).show();
            return;
        }





//        Toast.makeText(this,"Report Successfull",Toast.LENGTH_LONG).show();
//        extras = getIntent().getExtras();
//        String username= extras.getString("name");
//        Intent intent = new Intent(this, Mislayer.class);
//        intent.putExtra("name",username);
//        intent.putExtra("email",email);
//        startActivity(intent);
    }

}
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//
//public class Report extends ActionBarActivity {
//    EditText NameEt, DescriptionEt, ColorEt, LengthEt, WidthEt, LocationEt;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_report);
//        NameEt = (EditText)findViewById(R.id.etName);
//        DescriptionEt = (EditText)findViewById(R.id.etDes);
//        ColorEt = (EditText)findViewById(R.id.etColor);
//        LengthEt = (EditText)findViewById(R.id.etLength);
//        WidthEt = (EditText)findViewById(R.id.etWidth);
//        LocationEt = (EditText)findViewById(R.id.etLocation);
//    }
//
//    public void OnLogin(View view) {
//        String name = NameEt.getText().toString();
//        String description = DescriptionEt.getText().toString();
//        String color = ColorEt.getText().toString();
//        String length = LengthEt.getText().toString();
//        String width = WidthEt.getText().toString();
//        String location = LocationEt.getText().toString();
//        Bundle extras = getIntent().getExtras();
//        String email= extras.getString("email");
//        String type = "login";
//        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
//        backgroundWorker.execute(type, name, description, color, length, width, location,email);
//        Toast.makeText(this,"Report Successfull",Toast.LENGTH_LONG).show();
//        extras = getIntent().getExtras();
//        String username= extras.getString("name");
//        Intent intent = new Intent(this, Mislayer.class);
//        intent.putExtra("name",username);
//        intent.putExtra("email",email);
//        startActivity(intent);
//    }
//
//}