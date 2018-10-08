package com.example.fong1.seniorproject;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class score extends AppCompatActivity {

    sharedPreferences session; // connect to sharedPreferences
    private RequestQueue mQueue;
    TextView score;
    TextView coin;
    TextView passORfail;
    int Yourcoin;
    ImageView imageView;
    String lessonid;
    int cateid;
    int testscore;
    int catelesson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        mQueue = Volley.newRequestQueue(this);
        session=new sharedPreferences(getApplicationContext());
        Button tnext=(Button) findViewById(R.id.next);
        score=(TextView) findViewById(R.id.score);
        coin=(TextView) findViewById(R.id.coin);
        imageView=(ImageView) findViewById(R.id.image);
        passORfail=(TextView) findViewById(R.id.passORfail);
        testscore=getIntent().getExtras().getInt("Testscore");
        lessonid=getIntent().getStringExtra("lessonid");
        cateid=getIntent().getIntExtra("cateid",0);
        catelesson=getIntent().getIntExtra("catelesson",0);
        testscore=testscore*10;
        if(testscore<61){
            passORfail.setText("You are not pass!!");
            Yourcoin=20;
            imageView.setBackground(ContextCompat.getDrawable(this,R.mipmap.sad));
        }else{
            passORfail.setText("Congratulations! Lesson Pass");
            if(testscore<71){
                imageView.setBackground(ContextCompat.getDrawable(this,R.drawable.trophy3));
                Yourcoin=80;
            }else if(testscore<81){
                imageView.setBackground(ContextCompat.getDrawable(this,R.drawable.trophy2));
                Yourcoin=100;

            }else if(testscore<91){
                imageView.setBackground(ContextCompat.getDrawable(this,R.drawable.trophy1));
                Yourcoin=125;
            }else{
                imageView.setBackground(ContextCompat.getDrawable(this,R.drawable.trophy1));
                Yourcoin=150;
            }
        }
        if(testscore>60) { // enable next lession
            if (session.getAcheive() < cateid) {
                int checkToAddProgress = Integer.parseInt(lessonid.substring(2)); // whether do the same lesson
                int now = session.getProgress();
                int unlockLesson = now + 1;
                if (now != checkToAddProgress) {
                    if (unlockLesson < 3) {
                        session.UserLessonProgress(unlockLesson);
                    } else {
                        session.UserLessonProgress(0);
                        session.createAcheive(session.getAcheive() + 1);
                    }
                } else {
                    // don't do nothing because redo had been pass's lesson
                }
            }
        }
        Savecoin();
        SaveScore();
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(0, testscore);// here you set the range, from 0 to "count" value
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                score.setText(String.valueOf(animation.getAnimatedValue()));
            }
        });
        animator.setDuration(3000); // here you set the duration of the anim
        animator.start();

        ValueAnimator animator2 = new ValueAnimator();
        animator2.setObjectValues(0, Yourcoin);// here you set the range, from 0 to "count" value
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                coin.setText(String.valueOf(animation.getAnimatedValue()));
            }
        });
        animator2.setDuration(3000); // here you set the duration of the anim
        animator2.start();

        tnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(score.this,Menu.class);
                startActivity(intent);
            }
        });
        int updateCoin = session.getCoins() + Yourcoin;
        session.createUserCoins(updateCoin);
    }

    public void SaveScore(){
        String url = "http://chineseisfun.000webhostapp.com/php/SaveScore.php";

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
                params.put("lesson", lessonid);
                params.put("email", session.getEmail());
                params.put("score",Integer.toString(testscore));
                return params;
            }
        };
        mQueue.add(js);
    }

    public void Savecoin(){
        String url ="http://chineseisfun.000webhostapp.com/php/Savecoin.php";
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
                params.put("coin", Integer.toString(Yourcoin));
                params.put("email", session.getEmail());
                params.put("cateid",Integer.toString(session.getAcheive()));
                params.put("lesson",Integer.toString(session.getProgress()));
                return params;
            }
        };
        mQueue.add(js);
    }

    @Override
    public void onBackPressed(){

        Toast.makeText(this,"can't back to previous page",Toast.LENGTH_SHORT);

    }
}
