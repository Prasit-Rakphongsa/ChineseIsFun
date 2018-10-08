package com.example.fong1.seniorproject;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

public class Menu extends AppCompatActivity {

    private long backPressedTime;
    private Toast backtoast;
    sharedPreferences session;
    BottomNavigationView navigationView;
    private RequestQueue mQueue;
    int level; //user category
    int cateid; //user click category
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9;
    ImageView imageView10,imageView11,imageView12,imageView13,imageView14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        session=new sharedPreferences(getApplicationContext());
        mQueue = Volley.newRequestQueue(this);
        navigationView=(BottomNavigationView) findViewById(R.id.Navigation);
        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.me){
                    Intent intent=new Intent(Menu.this,Me.class);
                    startActivity(intent);
                } else if(item.getItemId()==R.id.learn){
                    Intent intent=new Intent(Menu.this,mainLearn.class);
                    startActivity(intent);
                }
            }
        });
        level=session.getAcheive();
        imageView1=(ImageView) findViewById(R.id.level1);
        imageView2=(ImageView) findViewById(R.id.level2);
        imageView3=(ImageView) findViewById(R.id.level3);
        imageView4=(ImageView) findViewById(R.id.level4);
        imageView5=(ImageView) findViewById(R.id.level5);
        imageView6=(ImageView) findViewById(R.id.level6);
        imageView7=(ImageView) findViewById(R.id.level7);
        imageView8=(ImageView) findViewById(R.id.level8);
        imageView9=(ImageView) findViewById(R.id.level9);
        imageView10=(ImageView) findViewById(R.id.level10);
        imageView11=(ImageView) findViewById(R.id.level11);
        imageView12=(ImageView) findViewById(R.id.level12);
        imageView13=(ImageView) findViewById(R.id.level13);
        imageView14=(ImageView) findViewById(R.id.level14);
        imageView1.setImageResource(R.drawable.pinyin);
        imageView2.setImageResource(R.drawable.basic);
        imageView3.setImageResource(R.drawable.number);
        imageView4.setImageResource(R.drawable.family);
        imageView5.setImageResource(R.drawable.times);
        imageView6.setImageResource(R.drawable.classroom);
        imageView7.setImageResource(R.drawable.travel);
        imageView8.setImageResource(R.drawable.animal);
        imageView9.setImageResource(R.drawable.season);
        imageView10.setImageResource(R.drawable.career);
        imageView11.setImageResource(R.drawable.con1);
        imageView12.setImageResource(R.drawable.con2);
        imageView13.setImageResource(R.drawable.con3);
        categoryGrant();

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
    public void categoryGrant(){
        if(level<1){
            imageView2.setEnabled(false);
            imageView3.setEnabled(false);
            imageView4.setEnabled(false);
            imageView5.setEnabled(false);
            imageView6.setEnabled(false);
            imageView7.setEnabled(false);
            imageView8.setEnabled(false);
            imageView9.setEnabled(false);
            imageView10.setEnabled(false);
            imageView11.setEnabled(false);
            imageView12.setEnabled(false);
            imageView13.setEnabled(false);
            imageView2.setImageResource(R.drawable.lock);
            imageView3.setImageResource(R.drawable.lock);
            imageView4.setImageResource(R.drawable.lock);
            imageView5.setImageResource(R.drawable.lock);
            imageView6.setImageResource(R.drawable.lock);
            imageView7.setImageResource(R.drawable.lock);
            imageView8.setImageResource(R.drawable.lock);
            imageView9.setImageResource(R.drawable.lock);
            imageView10.setImageResource(R.drawable.lock);
            imageView11.setImageResource(R.drawable.lock);
            imageView12.setImageResource(R.drawable.lock);
            imageView13.setImageResource(R.drawable.lock);
        }else if(level==1){
            imageView3.setEnabled(false);
            imageView4.setEnabled(false);
            imageView5.setEnabled(false);
            imageView6.setEnabled(false);
            imageView7.setEnabled(false);
            imageView8.setEnabled(false);
            imageView9.setEnabled(false);
            imageView10.setEnabled(false);
            imageView11.setEnabled(false);
            imageView12.setEnabled(false);
            imageView13.setEnabled(false);
            imageView3.setImageResource(R.drawable.lock);
            imageView4.setImageResource(R.drawable.lock);
            imageView5.setImageResource(R.drawable.lock);
            imageView6.setImageResource(R.drawable.lock);
            imageView7.setImageResource(R.drawable.lock);
            imageView8.setImageResource(R.drawable.lock);
            imageView9.setImageResource(R.drawable.lock);
            imageView10.setImageResource(R.drawable.lock);
            imageView11.setImageResource(R.drawable.lock);
            imageView12.setImageResource(R.drawable.lock);
            imageView13.setImageResource(R.drawable.lock);
        }else if(level==2){
            imageView4.setEnabled(false);
            imageView5.setEnabled(false);
            imageView6.setEnabled(false);
            imageView7.setEnabled(false);
            imageView8.setEnabled(false);
            imageView5.setEnabled(false);
            imageView6.setEnabled(false);
            imageView7.setEnabled(false);
            imageView8.setEnabled(false);
            imageView9.setEnabled(false);
            imageView10.setEnabled(false);
            imageView11.setEnabled(false);
            imageView12.setEnabled(false);
            imageView13.setEnabled(false);
            imageView4.setImageResource(R.drawable.lock);
            imageView5.setImageResource(R.drawable.lock);
            imageView6.setImageResource(R.drawable.lock);
            imageView7.setImageResource(R.drawable.lock);
            imageView8.setImageResource(R.drawable.lock);
            imageView9.setImageResource(R.drawable.lock);
            imageView10.setImageResource(R.drawable.lock);
            imageView11.setImageResource(R.drawable.lock);
            imageView12.setImageResource(R.drawable.lock);
            imageView13.setImageResource(R.drawable.lock);
        }else if(level==3){
            imageView5.setEnabled(false);
            imageView6.setEnabled(false);
            imageView7.setEnabled(false);
            imageView8.setEnabled(false);
            imageView6.setEnabled(false);
            imageView7.setEnabled(false);
            imageView8.setEnabled(false);
            imageView9.setEnabled(false);
            imageView10.setEnabled(false);
            imageView11.setEnabled(false);
            imageView12.setEnabled(false);
            imageView13.setEnabled(false);
            imageView6.setImageResource(R.drawable.lock);
            imageView7.setImageResource(R.drawable.lock);
            imageView8.setImageResource(R.drawable.lock);
            imageView9.setImageResource(R.drawable.lock);
            imageView10.setImageResource(R.drawable.lock);
            imageView11.setImageResource(R.drawable.lock);
            imageView12.setImageResource(R.drawable.lock);
            imageView13.setImageResource(R.drawable.lock);
        }else if(level==4){
            imageView6.setEnabled(false);
            imageView7.setEnabled(false);
            imageView8.setEnabled(false);
            imageView9.setEnabled(false);
            imageView10.setEnabled(false);
            imageView11.setEnabled(false);
            imageView12.setEnabled(false);
            imageView13.setEnabled(false);
            imageView6.setImageResource(R.drawable.lock);
            imageView7.setImageResource(R.drawable.lock);
            imageView8.setImageResource(R.drawable.lock);
            imageView9.setImageResource(R.drawable.lock);
            imageView10.setImageResource(R.drawable.lock);
            imageView11.setImageResource(R.drawable.lock);
            imageView12.setImageResource(R.drawable.lock);
            imageView13.setImageResource(R.drawable.lock);
        }else if(level==5){
            imageView7.setEnabled(false);
            imageView8.setEnabled(false);
            imageView9.setEnabled(false);
            imageView10.setEnabled(false);
            imageView11.setEnabled(false);
            imageView12.setEnabled(false);
            imageView13.setEnabled(false);
            imageView7.setImageResource(R.drawable.lock);
            imageView8.setImageResource(R.drawable.lock);
            imageView9.setImageResource(R.drawable.lock);
            imageView10.setImageResource(R.drawable.lock);
            imageView11.setImageResource(R.drawable.lock);
            imageView12.setImageResource(R.drawable.lock);
            imageView13.setImageResource(R.drawable.lock);
        }else if(level==6){
            imageView8.setEnabled(false);
            imageView9.setEnabled(false);
            imageView10.setEnabled(false);
            imageView11.setEnabled(false);
            imageView12.setEnabled(false);
            imageView13.setEnabled(false);
            imageView8.setImageResource(R.drawable.lock);
            imageView9.setImageResource(R.drawable.lock);
            imageView10.setImageResource(R.drawable.lock);
            imageView11.setImageResource(R.drawable.lock);
            imageView12.setImageResource(R.drawable.lock);
            imageView13.setImageResource(R.drawable.lock);
        }else if(level==7){
            imageView9.setEnabled(false);
            imageView10.setEnabled(false);
            imageView11.setEnabled(false);
            imageView12.setEnabled(false);
            imageView13.setEnabled(false);
            imageView9.setImageResource(R.drawable.lock);
            imageView10.setImageResource(R.drawable.lock);
            imageView11.setImageResource(R.drawable.lock);
            imageView12.setImageResource(R.drawable.lock);
            imageView13.setImageResource(R.drawable.lock);
        }else if(level==8){
            imageView10.setEnabled(false);
            imageView11.setEnabled(false);
            imageView12.setEnabled(false);
            imageView13.setEnabled(false);
            imageView10.setImageResource(R.drawable.lock);
            imageView11.setImageResource(R.drawable.lock);
            imageView12.setImageResource(R.drawable.lock);
            imageView13.setImageResource(R.drawable.lock);
        }else if(level==9){
            imageView11.setEnabled(false);
            imageView12.setEnabled(false);
            imageView13.setEnabled(false);
            imageView11.setImageResource(R.drawable.lock);
            imageView12.setImageResource(R.drawable.lock);
            imageView13.setImageResource(R.drawable.lock);
        }else if(level==10){
            imageView12.setEnabled(false);
            imageView13.setEnabled(false);
            imageView12.setImageResource(R.drawable.lock);
            imageView13.setImageResource(R.drawable.lock);
        }else if(level==11){
            imageView13.setEnabled(false);
            imageView13.setImageResource(R.drawable.lock);
        }else if(level==13){
            imageView14.setImageResource(R.drawable.complete);
        }

    }
    public void lesson(View view){
        if(view.getId()==R.id.level1){
            cateid=1;
            //getNOlesson();
        }else if(view.getId()==R.id.level2){
            cateid=2;
            //getNOlesson();
        }else if(view.getId()==R.id.level3){
            cateid=3;
        }else if(view.getId()==R.id.level4){
            cateid=4;
        }else if(view.getId()==R.id.level5){
            cateid=5;
        }else if(view.getId()==R.id.level6){
            cateid=6;
        }else if(view.getId()==R.id.level7){
            cateid=7;
        }else if(view.getId()==R.id.level8){
            cateid=8;
        }else if(view.getId()==R.id.level9){
            cateid=9;
        }else if(view.getId()==R.id.level10){
            cateid=10;
        }else if(view.getId()==R.id.level11){
            cateid=11;
        }else if(view.getId()==R.id.level12){
            cateid=12;
        }else if(view.getId()==R.id.level13){
            cateid=13;
        }
                Intent intent=new Intent(Menu.this,lesson.class);
                intent.putExtra("cateid",cateid);
                startActivity(intent);

    }


}
