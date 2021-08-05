package com.ovi.demotask;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ovi.demotask.model.SubcatgItem;

import java.lang.reflect.Type;
import java.util.List;

public class SharedPref {

    public static final String SHARED_PREF_MAIN="shared_preference_main";
    private static SharedPreferences mSharedPref;
    public static final String NAME = "com.ovi.demotask";

    private SharedPref()
    {

    }
    public static void init(Context context)
    {
        if(mSharedPref == null)
            mSharedPref = context.getSharedPreferences( NAME, Context.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static void saveArrayList(List<SubcatgItem> list, String key){
        SharedPreferences.Editor editor = mSharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();

    }

    public static List<SubcatgItem> getArrayList(String key){
        Gson gson = new Gson();
        String json = mSharedPref.getString(key, null);
        Type type = new TypeToken<List<SubcatgItem>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
