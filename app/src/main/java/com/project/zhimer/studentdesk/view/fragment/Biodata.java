package com.project.zhimer.studentdesk.view.fragment;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;
import com.squareup.picasso.Picasso;


public class Biodata extends Fragment {

    View view;

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
        view = inflater.inflate(R.layout.fragment_biodata_new, container, false);

        foto = view.findViewById(R.id.mahasiswa_foto);

        mahasiswa_nama = (TextView) view.findViewById(R.id.mahasiswa_nama);
        mahasiswa_nim = (TextView) view.findViewById(R.id.mahasiswa_nim);
        mahasiswa_prodi = (TextView) view.findViewById(R.id.mahasiswa_prodi);
        mahasiswa_dosen = (TextView) view.findViewById(R.id.mahasiswa_dosen);
        mahasiswa_jalur = (TextView) view.findViewById(R.id.mahasiswa_jalur);
        mahasiswa_status = (TextView) view.findViewById(R.id.mahasiswa_status);
        mahasiswa_alamat = (TextView) view.findViewById(R.id.mahasiswa_alamat);
        mahasiswa_kota = (TextView) view.findViewById(R.id.mahasiswa_kota);
        mahasiswa_telp = (TextView) view.findViewById(R.id.mahasiswa_telp);
        mahasiswa_phone = (TextView) view.findViewById(R.id.mahasiswa_phone);
        mahasiswa_email = (TextView) view.findViewById(R.id.mahasiswa_email);

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
