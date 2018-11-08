package com.project.zhimer.studentdesk.view.fragment;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class Biodata extends Fragment {

    View view;
    SessionManager sessionManager;

    ImageView foto;

    TextView mahasiswa_nama, mahasiswa_nim, mahasiswa_prodi, mahasiswa_dosen, mahasiswa_jalur,
            mahasiswa_status, mahasiswa_alamat, mahasiswa_kota, mahasiswa_telp, mahasiswa_phone,
            mahasiswa_email;

    ProgressView progressView;


    public Biodata() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_biodata, container, false);

        progressView = view.findViewById(R.id.circular);

        foto = view.findViewById(R.id.mahasiswa_foto);

        mahasiswa_nama = view.findViewById(R.id.mahasiswa_nama);
        mahasiswa_nim = view.findViewById(R.id.mahasiswa_nim);
        mahasiswa_prodi = view.findViewById(R.id.mahasiswa_prodi);
        mahasiswa_dosen = view.findViewById(R.id.mahasiswa_dosen);
        mahasiswa_jalur = view.findViewById(R.id.mahasiswa_jalur);
        mahasiswa_status = view.findViewById(R.id.mahasiswa_status);
        mahasiswa_alamat = view.findViewById(R.id.mahasiswa_alamat);
        mahasiswa_kota = view.findViewById(R.id.mahasiswa_kota);
        mahasiswa_telp = view.findViewById(R.id.mahasiswa_telp);
        mahasiswa_phone = view.findViewById(R.id.mahasiswa_phone);
        mahasiswa_email = view.findViewById(R.id.mahasiswa_email);

        sessionManager = new SessionManager(getContext());

        progressView.setVisibility(View.VISIBLE);
        progressView.start();
        DataBio();

        //hardcode
        Picasso.with(getContext()).load(R.drawable.photo).into(foto);

        CollapsingToolbarLayout collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Biodata");

        if (getContext() != null) {
            collapsingToolbarLayout.setCollapsedTitleTextColor(
                    ContextCompat.getColor(getContext(), R.color.white));
            collapsingToolbarLayout.setExpandedTitleColor(
                    ContextCompat.getColor(getContext(), R.color.colorPrimary));
        }
        return view;
    }

    private void DataBio() {
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
//                        mahasiswa = new Mahasiswa();

                        String nim = object.getString("mhs_nim");
                        String nama = object.getString("mhs_nm");
                        String tahun = object.getString("mhs_ank");
                        String alamat = object.getString("mhs_alm");
                        String kota = object.getString("mhs_kota");
                        String kodePos = object.getString("kodepos");
                        String telp = object.getString("mhs_telepon");
                        String phone = object.getString("mhs_hp");
                        String email = object.getString("mhs_email");
                        String prodi = object.getString("NamaProgdi");
                        String pembimbing = object.getString("DosenPembimbing");
                        String jalurMasuk = object.getString("NamaJalurMasuk");
                        String statusAkademik = object.getString("NamaStatusAkademik");

                        mahasiswa_nama.setText(nama);
                        mahasiswa_nim.setText(nim);
                        mahasiswa_prodi.setText(prodi);
                        mahasiswa_dosen.setText(pembimbing);
                        mahasiswa_jalur.setText(jalurMasuk);
                        mahasiswa_status.setText(statusAkademik);
                        mahasiswa_alamat.setText(alamat);
                        mahasiswa_kota.setText(kota + ", " + kodePos);
                        mahasiswa_telp.setText(telp);
                        mahasiswa_phone.setText(phone);
                        mahasiswa_email.setText(email);

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
