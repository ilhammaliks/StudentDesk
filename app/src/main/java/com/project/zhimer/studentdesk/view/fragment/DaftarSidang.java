package com.project.zhimer.studentdesk.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.rey.material.widget.ProgressView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class DaftarSidang extends Fragment {

    View view;
    SessionManager sessionManager;

    TextView mahasiswa_nama, mahasiswa_nim, mahasiswa_prodi;

    EditText mahasiswa_telp, mahasiswa_phone, mahasiswa_alamat, mahasiswa_kota, mahasiswa_kode_pos,
            mahasiswa_dosen1, mahasiswa_dosen2, mahasiswa_judul;

    Button bSave;

    String sTelp, sPhone, sAlamat, sKota, sKode, sDosen1, sJudul;

    ProgressView progressView;


    public DaftarSidang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_daftar_sidang, container, false);

        progressView = view.findViewById(R.id.circular);

        mahasiswa_nama = view.findViewById(R.id.mahasiswa_nama);
        mahasiswa_nim = view.findViewById(R.id.mahasiswa_nim);
        mahasiswa_prodi = view.findViewById(R.id.mahasiswa_prodi);

        mahasiswa_telp = view.findViewById(R.id.etTelepon);
        mahasiswa_phone = view.findViewById(R.id.etPhone);
        mahasiswa_alamat = view.findViewById(R.id.etAlamat);
        mahasiswa_kota = view.findViewById(R.id.etKota);
        mahasiswa_kode_pos = view.findViewById(R.id.etKodePos);
        mahasiswa_dosen1 = view.findViewById(R.id.etDosen1);
        mahasiswa_dosen2 = view.findViewById(R.id.etDosen2);
        mahasiswa_judul = view.findViewById(R.id.etSkripsi);

        bSave = view.findViewById(R.id.bSave);

        sessionManager = new SessionManager(getContext());

        progressView.setVisibility(View.VISIBLE);
        progressView.start();

        GetDataMahasiswa();


        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checkTelp = false, checkPhone = false, checkAlamat = false,
                        checkKota = false, checkKode = false, checkDosen1 = false, checkJudul = false;

                sTelp = mahasiswa_telp.getText().toString();
                sPhone = mahasiswa_phone.getText().toString();
                sAlamat = mahasiswa_alamat.getText().toString();
                sKota = mahasiswa_kota.getText().toString();
                sKode = mahasiswa_kode_pos.getText().toString();
                sDosen1 = mahasiswa_dosen1.getText().toString();
                sJudul = mahasiswa_judul.getText().toString();

                //set Required Field
                if (sTelp.isEmpty()) {
                    mahasiswa_telp.setError("No.Telp mohon diisi");
                } else {
                    checkTelp = true;
                }

                if (sPhone.isEmpty()) {
                    mahasiswa_telp.setError("No.Handphone mohon diisi");
                } else {
                    checkPhone = true;
                }

                if (sAlamat.isEmpty()) {
                    mahasiswa_alamat.setError("Alamat mohon diisi");
                } else {
                    checkAlamat = true;
                }

                if (sKota.isEmpty()) {
                    mahasiswa_kota.setError("Kota mohon diisi");
                } else {
                    checkKota = true;
                }

                if (sKode.isEmpty()) {
                    mahasiswa_kode_pos.setError("Kode Pos mohon diisi");
                } else {
                    checkKode = true;
                }

                if (sDosen1.isEmpty()) {
                    mahasiswa_dosen1.setError("Pembimbing 1 mohon diisi");
                } else {
                    checkDosen1 = true;
                }

                if (sJudul.isEmpty()) {
                    mahasiswa_judul.setError("Judul Skripsi mohon diisi");
                } else {
                    checkJudul = true;
                }

                if (checkTelp && checkPhone && checkAlamat && checkKota && checkKode && checkDosen1 && checkJudul) {

                    //TODO Push data Daftar Sidang
                }
            }
        });


        return view;
    }

    private void GetDataMahasiswa() {
        String url = sessionManager.getUrl() + "/biodata/LihatBiodata/format/json";
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

                        String nama = object.getString("mhs_nm");
                        String nim = object.getString("mhs_nim");
                        String prodi = object.getString("NamaProgdi");
                        String telepon = object.getString("mhs_telepon");
                        String phone = object.getString("mhs_hp");
                        String alamat = object.getString("mhs_alm");
                        String kota = object.getString("mhs_kota");
                        String kodePos = object.getString("kodepos");

                        mahasiswa_nama.setText(nama);
                        mahasiswa_nim.setText(nim);
                        mahasiswa_prodi.setText(prodi);

                        mahasiswa_telp.setText(telepon);
                        mahasiswa_phone.setText(phone);
                        mahasiswa_alamat.setText(alamat);
                        mahasiswa_kota.setText(kota);
                        mahasiswa_kode_pos.setText(kodePos);

                        progressView.stop();
                        progressView.setVisibility(View.GONE);
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
