package com.project.zhimer.studentdesk.view.tabQuran;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.project.zhimer.studentdesk.adapter.HasilQuranAdapter;
import com.project.zhimer.studentdesk.model.Quran;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class Score extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Quran> listScoreQuran;

    private ProgressView progressView;

    SessionManager sessionManager;
    Quran quran;


    public Score() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_quran_score, container, false);

        listScoreQuran = new ArrayList<>();
        adapter = new HasilQuranAdapter(listScoreQuran, getActivity());

        recyclerView = view.findViewById(R.id.recyclerViewScoreQuran);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        progressView = view.findViewById(R.id.circular);

        sessionManager = new SessionManager(getContext());

        progressView.setVisibility(View.VISIBLE);
        progressView.start();


        GetDataScoreQuran();
        return view;
    }

    private void GetDataScoreQuran() {
        String url = sessionManager.getUrl() + "/akademik/BacaQuran/format/json";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.setBasicAuth(sessionManager.getAuthUsername(), sessionManager.getAuthPassword());
        params.put("uname", sessionManager.getNim());
        params.put("pwd", sessionManager.getPassword());
        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        quran = new Quran();

                        String tanggal = object.getString("Tanggal");
                        String score = object.getString("NilaiTest");
                        String status = object.getString("Kelulusan");

                        SimpleDateFormat plainDate = new SimpleDateFormat("yyyy-M-d");
                        Date date = null;

                        try {
                            date = plainDate.parse(tanggal);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
                        String tanggalTest = dateFormat.format(date);

                        quran.setTanggalTest(tanggalTest);
                        quran.setScore(score);
                        quran.setStatus(status);

                        listScoreQuran.add(quran);
                        adapter.notifyDataSetChanged();
                    }
                    progressView.setVisibility(View.GONE);
                    progressView.stop();

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
