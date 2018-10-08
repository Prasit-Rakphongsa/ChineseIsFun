package com.example.fong1.seniorproject;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class forgetpassword extends AppCompatActivity {

    private RequestQueue mQueue;
    TextView forget;
    EditText email,comfirm;
    Button submit;
    String keepMail;
    View line;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        mQueue = Volley.newRequestQueue(this);
        forget=(TextView) findViewById(R.id.head);
        email=(EditText) findViewById(R.id.email);
        comfirm=(EditText) findViewById(R.id.comfirm);
        line=(View) findViewById(R.id.line);
        submit=(Button) findViewById(R.id.submit);

        comfirm.setVisibility(View.GONE);
        line.setVisibility(View.GONE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkEmail();
                if(i==1){
                    if(email.getText().toString().trim().equals(comfirm.getText().toString().trim())){
                        checkEmail();
                    }else{
                        Toast.makeText(forgetpassword.this,"Passwor isn't match",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void checkEmail(){

            String url = "http://chineseisfun.000webhostapp.com/php/ForgetPassword.php";
            StringRequest js = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject valid = new JSONObject(response);
                                Boolean exist=valid.getBoolean("error");
                                if(i==0){
                                    if(exist==true){
                                        forget.setText("Create New Password");
                                        email.setText("");
                                        email.setHint("Password");
                                        comfirm.setVisibility(View.VISIBLE);
                                        line.setVisibility(View.VISIBLE);
                                        submit.setText("Save");
                                        i++;
                                    }else{

                                        Toast.makeText(forgetpassword.this,"invalid email",Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    if(exist==false){
                                        Toast.makeText(forgetpassword.this,"Successful changed your password!!",Toast.LENGTH_LONG).show();

                                        new CountDownTimer(2000, 1000) {

                                            public void onTick(long millisUntilFinished) {

                                            }

                                            public void onFinish() {
                                                Intent intent=new Intent(forgetpassword.this,Login.class);
                                                startActivity(intent);
                                            }
                                        }.start();
                                    }
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
                    String mail=email.getText().toString().trim();
                    if(i==0) {
                        keepMail=mail;
                        params.put("email",mail);
                        params.put("status",Integer.toString(i));
                    }else{
                        params.put("email",keepMail);
                        params.put("status",Integer.toString(i));
                        params.put("password",mail);

                    }
                    return params;
                }
            };
            mQueue.add(js);

    }


    public void back(View view) {
        Intent back = new Intent(forgetpassword.this,Login.class);
        startActivity(back);
    }
}
