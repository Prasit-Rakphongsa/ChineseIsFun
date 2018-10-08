package com.example.fong1.seniorproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class mainLearn extends AppCompatActivity {

    private long backPressedTime;
    private Toast backtoast;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_learn);

        navigationView=(BottomNavigationView) findViewById(R.id.Navigation);
        navigationView.setSelectedItemId(R.id.learn);
        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.test){
                    Intent intent=new Intent(mainLearn.this,Menu.class);
                    startActivity(intent);
                } else if(item.getItemId()==R.id.me){
                    Intent intent=new Intent(mainLearn.this,Me.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void check(View v){
        Intent intent =new Intent(this, inLearn.class);
        switch(v.getId()) {
            case R.id.one:
                intent = new Intent(this, Learn.class);
                break;
            case R.id.one1:
                intent.putExtra("cateid",1);
                break;
            case R.id.two:
                intent.putExtra("cateid",2);
                break;
            case R.id.two1:
                intent.putExtra("cateid",3);
                break;
            case R.id.three:
                intent.putExtra("cateid",4);
                break;
            case R.id.three1:
                intent.putExtra("cateid",5);
                break;
            case R.id.four:
                intent.putExtra("cateid",6);
                break;
            case R.id.four1:
                intent.putExtra("cateid",7);
                break;
            case R.id.five:
                intent.putExtra("cateid",8);
                break;
            case R.id.five1:
                intent.putExtra("cateid",9);
                break;
            case R.id.six:
                intent.putExtra("cateid",10);
                break;
            case R.id.seven:
                intent.putExtra("cateid",11);
                break;
            case R.id.eight:
                intent.putExtra("cateid",12);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        if(backPressedTime+2000>System.currentTimeMillis()){
            backtoast.cancel();
            this.finishAffinity();
        }else{
            backtoast= Toast.makeText(this,"Press back again to exit",Toast.LENGTH_SHORT);
            backtoast.show();;
        }
        backPressedTime=System.currentTimeMillis();
    }
}

