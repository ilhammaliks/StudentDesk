package com.project.zhimer.studentdesk.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.model.Surat;

import java.util.List;

public class SuratHistoryAdapter extends RecyclerView.Adapter<SuratHistoryAdapter.ViewHolder> {


    private List<Surat> suratList;

    public SuratHistoryAdapter(List<Surat> suratList) {
        this.suratList = suratList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_surat, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Surat surat = suratList.get(position);

        holder.tanggalBuat.setText(surat.getTanggalBuat());
        holder.jenis.setText(surat.getJenis());
        holder.tanggalJadi.setText(surat.getTanggalJadi());
    }

    @Override
    public int getItemCount() {
        return suratList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tanggalBuat, jenis, tanggalJadi;

        public ViewHolder(View itemView) {
            super(itemView);

            tanggalBuat = itemView.findViewById(R.id.surat_tanggal_buat);
            jenis = itemView.findViewById(R.id.surat_jenis);
            tanggalJadi = itemView.findViewById(R.id.surat_jadi);
        }
    }
}
