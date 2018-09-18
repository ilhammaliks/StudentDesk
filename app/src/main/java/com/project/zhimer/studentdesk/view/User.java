package com.project.zhimer.studentdesk.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;

public class User extends AppCompatActivity {

    TextView sks, ipk, uet, tilawah, namaLengkap, nim, prodi, tahun;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header);

        sks = (TextView)findViewById(R.id.mahasiswa_sks);
        ipk = (TextView)findViewById(R.id.mahasiswa_ipk);
        uet = (TextView)findViewById(R.id.mahasiswa_uet);
        tilawah = (TextView)findViewById(R.id.mahasiswa_tilawah);
        namaLengkap = (TextView)findViewById(R.id.mahasiswa_nama);
        nim = (TextView)findViewById(R.id.mahasiswa_nim);
        prodi = (TextView)findViewById(R.id.mahasiswa_prodi);
        tahun = (TextView)findViewById(R.id.mahasiswa_tahun);

        sks.setText("139");
        ipk.setText("3.75");
        uet.setText("560");
        tilawah.setText("---");
        namaLengkap.setText("Ilham Malik Muhammad");
        nim.setText("0102513010");
        prodi.setText("Teknik Informatika");
        tahun.setText("2013");
    }
}
