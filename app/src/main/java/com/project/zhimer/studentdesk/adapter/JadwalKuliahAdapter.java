package com.project.zhimer.studentdesk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
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
    private Context context;
    private LayoutInflater inflater = null;

    public JadwalKuliahAdapter(List<Kuliah> jadwalKuliahList, Context context) {
        this.jadwalKuliahList = jadwalKuliahList;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public JadwalKuliahAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_jadwalkuliah, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalKuliahAdapter.ViewHolder holder, int position) {

        final Kuliah jadwalKuliah = jadwalKuliahList.get(position);

        holder.kode.setText(jadwalKuliah.getKelas());
        holder.mataKuliah.setText(jadwalKuliah.getMataKuliah());
        holder.sks.setText(String.valueOf(jadwalKuliah.getSks()));
        holder.waktu.setText(jadwalKuliah.getHari());
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
