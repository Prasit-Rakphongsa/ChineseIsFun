package com.example.fong1.seniorproject;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
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


public class Login extends AppCompatActivity {

    private static final String TAG = "Login";
    ProgressDialog progressDialog;
    private EditText loginInputEmail, loginInputPassword;
    private Button btnlogin;
    private TextView btnLinkSignup,forgetpass;
    sharedPreferences session;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session=new sharedPreferences(getApplicationContext());
        mQueue= Volley.newRequestQueue(this);
        loginInputEmail = (EditText) findViewById(R.id.email);
        loginInputPassword = (EditText) findViewById(R.id.password);
        forgetpass=(TextView) findViewById(R.id.forgetpass);
        btnlogin = (Button) findViewById(R.id.login);
        btnLinkSignup = (TextView) findViewById(R.id.register);
        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(loginInputEmail.getText().toString(),
                        loginInputPassword.getText().toString());
            }
        });

        btnLinkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);

            }
        });

        forgetpass.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,forgetpassword.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser(final String email, final String password) {
        // Tag used to cancel the request
        String cancel_req_tag = "login";
        progressDialog.setMessage("Logging you in...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                        getAchievement();
                        session.createLoginSession(email);
                        int coins=jObj.getJSONObject("user").getInt("coins");
                        session.createUserCoins(coins);
                        String name=jObj.getJSONObject("user").getString("name");
                        session.createUserName(name);
                        String image=jObj.getJSONObject("user").getString("image");
                        session.createUserProfile(image);
                       // String user = jObj.getJSONObject("user").getString("name");
                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run(){
                                hideDialog();
                                // Launch User activity
                                Intent intent = new Intent(
                                        Login.this,
                                        FirstPage.class);
                                //intent.putExtra("username", user);
                                startActivity(intent);
                                finish();
                            }
                        },2000);

                    } else {
                        hideDialog();
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public void getAchievement(){

        String url="http://chineseisfun.000webhostapp.com/php/checkLevel.php";

        StringRequest js = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject valid = new JSONObject(response);
                            session.UserLessonProgress(valid.getInt("lesson"));
                            session.createAcheive(valid.getInt("Category"));
                        }catch (JSONException e) {
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
                params.put("email",session.getEmail());
                return params;
            }
        };
        mQueue.add(js);
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }


}