package com.example.fong1.seniorproject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class test2 extends AppCompatActivity {

    String[] groupChoice; //keep all the lesson choices and random to be a wrong answer
    String theQuestion;
    ArrayList<String> Choice = new ArrayList<String>(); // keep all the correct anwer
    String[] groupLink=new String[10]; // audio link
    String[] meaning=new String[10]; // store meaning
    String lessonid;
    int cateid;
    Button bone;
    Button btwo;
    Button bthree;
    Button bfour;
    TextView showscore;
    ImageView sound;
    TextView question,Meaning;
    private RequestQueue mQueue;
    int loopNumber=0;
    int ButtomCorrectPosition=0;
    int yourAnswer=4;
    int score=0;
    int Ngroup=0; //getIntent group number to test
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        mQueue = Volley.newRequestQueue(this);
        bone = (Button) findViewById(R.id.one);
        btwo = (Button) findViewById(R.id.two);
        bthree = (Button) findViewById(R.id.three);
        bfour = (Button) findViewById(R.id.four);
        showscore=(TextView) findViewById(R.id.showScore);
        question = (TextView) findViewById(R.id.question);
        Meaning=(TextView) findViewById(R.id.meaning);
        sound=(ImageView) findViewById(R.id.sound);
        lessonid=getIntent().getStringExtra("lessonid");
        Ngroup=getIntent().getIntExtra("group",0);
        Choice=getIntent().getStringArrayListExtra("choice");
        cateid=getIntent().getIntExtra("cateid",0);
        theQuestion=AppConfig.question2;
        question.setText(theQuestion);
        groupChoice=AppConfig.Categoly21;
        Toast.makeText(this,Choice.get(9),Toast.LENGTH_SHORT).show();
        Link();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading, please wait...");
        progressDialog.show();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                progressDialog.dismiss();
                Looptest();
            }
        },2000);
    }


    public void Link(){
        String url = "http://chineseisfun.000webhostapp.com/php/groupQuestion.php";

        StringRequest js = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array=new JSONArray(response);
                            for (int i = 0; i <response.length(); i++) {
                                try {
                                    JSONObject jsonObject = array.getJSONObject(i);
                                    groupLink[i] = jsonObject.getString("audio");
                                    meaning[i] = jsonObject.getString("meaning");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cateid",Integer.toString(cateid));
                params.put("lesson",lessonid);
                params.put("group",Integer.toString(Ngroup));
                return params;
            }
        };
        mQueue.add(js);
    }

    public void Looptest(){
        if(loopNumber<10) {
            //remenber which position have been selected and selected it from index of groupChoice
            ArrayList<Integer> selectedPositon = new ArrayList<Integer>();
            int selectedUsed = 0;
            Random random = new Random();
            int choiceRandom = random.nextInt(4);
            ButtomCorrectPosition = choiceRandom;
            for(int z=0;z<2;z++) {
                if (selectedPositon.size() == 0) {
                    int newRandom = random.nextInt(groupChoice.length); // random one wrong choice
                    // pinyin that we random must not same with the corrected answer
                    if(!groupChoice[newRandom].equals(Choice.get(loopNumber))) {
                        selectedPositon.add(newRandom);
                    } else {
                        --z;
                    }
                } else {
                    for (int i = 0; i < 2; i++) {
                        int newRandom = random.nextInt(groupChoice.length);
                        if (i == 0) {
                            // not selected the same random number that we were selected before
                            if (selectedPositon.get(i) != newRandom) {
                                if (!groupChoice[newRandom].equals(Choice.get(loopNumber))) {
                                    selectedPositon.add(newRandom);
                                } else {
                                    --i;
                                }
                            } else {
                                --i;
                            }
                        } else if (i > 0) {
                            if (selectedPositon.get(0) != newRandom && selectedPositon.get(1) != newRandom) {
                                if (!groupChoice[newRandom].equals(Choice.get(loopNumber))) {
                                    selectedPositon.add(newRandom);
                                } else {
                                    --i;
                                }
                            } else {
                                --i;
                            }
                        }
                    }
                }
            }
            if (choiceRandom == 0) {
                bone.setText(Choice.get(loopNumber));
                //bone.setBackgroundColor(Color.GREEN);
            } else {
                bone.setText(groupChoice[selectedPositon.get(selectedUsed)]);
                ++selectedUsed;
            }
            if (choiceRandom == 1) {
                btwo.setText(Choice.get(loopNumber));
            } else {
                btwo.setText(groupChoice[selectedPositon.get(selectedUsed)]);
                ++selectedUsed;
            }
            if (choiceRandom == 2) {
                bthree.setText(Choice.get(loopNumber));
            } else {
                bthree.setText(groupChoice[selectedPositon.get(selectedUsed)]);
                ++selectedUsed;
            }
            if (choiceRandom == 3) {
                bfour.setText(Choice.get(loopNumber));
            } else {
                bfour.setText(groupChoice[selectedPositon.get(selectedUsed)]);
            }
            play(loopNumber);
            Meaning.setText("Meaning : "+meaning[loopNumber]);
            loopNumber++;
        }else{
            Intent intent=new Intent(this,score.class);
            intent.putExtra("Testscore",score);
            intent.putExtra("cateid",cateid);
            intent.putExtra("lessonid", lessonid);
            startActivity(intent);
            //Toast.makeText(this,"....Finished....",Toast.LENGTH_LONG).show();
        }
    }

    public void check(View view) {
        if (view.getId() == R.id.one) {
            bone.setBackgroundColor(Color.GREEN);
            yourAnswer=0;
            btwo.setBackgroundColor(Color.WHITE);
            bthree.setBackgroundColor(Color.WHITE);
            bfour.setBackgroundColor(Color.WHITE);
        } else if (view.getId() == R.id.two) {
            btwo.setBackgroundColor(Color.GREEN);
            yourAnswer = 1;
            bone.setBackgroundColor(Color.WHITE);
            bthree.setBackgroundColor(Color.WHITE);
            bfour.setBackgroundColor(Color.WHITE);
        } else if (view.getId() == R.id.three) {
            bthree.setBackgroundColor(Color.GREEN);
            yourAnswer = 2;
            bone.setBackgroundColor(Color.WHITE);
            btwo.setBackgroundColor(Color.WHITE);
            bfour.setBackgroundColor(Color.WHITE);
        } else {
            bfour.setBackgroundColor(Color.GREEN);
            yourAnswer = 3;
            bone.setBackgroundColor(Color.WHITE);
            btwo.setBackgroundColor(Color.WHITE);
            bthree.setBackgroundColor(Color.WHITE);
        }

        // countdown();
    }

    public void play(final int soundPosition) {
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url =groupLink[soundPosition];
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(String.valueOf(url));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
            }
        });

    }

    public void next(View view){
        if(yourAnswer==4) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
            a_builder.setMessage("Please select your answer!!!")
                    .setCancelable(false)
                    .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
            ;
            AlertDialog alert = a_builder.create();
            alert.setTitle("Alert !!!");
            alert.show();
        }else{
            if (yourAnswer == ButtomCorrectPosition) {
                score++;
            }
            bone.setBackgroundColor(Color.WHITE);
            btwo.setBackgroundColor(Color.WHITE);
            bthree.setBackgroundColor(Color.WHITE);
            bfour.setBackgroundColor(Color.WHITE);
            showscore.setText(score + "/10");
            yourAnswer=4;
            Looptest();

        }
    }

    @Override
    public void onBackPressed(){

        Toast.makeText(this,"can't back to previous page",Toast.LENGTH_SHORT);

    }
}
