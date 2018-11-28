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
import com.project.zhimer.studentdesk.model.Quran;

import java.util.List;

public class HasilQuranAdapter extends RecyclerView.Adapter<HasilQuranAdapter.ViewHolder> {

    List<Quran> quranList;
    Context context;
    LayoutInflater inflater;

    public HasilQuranAdapter(List<Quran> quranList, Context context) {
        this.quranList = quranList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public HasilQuranAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_quran_hasil, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HasilQuranAdapter.ViewHolder holder, int position) {

        final Quran hasilTestQuran = quranList.get(position);

        holder.tanggalTest.setText(hasilTestQuran.getTanggal());
        holder.totalScore.setText(hasilTestQuran.getScore());

        if (hasilTestQuran.getStatus().equals("LULUS")) {

            holder.status.setText(hasilTestQuran.getStatus());
            holder.status.setTextColor(Color.BLUE);
        } else {
            holder.status.setText(hasilTestQuran.getStatus());
            holder.status.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return quranList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tanggalTest, totalScore, status;

        public ViewHolder(View itemView) {
            super(itemView);

            tanggalTest = itemView.findViewById(R.id.tvTanggalTest);
            totalScore = itemView.findViewById(R.id.tvTotalScore);
            status = itemView.findViewById(R.id.tvStatus);
        }
    }
}
