package com.project.zhimer.studentdesk.view.tabSurat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SuratLulus extends Fragment {

    View view;

    TextView tanggalLulus;
    Button bsave;

    ProgressView progressView;
    SessionManager sessionManager;

    String tanggal;


    public SuratLulus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_surat_lulus, container, false);

        tanggalLulus = view.findViewById(R.id.tvTanggalLulus);
        bsave = view.findViewById(R.id.bSave);
        progressView = view.findViewById(R.id.circular);

        sessionManager = new SessionManager(getContext());

        tanggalLulus.setText("---");

//        GetTanggalLulus();

        bsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tanggalLulus.equals("---")) {
                    Toast.makeText(getContext(), "Anda belum dinyatakan lulus", Toast.LENGTH_SHORT).show();
                } else {
                    progressView.setVisibility(View.VISIBLE);
                    progressView.start();

                    SendDataTanggalLulus();
                }
            }
        });


        return view;
    }

    private void GetTanggalLulus() {
        String url = sessionManager.getUrl() + "";
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

                        //TODO nama parameter nya sesuaikan dengan yang ada pada json asli
                        String tanggal = object.getString("tanggalLulus");

                        if (tanggal == null) {
                            tanggalLulus.setText("---");
                        } else {
                            tanggalLulus.setText(tanggal);
                        }
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

    private void SendDataTanggalLulus() {
        String url = sessionManager.getUrl() + "";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.setBasicAuth(sessionManager.getAuthUsername(), sessionManager.getAuthPassword());
        params.put("uname", sessionManager.getNim());
        params.put("pwd", sessionManager.getPassword());

        //tanggal lulus nya
        params.put("tanggalLulus", tanggalLulus);
        //end

        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                progressView.setVisibility(View.GONE);
                progressView.stop();

                Toast.makeText(getContext(), "Surat berhasil disimpan", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                progressView.setVisibility(View.GONE);
                progressView.stop();
                Toast.makeText(getContext(),"Terjadi kesalah pada sistem\nsilahkan coba beberapa saat lagi", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
