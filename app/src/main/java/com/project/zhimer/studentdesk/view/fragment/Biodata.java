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


    public Biodata() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_biodata, container, false);

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

        //hardcode
        Picasso.with(getContext()).load(R.drawable.photo).into(foto);

        mahasiswa_nama.setText("Ilham Malik Muhammad");
        mahasiswa_nim.setText("0102513010");
        mahasiswa_prodi.setText("Teknik Informatika");
        mahasiswa_dosen.setText("Ir. Endang Ripmiatin, M.T");
        mahasiswa_jalur.setText("Regular");
        mahasiswa_status.setText("Aktif");
        mahasiswa_alamat.setText("Jl. Kepodang VIII K1/24, Rt/Rw 001/006, Rengas, Ciputat Timur, Tangerang Selatan");
        mahasiswa_kota.setText("Tangerang Selatan, 15412");
        mahasiswa_telp.setText("083895671999");
        mahasiswa_phone.setText("083895671999");
        mahasiswa_email.setText("ilhammalikmuhammad@gmail.com");

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Biodata");

        if (getContext() != null) {
            collapsingToolbarLayout.setCollapsedTitleTextColor(
                    ContextCompat.getColor(getContext(), R.color.white));
            collapsingToolbarLayout.setExpandedTitleColor(
                    ContextCompat.getColor(getContext(), R.color.colorPrimary));
        }
        return view;
    }



}
