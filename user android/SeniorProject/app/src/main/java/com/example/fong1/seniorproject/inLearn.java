package com.example.fong1.seniorproject;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.List;
import java.util.Map;

/**
 * Created by NgocTri on 11/15/2015.
 */
public class inLearn extends AppCompatActivity {
    private ListView lvProduct;
    private WordAdapter adapter;
    private List<Word> mWord;
    private RequestQueue mQueue;
    ArrayList<String> mp3=new ArrayList<String>();
    AnimationDrawable animation;
    int cate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_learn);

        mQueue= Volley.newRequestQueue(this);
        mWord = new ArrayList<>();
        lvProduct = (ListView)findViewById(R.id.listview);
        cate=getIntent().getIntExtra("cateid",0);
        content();
        AlertDialog.Builder loading=new AlertDialog.Builder(this);
        View design=getLayoutInflater().inflate(R.layout.loading,null);
        ImageView load=(ImageView) design.findViewById(R.id.load);
        animation=(AnimationDrawable) load.getDrawable();
        loading.setView(design);
        final AlertDialog dialogLoad=loading.create();
        dialogLoad.show();
        animation.start();
        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                animation.stop();
                dialogLoad.cancel();
            }
        }.start();
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
                //Ex: display msg with product id get from view.getTag
                int i= (int) view.getTag();
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(mp3.get(i));
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void content(){

        String link="http://chineseisfun.000webhostapp.com/php/ServerLearn.php";

        StringRequest js = new StringRequest(Request.Method.POST, link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array=new JSONArray(response);
                            for (int i = 0; i<response.length(); i++) {
                                try {
                                    JSONObject person = array.getJSONObject(i);
                                    String word = person.getString("word");
                                    String pinyin = person.getString("pinyin");
                                    String meaning = person.getString("meaning");
                                    String audio = person.getString("audio");
                                    mp3.add(audio);
                                    //Add sample data for list
                                    //We can get data from DB, webservice here
                                    mWord.add(new Word(i, word, pinyin, meaning));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    /*Toast.makeText(getApplicationContext(),
                                            "Error......: " + e.getMessage(),
                                            Toast.LENGTH_LONG).show();*/
                                }
                            }
                            //Init adapter
                            adapter = new WordAdapter(inLearn.this, mWord);
                            lvProduct.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "Error volley: " + error.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }) {
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cateid",Integer.toString(cate));
                return params;
            }
        };
        mQueue.add(js);
    }

    public void back(View view){
        super.onBackPressed();
    }

}
