package com.example.fong1.seniorproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Inlesson extends AppCompatActivity {

    private static final String TAG = "Inlesson";
    sharedPreferences session;
    private RequestQueue mQueue;
    ArrayList<String> keepChoice = new ArrayList<String>();
    String[] Choice=new String[10];
    String choice;
    String lessonid;
    int cateid;
    int payCoin=50;
    String getresponse="no";
    TextView rant,Nlesson,header;
    int Ngroup=0; //random group number to test
    int range=0; //student range
    AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inlesson);

        mQueue = Volley.newRequestQueue(this);
        session=new sharedPreferences(getApplicationContext());
        rant=(TextView) findViewById(R.id.Myrant);
        Nlesson=(TextView) findViewById(R.id.Nlesson);
        header=(TextView) findViewById(R.id.header);
        lessonid=getIntent().getStringExtra("lessonID");
        cateid=getIntent().getIntExtra("cateid",0);
        if(cateid==2){
            header.setText("BASIC");
        }else if(cateid==3){
            header.setText("NUMBER");
        }else if(cateid==4){
            header.setText("FAMILY");
        }else if(cateid==5){
            header.setText("TIMES");
        }else if(cateid==6){
            header.setText("CLASSROOM");
        }else if(cateid==7){
            header.setText("TRAVEL");
        }else if(cateid==8){
            header.setText("ANIMAL");
        }else if(cateid==9){
            header.setText("SEASON");
        }else if(cateid==10){
            header.setText("CAREER");
        }else if(cateid==11){
            header.setText("CONVERSATION 1");
        }else if(cateid==12){
            header.setText("CONVERSATION 2");
        }else if(cateid==13){
            header.setText("CONVERSATION 3");
        }
        if(lessonid.equals(Integer.toString(cateid)+".1")) {
            Nlesson.setText("Lesson 1/3");
        }else if(lessonid.equals(Integer.toString(cateid)+".2")) {
            Nlesson.setText("Lesson 2/3");
        }else {
            Nlesson.setText("Lesson 3/3");
        }
        getGroup();
        studentRange();
    }

    public void start(View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(Inlesson.this);
        View design=getLayoutInflater().inflate(R.layout.dialoglayout,null);
        Button no=(Button) design.findViewById(R.id.NO);
        Button ok=(Button) design.findViewById(R.id.OK);

        builder.setView(design);
        final AlertDialog dialog=builder.create();
        dialog.show();

        no.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomNgroup();
                 paycoin();
                    if (getresponse.equals("no")) {
                        getChoice();
                        dialog.cancel();
                        AlertDialog.Builder loading=new AlertDialog.Builder(Inlesson.this);
                        View design=getLayoutInflater().inflate(R.layout.loading,null);
                        ImageView load=(ImageView) design.findViewById(R.id.load);
                        animation=(AnimationDrawable) load.getDrawable();
                        loading.setView(design);
                        final AlertDialog dialogLoad=loading.create();
                        dialogLoad.show();
                        animation.start();
                        new CountDownTimer(5000, 1000) {

                            public void onTick(long millisUntilFinished) {

                            }

                            public void onFinish() {
                                animation.stop();
                                dialogLoad.cancel();
                                if(getresponse.equals("1")) {// not enough money
                                    alert();
                                }else if(getresponse.equals("0")){
                                    checkvalue();
                                }
                            }
                        }.start();

                    } else if(getresponse.equals("1")){// not enough money
                        dialog.cancel();
                        alert();
                    }else if(getresponse.equals("0")){ //already paid
                        dialog.cancel();
                        checkvalue();
                    }
                }
        });
            }

            public void checkvalue() {

                session = new sharedPreferences(getApplicationContext());
                int updateCoin = session.getCoins() - payCoin;
                session.createUserCoins(updateCoin);
                keepChoice = new ArrayList<String>(Arrays.asList(Choice));
                if (cateid == 1) {
                    Intent intent = new Intent(Inlesson.this, test.class);
                    intent.putExtra("lessonid", lessonid);
                    intent.putExtra("cateid", cateid);
                    intent.putExtra("group", Ngroup);
                    intent.putStringArrayListExtra("choice", keepChoice);
                    startActivity(intent);
                } else {
                    int checklesson = Integer.parseInt(lessonid.substring(2));
                    if (checklesson == 3) {
                        Intent intent = new Intent(Inlesson.this, typingTest.class);
                        intent.putExtra("lessonid", lessonid);
                        intent.putExtra("cateid", cateid);
                        intent.putExtra("group", Ngroup);
                        intent.putStringArrayListExtra("choice", keepChoice);
                        startActivity(intent);

                    } else {
                        Intent intent = new Intent(Inlesson.this, test2.class);
                        intent.putExtra("lessonid", lessonid);
                        intent.putExtra("cateid", cateid);
                        intent.putExtra("group", Ngroup);
                        intent.putStringArrayListExtra("choice", keepChoice);
                        startActivity(intent);
                    }
                }

            }

            public void randomNgroup() {
                Random get = new Random();
                int group = get.nextInt(Ngroup) + 1;
                Ngroup = group;
            }

            public void getGroup() { // get how many group of question in the lesson

                String url = "http://chineseisfun.000webhostapp.com/php/getNgroup.php";
                StringRequest js = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject valid = new JSONObject(response);
                                    Ngroup = valid.getInt("group");
                                    if(Ngroup==0){

                                                AlertDialog.Builder builder=new AlertDialog.Builder(Inlesson.this);
                                                View design=getLayoutInflater().inflate(R.layout.dialoglayout,null);
                                                ImageView image=(ImageView) design.findViewById(R.id.image);
                                                TextView text=(TextView) design.findViewById(R.id.text);
                                                Button no=(Button) design.findViewById(R.id.NO);
                                                Button ok=(Button) design.findViewById(R.id.OK);
                                                text.setText("No context in this lesson!!");
                                                image.setVisibility(View.INVISIBLE);
                                                no.setVisibility(View.GONE);
                                                builder.setView(design);
                                                final AlertDialog dialog=builder.create();
                                                dialog.show();
                                                ok.setOnClickListener(new View.OnClickListener(){

                                                    @Override
                                                    public void onClick(View v) {
                                                        dialog.cancel();
                                                        Intent intent=new Intent(Inlesson.this,Menu.class);
                                                        startActivity(intent);
                                                    }
                                                });
                                    }
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
                        params.put("lessonID", lessonid);
                        params.put("cateID", Integer.toString(cateid));
                        return params;
                    }
                };
                mQueue.add(js);

            }

            public void paycoin() {

                String url = "http://chineseisfun.000webhostapp.com/php/payCoin.php";
                session = new sharedPreferences(getApplicationContext());
                StringRequest js = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, response);
                                try {
                                    JSONObject valid = new JSONObject(response);
                                    getresponse = valid.getString("se");
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
                        params.put("Paycoin", Integer.toString(payCoin));
                        params.put("Useremail", session.getEmail());
                        return params;
                    }
                };
                mQueue.add(js);
            }

            public void getChoice() {

                String url = "http://chineseisfun.000webhostapp.com/php/Choice.php";

                StringRequest js = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject valid = new JSONObject(response);
                                    choice = valid.getString("choice");
                                    Choice = choice.split(",");
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
                        params.put("cateid", Integer.toString(cateid));
                        params.put("lesson", lessonid);
                        params.put("group", Integer.toString(Ngroup));
                        return params;
                    }
                };
                mQueue.add(js);
            }

    public void studentRange() {

        String url = "http://chineseisfun.000webhostapp.com/php/StudentRange.php";
        StringRequest js = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject valid = new JSONObject(response);
                            range = valid.getInt("score");
                            if(range<70) {
                                rant.setText("You haven't pass the lesson yet!");
                            }else if(range==70){
                                rant.setText("You are in good grade group!");
                            }else if(range==80||range==90){
                                rant.setText("You are in very good grade group!");
                            }else{
                                rant.setText("You are in excellent grade group!");
                            }
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
                params.put("lesson", lessonid);
                params.put("email", session.getEmail());
                return params;
            }
        };
        mQueue.add(js);

    }

            public void alert() {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(Inlesson.this);
                a_builder.setMessage("Your coins is insufficient !!!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                ;
                AlertDialog alert = a_builder.create();
                alert.setTitle("Alert !!!");
                alert.show();
            }

            public void back(View view) {
                Intent back = new Intent(Inlesson.this, Menu.class);
                startActivity(back);
            }
}
