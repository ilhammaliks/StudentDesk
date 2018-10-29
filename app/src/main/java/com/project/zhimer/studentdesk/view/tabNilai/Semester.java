package com.project.zhimer.studentdesk.view.tabNilai;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.project.zhimer.studentdesk.model.Nilai;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class Semester extends Fragment {
    View view;

    Nilai nilai;
    SessionManager sessionManager;


    public Semester() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_nilai_semester, container, false);

        sessionManager = new SessionManager(getContext());

        NilaiPersemester();

        return view;
    }

    private void NilaiPersemester() {
        String url = "https://studentdesk.uai.ac.id/api/index.php/akademik/daftarnilaipersemester/format/json";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.setBasicAuth("admin", "1234");
        params.put("uname", sessionManager.getNim());
        params.put("pwd", sessionManager.getPassword());

        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i<jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);


                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

}
