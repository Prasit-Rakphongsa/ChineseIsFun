package com.example.fong1.seniorproject;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Learn extends AppCompatActivity {

    private RequestQueue mQueue;
    ArrayList<String> linkmp3=new ArrayList<String>();
    String[] pinyin=new String[21];
    String[] pinyin2=new String[38];
    String[] pinyin3=new String[20];
    Button one,two,three,four,five,six,seven,eight,nine,ten,eleven,twelve,thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,nineteen,twenty,twentyone,twentytwo,twentythree;
    Button one1,two1,three1,four1,five1,six1,seven1,eight1,nine1,ten1,eleven1,twelve1,thirteen1,fourteen1,fifteen1,sixteen1,seventeen1,eighteen1,nineteen1,twenty1,twentyone1,twentytwo1,twentythree1;
    Button one2,two2,three2,four2,five2,six2,seven2,eight2,nine2,ten2,eleven2,twelve2,thirteen2,fourteen2,fifteen2,sixteen2,seventeen2,eighteen2,nineteen2,twenty2,twentyone2,twentytwo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        mQueue= Volley.newRequestQueue(this);
        content();
        pinyin=AppConfig.groupChoiceOne;
        pinyin2=AppConfig.getGroupChoiceTwo;
        pinyin3=AppConfig.getGroupChoiceThree;
        one=(Button) findViewById(R.id.one);
        two=(Button) findViewById(R.id.two);
        three=(Button) findViewById(R.id.three);
        four=(Button) findViewById(R.id.four);
        five=(Button) findViewById(R.id.five);
        six=(Button) findViewById(R.id.six);
        seven=(Button) findViewById(R.id.seven);
        eight=(Button) findViewById(R.id.eight);
        nine=(Button) findViewById(R.id.nine);
        ten=(Button) findViewById(R.id.ten);
        eleven=(Button) findViewById(R.id.eleven);
        twelve=(Button) findViewById(R.id.twelve);
        thirteen=(Button) findViewById(R.id.thirteen);
        fourteen=(Button) findViewById(R.id.fourteen);
        fifteen=(Button) findViewById(R.id.fifteen);
        sixteen=(Button) findViewById(R.id.sixteen);
        seventeen=(Button) findViewById(R.id.seventeen);
        eighteen=(Button) findViewById(R.id.eighteen);
        nineteen=(Button) findViewById(R.id.nineteen);
        twenty=(Button) findViewById(R.id.twenty);
        twentyone=(Button) findViewById(R.id.twentyone);
        twentytwo=(Button) findViewById(R.id.twentytwo);
        twentythree=(Button) findViewById(R.id.twentythree);
        one1=(Button) findViewById(R.id.one1);
        two1=(Button) findViewById(R.id.two1);
        three1=(Button) findViewById(R.id.three1);
        four1=(Button) findViewById(R.id.four1);
        five1=(Button) findViewById(R.id.five1);
        six1=(Button) findViewById(R.id.six1);
        seven1=(Button) findViewById(R.id.seven1);
        eight1=(Button) findViewById(R.id.eight1);
        nine1=(Button) findViewById(R.id.nine1);
        ten1=(Button) findViewById(R.id.ten1);
        eleven1=(Button) findViewById(R.id.eleven1);
        twelve1=(Button) findViewById(R.id.twelve1);
        thirteen1=(Button) findViewById(R.id.thirteen1);
        fourteen1=(Button) findViewById(R.id.fourteen1);
        fifteen1=(Button) findViewById(R.id.fifteen1);
        sixteen1=(Button) findViewById(R.id.sixteen1);
        seventeen1=(Button) findViewById(R.id.seventeen1);
        eighteen1=(Button) findViewById(R.id.eighteen1);
        nineteen1=(Button) findViewById(R.id.nineteen1);
        twenty1=(Button) findViewById(R.id.twenty1);
        twentyone1=(Button) findViewById(R.id.twentyone1);
        twentytwo1=(Button) findViewById(R.id.twentytwo1);
        twentythree1=(Button) findViewById(R.id.twentythree1);
        one2=(Button) findViewById(R.id.one2);
        two2=(Button) findViewById(R.id.two2);
        three2=(Button) findViewById(R.id.three2);
        four2=(Button) findViewById(R.id.four2);
        five2=(Button) findViewById(R.id.five2);
        six2=(Button) findViewById(R.id.six2);
        seven2=(Button) findViewById(R.id.seven2);
        eight2=(Button) findViewById(R.id.eight2);
        nine2=(Button) findViewById(R.id.nine2);
        ten2=(Button) findViewById(R.id.ten2);
        eleven2=(Button) findViewById(R.id.eleven2);
        twelve2=(Button) findViewById(R.id.twelve2);
        thirteen2=(Button) findViewById(R.id.thirteen2);
        fourteen2=(Button) findViewById(R.id.fourteen2);
        fifteen2=(Button) findViewById(R.id.fifteen2);
        sixteen2=(Button) findViewById(R.id.sixteen2);
        seventeen2=(Button) findViewById(R.id.seventeen2);
        eighteen2=(Button) findViewById(R.id.eighteen2);
        nineteen2=(Button) findViewById(R.id.nineteen2);
        twenty2=(Button) findViewById(R.id.twenty2);
        twentyone2=(Button) findViewById(R.id.twentyone2);
        twentytwo2=(Button) findViewById(R.id.twentytwo2);
        sentButton();

    }

    public void content(){

        String link="http://chineseisfun.000webhostapp.com/php/pinyin.php";

        JsonArrayRequest array=new JsonArrayRequest(link,new Response.Listener<JSONArray>(){


            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Parsing json array response
                    // loop through each json object

                    for (int i = 0; i < response.length(); i++) {

                        JSONObject person = (JSONObject) response.get(i);
                        String mp3 = person.getString("link");
                        linkmp3.add(mp3);
                    }
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
        });
        mQueue.add(array);
    }

    public void check(View v) throws IOException {
        String mp3="";
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        switch(v.getId()) {
            case R.id.one:
                mp3=linkmp3.get(0);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.one1:
                mp3=linkmp3.get(1);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.one2:
                mp3=linkmp3.get(2);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.two:
                mp3=linkmp3.get(3);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.two1:
                mp3=linkmp3.get(4);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.two2:
                mp3=linkmp3.get(5);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.three:
                mp3=linkmp3.get(6);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.three1:
                mp3=linkmp3.get(7);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.three2:
                mp3=linkmp3.get(8);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.four:
                mp3=linkmp3.get(9);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.four1:
                mp3=linkmp3.get(10);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.four2:
                mp3=linkmp3.get(11);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.five:
                mp3=linkmp3.get(12);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.five1:
                mp3=linkmp3.get(13);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.five2:
                mp3=linkmp3.get(14);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.six:
                mp3=linkmp3.get(15);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.six1:
                mp3=linkmp3.get(16);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.six2:
                mp3=linkmp3.get(17);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.seven:
                mp3=linkmp3.get(18);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.seven1:
                mp3=linkmp3.get(19);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.seven2:
                mp3=linkmp3.get(20);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.eight:
                mp3=linkmp3.get(21);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.eight1:
                mp3=linkmp3.get(22);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.eight2:
                mp3=linkmp3.get(23);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.nine:
                mp3=linkmp3.get(24);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.nine1:
                mp3=linkmp3.get(25);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.nine2:
                mp3=linkmp3.get(26);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.ten:
                mp3=linkmp3.get(27);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.ten1:
                mp3=linkmp3.get(28);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.ten2:
                mp3=linkmp3.get(29);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.eleven:
                mp3=linkmp3.get(30);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.eleven1:
                mp3=linkmp3.get(31);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.eleven2:
                mp3=linkmp3.get(32);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.twelve:
                mp3=linkmp3.get(33);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.twelve1:
                mp3=linkmp3.get(34);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.twelve2:
                mp3=linkmp3.get(35);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.thirteen:
                mp3=linkmp3.get(36);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.thirteen1:
                mp3=linkmp3.get(37);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.thirteen2:
                mp3=linkmp3.get(38);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.fourteen:
                mp3=linkmp3.get(39);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.fourteen1:
                mp3=linkmp3.get(40);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.fourteen2:
                mp3=linkmp3.get(41);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.fifteen:
                mp3=linkmp3.get(42);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.fifteen1:
                mp3=linkmp3.get(43);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.fifteen2:
                mp3=linkmp3.get(44);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.sixteen:
                mp3=linkmp3.get(45);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.sixteen1:
                mp3=linkmp3.get(46);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.sixteen2:
                mp3=linkmp3.get(47);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.seventeen:
                mp3=linkmp3.get(48);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.seventeen1:
                mp3=linkmp3.get(49);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.seventeen2:
                mp3=linkmp3.get(50);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.eighteen:
                mp3=linkmp3.get(51);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.eighteen1:
                mp3=linkmp3.get(60);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.eighteen2:
                mp3=linkmp3.get(61);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.nineteen:
                mp3=linkmp3.get(62);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.nineteen1:
                mp3=linkmp3.get(63);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.nineteen2:
                mp3=linkmp3.get(64);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.twenty:
                mp3=linkmp3.get(65);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.twenty1:
                mp3=linkmp3.get(66);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.twenty2:
                mp3=linkmp3.get(67);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.twentyone:
                mp3=linkmp3.get(68);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.twentyone1:
                mp3=linkmp3.get(69);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.twentyone2:
                mp3=linkmp3.get(70);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.twentytwo:
                mp3=linkmp3.get(71);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.twentytwo1:
                mp3=linkmp3.get(80);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.twentytwo2:
                mp3=linkmp3.get(81);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.twentythree:
                mp3=linkmp3.get(82);
                mediaPlayer.setDataSource(mp3);
                break;
            case R.id.twentythree1:
                mp3=linkmp3.get(83);
                mediaPlayer.setDataSource(mp3);
                break;
        }
        mediaPlayer.prepare();
        mediaPlayer.start();
    }


    public void sentButton(){

        one.setText(pinyin[0]);
        one1.setText(pinyin[1]);
        one2.setText(pinyin[2]);
        two.setText(pinyin[3]);
        two1.setText(pinyin[4]);
        two2.setText(pinyin[5]);
        three.setText(pinyin[6]);
        three1.setText(pinyin[7]);
        three2.setText(pinyin[8]);
        four.setText(pinyin[9]);
        four1.setText(pinyin[10]);
        four2.setText(pinyin[11]);
        five.setText(pinyin[12]);
        five1.setText(pinyin[13]);
        five2.setText(pinyin[14]);
        six.setText(pinyin[15]);
        six1.setText(pinyin[16]);
        six2.setText(pinyin[17]);
        seven.setText(pinyin[18]);
        seven1.setText(pinyin[19]);
        seven2.setText(pinyin[20]);
        eight.setText(pinyin2[0]);
        eight1.setText(pinyin2[1]);
        eight2.setText(pinyin2[2]);
        nine.setText(pinyin2[3]);
        nine1.setText(pinyin2[4]);
        nine2.setText(pinyin2[5]);
        ten.setText(pinyin2[6]);
        ten1.setText(pinyin2[7]);
        ten2.setText(pinyin2[8]);
        eleven.setText(pinyin2[9]);
        eleven1.setText(pinyin2[10]);
        eleven2.setText(pinyin2[11]);
        twelve.setText(pinyin2[12]);
        twelve1.setText(pinyin2[13]);
        twelve2.setText(pinyin2[14]);
        thirteen.setText(pinyin2[15]);
        thirteen1.setText(pinyin2[16]);
        thirteen2.setText(pinyin2[17]);
        fourteen.setText(pinyin2[18]);
        fourteen1.setText(pinyin2[19]);
        fourteen2.setText(pinyin2[20]);
        fifteen.setText(pinyin2[22]);
        fifteen1.setText(pinyin2[23]);
        fifteen2.setText(pinyin2[28]);
        sixteen.setText(pinyin2[34]);
        sixteen1.setText(pinyin2[36]);
        sixteen2.setText(pinyin2[37]);
        seventeen.setText(pinyin3[0]);
        seventeen1.setText(pinyin3[1]);
        seventeen2.setText(pinyin3[2]);
        eighteen.setText(pinyin3[3]);
        eighteen1.setText(pinyin3[4]);
        eighteen2.setText(pinyin3[5]);
        nineteen.setText(pinyin3[6]);
        nineteen1.setText(pinyin3[7]);
        nineteen2.setText(pinyin3[8]);
        twenty.setText(pinyin3[9]);
        twenty1.setText(pinyin3[10]);
        twenty2.setText(pinyin3[11]);
        twentyone.setText(pinyin3[12]);
        twentyone1.setText(pinyin3[13]);
        twentyone2.setText(pinyin3[14]);
        twentytwo.setText(pinyin3[15]);
        twentytwo1.setText(pinyin3[16]);
        twentytwo2.setText(pinyin3[17]);
        twentythree.setText(pinyin3[18]);
        twentythree1.setText(pinyin3[19]);
    }

    public void back(View view){
        super.onBackPressed();
    }
}
