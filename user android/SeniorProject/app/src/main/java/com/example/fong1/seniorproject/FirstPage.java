package com.example.fong1.seniorproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class FirstPage extends AppCompatActivity {

    sharedPreferences session;
    private RequestQueue mQueue;
    private static int SPLASH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        session=new sharedPreferences(getApplicationContext());
        mQueue= Volley.newRequestQueue(this);

        freeCoins();
        if(session.checkLogin()){
        }else {
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run(){
                    getAchievement();
                    UserCoins();
                    countTime();
                }
            },1000);
        }



    }
     public void countTime(){

         new Handler().postDelayed(new Runnable(){
             @Override
             public void run(){
                 Intent hommeIntent=new Intent(FirstPage.this,Menu.class);
                 startActivity(hommeIntent);
                 finish();
             }
         },SPLASH_TIME_OUT);


     }
    public void UserCoins(){
        String url ="http://chineseisfun.000webhostapp.com/php/Usercoin.php";
        session=new sharedPreferences(getApplicationContext());
        StringRequest js = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        try {
                            JSONObject get=new JSONObject(response);
                            int coin=get.getInt("coin");
                            session.createUserCoins(coin);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", session.getEmail());
                return params;
            }
        };
        mQueue.add(js);
    }

    public void getAchievement(){

        String url="http://chineseisfun.000webhostapp.com/php/checkLevel.php";

        StringRequest js = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject valid = new JSONObject(response);
                            session.UserLessonProgress(valid.getInt("lesson"));
                            session.createAcheive(valid.getInt("Category"));
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email",session.getEmail());
                return params;
            }
        };
        mQueue.add(js);
    }
// check date to rechage free coins
    public void freeCoins(){
        String url ="http://chineseisfun.000webhostapp.com/php/checkTime.php";
        session=new sharedPreferences(getApplicationContext());
        StringRequest js = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", session.getEmail());
                return params;
            }
        };
        mQueue.add(js);
    }


}
