package com.project.zhimer.studentdesk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.model.Kuliah;

import java.util.List;

public class JadwalKehadiranAdapter extends RecyclerView.Adapter<JadwalKehadiranAdapter.ViewHolder> {

    private List<Kuliah> kehadiranKuliah;
    private Context context;
    private LayoutInflater inflater = null;

    public JadwalKehadiranAdapter(List<Kuliah> kehadiranKuliah, Context context) {
        this.kehadiranKuliah = kehadiranKuliah;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_jadwal_kehadiran, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Kuliah kuliah = kehadiranKuliah.get(position);

        holder.namaMk.setText(kuliah.getMataKuliah());
        holder.sks.setText(String.valueOf(kuliah.getSks()));
        holder.hadir.setText(String.valueOf(kuliah.getHadir()));
        holder.sakit.setText(String.valueOf(kuliah.getSakit()));
        holder.izin.setText(String.valueOf(kuliah.getIzin()));
        holder.alpa.setText(String.valueOf(kuliah.getAlpa()));
        holder.persentase.setText(String.valueOf(kuliah.getPersentase()));

    }

    @Override
    public int getItemCount() {
        return kehadiranKuliah.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView namaMk, sks, hadir, sakit, izin, alpa, persentase;

        public ViewHolder(View itemView) {
            super(itemView);

            namaMk = itemView.findViewById(R.id.tvMataKuliah);
            sks = itemView.findViewById(R.id.tvSks);
            hadir = itemView.findViewById(R.id.tvHadir);
            sakit = itemView.findViewById(R.id.tvSakit);
            izin = itemView.findViewById(R.id.tvIzin);
            alpa = itemView.findViewById(R.id.tvAlpa);
            persentase = itemView.findViewById(R.id.tvPersentase);
        }
    }
}
