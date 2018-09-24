package com.project.zhimer.studentdesk.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.model.Kuliah;

import java.util.List;

public class JadwalKuliahAdapter extends RecyclerView.Adapter<JadwalKuliahAdapter.ViewHolder> {

    private List<Kuliah> jadwalKuliahList;

    public JadwalKuliahAdapter(List<Kuliah> jadwalKuliahList) {
        this.jadwalKuliahList = jadwalKuliahList;
    }

    @NonNull
    @Override
    public JadwalKuliahAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_jadwalkuliah, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalKuliahAdapter.ViewHolder holder, int position) {

        Kuliah jadwalKuliah = jadwalKuliahList.get(position);

        holder.kode.setText(jadwalKuliah.getKelas());
        holder.mataKuliah.setText(jadwalKuliah.getMataKuliah());
        holder.sks.setText(jadwalKuliah.getSks());
        holder.waktu.setText(jadwalKuliah.getWaktu());
        holder.dosen.setText(jadwalKuliah.getDosen());
        holder.ruang.setText(jadwalKuliah.getRuang());
    }

    @Override
    public int getItemCount() {
        return jadwalKuliahList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView kode, mataKuliah, sks, waktu, dosen, ruang;

        ViewHolder(View itemView) {
            super(itemView);

            kode = itemView.findViewById(R.id.tvKelas);
            mataKuliah = itemView.findViewById(R.id.tvMatakuliah);
            sks = itemView.findViewById(R.id.tvSks);
            waktu = itemView.findViewById(R.id.tvWaktu);
            dosen = itemView.findViewById(R.id.tvDosen);
            ruang = itemView.findViewById(R.id.tvRuang);
        }
    }
}
