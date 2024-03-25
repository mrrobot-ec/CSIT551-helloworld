package com.csit.millonairegame;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtil {
    private static final String PREFS_NAME = "millongame";
    private static final String QUESTIONS_KEY = "jsonquestions";

    public static String getQuestionsJson(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(QUESTIONS_KEY, "");
    }

    public static void saveQuestionsJson(Context context, String json) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(QUESTIONS_KEY, json);
        editor.apply();
    }
}





