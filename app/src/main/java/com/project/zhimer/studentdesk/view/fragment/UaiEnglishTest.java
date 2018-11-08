package com.project.zhimer.studentdesk.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.project.zhimer.studentdesk.adapter.UetAdapter;
import com.project.zhimer.studentdesk.model.Nilai;
import com.project.zhimer.studentdesk.model.UET;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class UaiEnglishTest extends Fragment {

    View view;
    SessionManager sessionManager;
    UET uet;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private RecyclerView.Adapter adapter;
    private ArrayList<UET> uetList;



    public UaiEnglishTest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_uai_english_test, container, false);

        uetList = new ArrayList<>();
        adapter = new UetAdapter(uetList, getActivity());

        recyclerView = view.findViewById(R.id.recyclerViewUET);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        sessionManager = new SessionManager(getContext());

        DataUaiEnglishTest();
        return view;
    }

    private void DataUaiEnglishTest() {
        String url = sessionManager.getUrl() + "/akademik/UET/format/json";
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
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray jsonArray = object.getJSONArray("data");

                    Log.d("dataUet", object.length() + "");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objek = jsonArray.getJSONObject(i);
                        uet = new UET();

                        String tanggal = objek.getString("TglTest");

                        SimpleDateFormat plainDate = new SimpleDateFormat("yyyy-M-d");
                        Date date = null;
                        try {
                            date = plainDate.parse(tanggal);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");

                        String waktu = dateFormat.format(date);
                        String score = objek.getString("nilai");

                        uet.setTanggal(waktu);
                        uet.setScore(score);

                        uetList.add(uet);
                        adapter.notifyDataSetChanged();
                    }
                    //TODO Set Text gabisa di masukin value int, jadi harus di convert ke String dulu
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
