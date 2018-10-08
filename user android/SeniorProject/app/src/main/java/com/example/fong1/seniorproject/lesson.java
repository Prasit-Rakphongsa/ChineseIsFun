package com.example.fong1.seniorproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class lesson extends AppCompatActivity {

    sharedPreferences session;
    Button Start2,Start3;
    int cateid;
    TextView header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        session=new sharedPreferences(getApplicationContext());
        cateid=getIntent().getIntExtra("cateid",0);
        header=(TextView) findViewById(R.id.header);
        Start2=(Button) findViewById(R.id.start2);
        Start3=(Button) findViewById(R.id.start3);
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
        setEnable();
    }

    public void start(View view){
        Intent intent=new Intent(this,Inlesson.class);
        if(view.getId()==R.id.start1){
            intent.putExtra("lessonID",Integer.toString(cateid)+".1");

        }else if(view.getId()==R.id.start2){
            intent.putExtra("lessonID",Integer.toString(cateid)+".2");
        }else{
            intent.putExtra("lessonID",Integer.toString(cateid)+".3");
        }
        intent.putExtra("cateid",cateid);
        startActivity(intent);
    }

    public void setEnable() {
        if(cateid-1<session.getAcheive()){
            Start2.setEnabled(true);
            Start3.setEnabled(true);
        }else {
            int nowProgress = session.getProgress();
            if (nowProgress == 0) {
                Start2.setEnabled(false);
                Start3.setEnabled(false);
            } else if (nowProgress == 1) {
                Start2.setEnabled(true);
                Start3.setEnabled(false);
            } else if (nowProgress == 2) {
                Start2.setEnabled(true);
                Start3.setEnabled(true);
            }
        }
    }

    public void back(View view){
        Intent back=new Intent(this,Menu.class);
        startActivity(back);
    }
}
