package com.example.fong1.seniorproject;

/**
 * Created by fong1 on 2/20/2018.
 */
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class sharedPreferences {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // Email address (make variable public to access from outside)
    private static final String KEY_EMAIL = "email";

    private static final String Key_COINS="coins";

    private static final String Key_ACHEIVE="acheive"; // user category

    private  static final String Key_UserLesson="userlesson";

    protected static final int number_of_lesson=3;

    private  static final String Key_UserName="username";

    private  static final String Key_UserProfile="userprofile";
    //set category 1 availabe

    // Constructor
    public sharedPreferences(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();
    }

    /**
     * Create UserCoins session
     * */
    public void createUserCoins(int coins){

        // Storing coins in pref
        editor.putInt(Key_COINS, coins);

        // commit changes
        editor.commit();
    }

    public void createUserProfile(String image){

        // Storing coins in pref
        editor.putString(Key_UserProfile, image);

        // commit changes
        editor.commit();
    }

    public void createUserName(String name){

        // Storing coins in pref
        editor.putString(Key_UserName, name);

        // commit changes
        editor.commit();
    }

    public void createAcheive(int getacheive){

        // Storing coins in pref
        editor.putInt(Key_ACHEIVE, getacheive);

        // commit changes
        editor.commit();
    }

    public void UserLessonProgress(int lesson){

        // Storing coins in pref
        editor.putInt(Key_UserLesson, lesson);

        // commit changes
        editor.commit();
    }
    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public boolean checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
             Intent i = new Intent(_context, Login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);

            return true;

        }

        return false;
    }

    public String getEmail(){
        String email=pref.getString("email",null);
        return email;
    }

    public String getUserName(){
        String name=pref.getString("username",null);
        return name;
    }

    public String getUserProfile(){
        String image=pref.getString("userprofile",null);
        return image;
    }

    public int getCoins(){
        int coins=pref.getInt("coins",0);
        return coins;
  }
    public int getAcheive(){
        int sendacheive=pref.getInt("acheive",0);
        return sendacheive;
    }

    public int getProgress(){
        int progress=pref.getInt("userlesson",0);
        return progress;
    }
    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL,""));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, Login.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }


}
