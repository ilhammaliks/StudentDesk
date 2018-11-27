package com.project.zhimer.studentdesk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.model.UET;

import java.util.List;

public class UetAdapter extends RecyclerView.Adapter<UetAdapter.ViewHolder> {

    private Context context;
    private List<UET> uetList;
    private LayoutInflater inflater;

    public UetAdapter(List<UET> uetList, Context context) {
        this.uetList = uetList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public UetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_uet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UetAdapter.ViewHolder holder, int position) {

        final UET uet = uetList.get(position);


        holder.tanggal.setText(uet.getTanggal());
        holder.score.setText(uet.getScore());

    }

    @Override
    public int getItemCount() {
        return uetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tanggal, score;

        public ViewHolder(View itemview) {
            super(itemview);

            tanggal = itemview.findViewById(R.id.tvTanggalTest);
            score = itemview.findViewById(R.id.tvJumlahPeserta);
        }
    }
}