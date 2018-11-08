package com.project.zhimer.studentdesk;

import android.content.Context;
import android.content.SharedPreferences;


public class SessionManager {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context _context;

    private static final String PREF_NAME = "studentdesk";

    public SessionManager(Context context) {
        this._context = context;
        preferences = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setNim(String data) {
        editor.putString("nim", data);
        editor.apply();
    }

    public String getNim() {
        return preferences.getString("nim", "");
    }

    public void setPassword(String data) {
        editor.putString("password", data);
        editor.apply();
    }

    public String getPassword() {
        return preferences.getString("password", "");
    }

    public void setMahasiswa(String data) {
        editor.putString("mahasiswa", data);
        editor.apply();
    }

    public String getMahasiswa() {
        return preferences.getString("mahasiswa", "");
    }

    public void setLogin(Boolean data) {
        editor.putBoolean("login", data);
        editor.apply();
    }

    public Boolean isLogin() {
        return preferences.getBoolean("login", false);
    }

    public void setToken(String data) {
        editor.putString("token", data);
        editor.apply();
    }

    public String getToken() {
        return preferences.getString("token", "");
    }

    public String getUrl() {
        return preferences.getString("url", "https://studentdesk.uai.ac.id/api/index.php");
    }

    public String getAuthUsername() {
        return preferences.getString("username", "admin");
    }

    public String getAuthPassword() {
        return preferences.getString("Password", "1234");
    }
}
