package com.project.zhimer.studentdesk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.model.Quran;

import java.util.List;

public class JadwalQuranAdapter extends RecyclerView.Adapter<JadwalQuranAdapter.ViewHolder> {

    List<Quran> listJadwalQuran;
    Context context;
    LayoutInflater inflater;

    public JadwalQuranAdapter(List<Quran> listJadwalQuran, Context context) {
        this.listJadwalQuran = listJadwalQuran;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_quran_test, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Quran jadwalTest = listJadwalQuran.get(position);

        holder.tanggalTest.setText(jadwalTest.getTanggalTest());
        holder.jumlahPeserta.setText(jadwalTest.getJumlahPeserta());

    }

    @Override
    public int getItemCount() {
        return listJadwalQuran.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tanggalTest, jumlahPeserta;

        public ViewHolder(View itemView) {
            super(itemView);

            tanggalTest = itemView.findViewById(R.id.tvTanggalTest);
            jumlahPeserta = itemView.findViewById(R.id.tvJumlahPeserta);

        }
    }
}
