package app.manaper.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import app.manaper.models.allTrips.AllTrips;
import app.manaper.models.login.UserItem;


public class UserPreferenceHelper {
    private static UserPreferenceHelper mInstance;
    private static Context mCtx;
    private static final String SHARED_NAMES = "LuandrySharedPref";
    private static final String SHARED_PREF_NAME = "myshared";

    private UserPreferenceHelper(Context context) {
        mCtx = context;
    }

    public static synchronized UserPreferenceHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UserPreferenceHelper(context);
        }
        return mInstance;
    }

    public void userLogin(UserItem userData) {
        loggout();
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userData);
        editor.putString("userData", json);
        editor.apply();
        editor.commit();

    }

    public void addUserPhone(String userPhone) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userPhone", userPhone);
        editor.apply();

    }

    public void addTripId(String tripId) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tripId", tripId);
        editor.apply();

    }

    public void addLastTripDate(String tripDate) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tripDate", tripDate);
        editor.apply();

    }

    public void addArrDepsId(String arrId) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("trip_arr_deps_type", arrId);
        editor.apply();

    }

    public void addSecDepsId(String secId) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("trip_fir_secs_type", secId);
        editor.apply();

    }


    public String getUserPhone() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("userPhone", null);
    }

    public String getTripId() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("tripId", null);
    }

    public String getLastTripDate() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("tripDate", null);
    }

    public String getArrDepsTypes() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("trip_arr_deps_type", null);
    }

    public String getSecDepsTypes() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("trip_fir_secs_type", null);
    }

    public String addUserData() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("userData", null);

    }

    public UserItem getUserData() {
        Gson gson = new Gson();
        String json = addUserData();
        UserItem obj = gson.fromJson(json, UserItem.class);
//        if(obj==null)return new UserItem();
        return obj;
    }

    public boolean loggout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public void removeArrDepsId() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove("trip_arr_deps_type").apply();

    }

    public void removeSecDepsId() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove("trip_fir_secs_type").apply();

    }

    public void setLanguage(Context context, String language) {
        SharedPreferences userDetails = context.getSharedPreferences("languageData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userDetails.edit();
        editor.putString("language", language);
        editor.putBoolean("haveLanguage", true);
        editor.apply();
    }

    public String getCurrentLanguage(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("languageData", Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("haveLanguage", false)) return "en";
        return sharedPreferences.getString("language", "en");
    }

    public void saveToken(String token) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("TOKEN", token);
        editor.apply();

    }

    public String getToken() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("TOKEN", null);

    }

}
