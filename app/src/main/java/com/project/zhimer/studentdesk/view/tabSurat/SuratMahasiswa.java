package com.project.zhimer.studentdesk.view.tabSurat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SuratMahasiswa extends Fragment {

    View view;

    private Spinner spinner;
    private EditText etKeterangan;
    private Button bSave;

    ProgressView progressView;

    SessionManager sessionManager;

    String keterangan;

    public SuratMahasiswa() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_surat_mahasiswa, container, false);

        spinner = view.findViewById(R.id.bahasa);
        etKeterangan = view.findViewById(R.id.etKeterangan);
        bSave = view.findViewById(R.id.bSave);

        progressView = view.findViewById(R.id.circular);

        //spinner
        spinner = view.findViewById(R.id.bahasa);

        sessionManager = new SessionManager(getContext());

        List<String> Bahasa = new ArrayList<String>();
        Bahasa.add("Bahasa Indonesia");
        Bahasa.add("English");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Bahasa);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner.setAdapter(adapter);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressView.setVisibility(View.VISIBLE);
                progressView.start();

                keterangan = etKeterangan.getText().toString();

                SendSuratMahasiswa();
            }
        });


        return view;
    }

    private void SendSuratMahasiswa() {

        String url = sessionManager.getUrl() + "";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.setBasicAuth(sessionManager.getAuthUsername(), sessionManager.getAuthPassword());
        params.put("uname", sessionManager.getNim());
        params.put("pwd", sessionManager.getPassword());

        //TODO nama parameter nya sesuaikan dengan yang ada pada json asli
        params.put("keterangan", keterangan);

        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Toast.makeText(getContext(),"Surat berhasil disimpan", Toast.LENGTH_SHORT).show();

                progressView.setVisibility(View.GONE);
                progressView.stop();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Toast.makeText(getContext(),"Terjadi kesalah pada sistem\nsilahkan coba beberapa saat lagi", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
                progressView.stop();
            }
        });
    }
}
