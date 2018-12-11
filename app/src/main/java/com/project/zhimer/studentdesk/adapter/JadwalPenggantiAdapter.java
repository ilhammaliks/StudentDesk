package com.project.zhimer.studentdesk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.model.Kuliah;

import java.util.List;

public class JadwalPenggantiAdapter extends RecyclerView.Adapter<JadwalPenggantiAdapter.ViewHolder> {

    private List<Kuliah> listPengganti;
    private Context context;
    private LayoutInflater inflater = null;

    public JadwalPenggantiAdapter(List<Kuliah> listPengganti, Context context) {
        this.listPengganti = listPengganti;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public JadwalPenggantiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_jadwal_pengganti, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalPenggantiAdapter.ViewHolder holder, int position) {

        final Kuliah kuliah = listPengganti.get(position);

        holder.namaMk.setText(kuliah.getMataKuliah());
        holder.dosen.setText(kuliah.getDosen());
        holder.berhalangan.setText(kuliah.getTanggalBerhalangan());
        holder.pengganti.setText(kuliah.getTanggalPengganti());
        if (kuliah.getRuang().equals("null")) {
            holder.ruang.setText("[Belum]");
            holder.ruang.setTextColor(Color.RED);
        }else {
            holder.ruang.setText(kuliah.getRuang());
        }

    }

    @Override
    public int getItemCount() {
        return listPengganti.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView namaMk, dosen, berhalangan, pengganti, ruang;

        public ViewHolder(View itemView) {
            super(itemView);

            namaMk = itemView.findViewById(R.id.tvMataKuliah);
            dosen = itemView.findViewById(R.id.tvDosen);
            berhalangan = itemView.findViewById(R.id.tvBerhalangan);
            pengganti = itemView.findViewById(R.id.tvPengganti);
            ruang = itemView.findViewById(R.id.tvRuang);
        }
    }
}
