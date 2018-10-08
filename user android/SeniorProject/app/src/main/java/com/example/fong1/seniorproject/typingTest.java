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
import android.widget.EditText;
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

public class typingTest extends AppCompatActivity {

    ArrayList<String> Choice = new ArrayList<String>(); // keep all the correct anwer
    String[] meaning=new String[10]; // store meaning
    String lessonid;
    int cateid;
    TextView showscore;
    TextView question,Meaning;
    EditText answer;
    private RequestQueue mQueue;
    int loopNumber=0;
    int score=0;
    int Ngroup=0; //getIntent group number to test
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typing_test);

        mQueue = Volley.newRequestQueue(this);
        showscore=(TextView) findViewById(R.id.showScore);
        question = (TextView) findViewById(R.id.question);
        Meaning=(TextView) findViewById(R.id.meaning);
        answer=(EditText) findViewById(R.id.editText);
        lessonid=getIntent().getStringExtra("lessonid");
        Ngroup=getIntent().getIntExtra("group",0);
        Choice=getIntent().getStringArrayListExtra("choice");
        cateid=getIntent().getIntExtra("cateid",0);
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
            Meaning.setText("Meaning : "+meaning[loopNumber]);
        }else{
            Intent intent=new Intent(this,score.class);
            intent.putExtra("Testscore",score);
            intent.putExtra("cateid",cateid);
            intent.putExtra("lessonid", lessonid);
            startActivity(intent);
            //Toast.makeText(this,"....Finished....",Toast.LENGTH_LONG).show();
        }
    }

    public void next(View view){
        String YourAnswer=answer.getText().toString();
        if( YourAnswer=="") {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
            a_builder.setMessage("No Answer!!!")
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
            if ( YourAnswer.equals(Choice.get(loopNumber))) {
                score++;
            }
            showscore.setText(score + "/10");
            loopNumber++;
            answer.setText("");
            Looptest();

        }
    }

    @Override
    public void onBackPressed(){

        Toast.makeText(this,"can't back to previous page",Toast.LENGTH_SHORT);

    }
}

