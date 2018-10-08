package com.example.fong1.seniorproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.util.HashMap;
import java.util.Map;

public class Me extends AppCompatActivity {

    private long backPressedTime;
    private Toast backtoast;
    Button select,upload;
    sharedPreferences session;
    TextView name,myCoin,mylevel;
    private RequestQueue mQueue;
    BottomNavigationView navigationView;
    int level;
    ImageView image;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int PICK_IMAGE_REQUEST= 99;
    Bitmap bitmap;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        mQueue = Volley.newRequestQueue(this);
        session=new sharedPreferences(getApplicationContext());
        name=(TextView) findViewById(R.id.name);
        mylevel= (TextView) findViewById(R.id.level);
        myCoin=(TextView) findViewById(R.id.coin);
        image=(ImageView) findViewById(R.id.profile);
        select=(Button) findViewById(R.id.selectpic);
        upload=(Button) findViewById(R.id.upload);
        Button logout=(Button) findViewById(R.id.button);
        myCoin.setText(Integer.toString(session.getCoins()));
        select.setVisibility(View.INVISIBLE);
        upload.setVisibility(View.INVISIBLE);
        String link=session.getUserProfile();
        if(link.equals("")){
            image.setImageResource(R.mipmap.defaultprofile);
            select.setVisibility(View.VISIBLE);
        }else{
            select.setVisibility(View.GONE);
            upload.setVisibility(View.GONE);
            Picasso.with(Me.this).load(link).into(image);
        }
        name.setText(session.getUserName());
        level=session.getAcheive();
        if(level<2){
            mylevel.setText("Beginner");
        }else if(level<4){
            mylevel.setText("Elementary");
        }else if(level<6){
            mylevel.setText("Pre-Intermediate");
        }else if(level<9){
            mylevel.setText("Low-Intermediate");
        }else if(level<12){
            mylevel.setText("Intermediate");
        }
        navigationView=(BottomNavigationView) findViewById(R.id.Navigation);
        navigationView.setSelectedItemId(R.id.me);
        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.test){
                    Intent intent=new Intent(Me.this,Menu.class);
                    startActivity(intent);
                } else if(item.getItemId()==R.id.learn){
                    Intent intent=new Intent(Me.this,mainLearn.class);
                    startActivity(intent);
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
            }
        });

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission();
            }
        }
    }

    public void choose(View view){
        showFileChooser();
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, " Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted Successfully! ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission Denied :( ", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override  //after choose the image
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Toast.makeText(this, "Image ready for uploading", Toast.LENGTH_SHORT).show();
                upload.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void uploadimage(View view){

        String myurl = "http://chineseisfun.000webhostapp.com/php/upload.php";
        progressDialog = new ProgressDialog(Me.this);
        progressDialog.setMessage("Uploading, please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, myurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
               // Toast.makeText(Me.this,response, Toast.LENGTH_SHORT).show();
                profile();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Me.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();

                String images = getStringImage(bitmap);
                param.put("email",session.getEmail());
                param.put("pic",images);
                return param;
            }
        };
        mQueue.add(stringRequest);

    }

    public String getStringImage(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    // if user upload profile
    private void profile() {

        String myurl = "http://chineseisfun.000webhostapp.com/php/loadImage.php";
        StringRequest strReq = new StringRequest(Request.Method.POST,myurl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jObj = new JSONObject(response);
                    String image=jObj.getString("image");
                    if(image.equals("")){

                    }else {
                        session.createUserProfile(image);
                        Intent intent=new Intent(Me.this,Me.class);
                        startActivity(intent);

                    }
                    } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", session.getEmail());
                return params;
            }
        };
        // Adding request to request queue
        mQueue.add(strReq);
    }

    @Override
    public void onBackPressed(){
        if(backPressedTime+2000>System.currentTimeMillis()){
            backtoast.cancel();
            this.finishAffinity();
        }else{
            backtoast=Toast.makeText(this,"Press back again to exit",Toast.LENGTH_SHORT);
            backtoast.show();;
        }
        backPressedTime=System.currentTimeMillis();
    }
}
