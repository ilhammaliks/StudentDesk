package com.project.zhimer.studentdesk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.model.Quran;
import com.project.zhimer.studentdesk.model.SyaratSidang;

import java.util.List;

public class DaftarSidangAdapter extends RecyclerView.Adapter<DaftarSidangAdapter.ViewHolder> {

    List<SyaratSidang> syaratSidang;
    Context context;
    LayoutInflater inflater;

    public DaftarSidangAdapter(List<SyaratSidang> syaratSidang, Context context){
        this.syaratSidang = syaratSidang;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public DaftarSidangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_syarat_sidang, parent, false);

        return new DaftarSidangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarSidangAdapter.ViewHolder holder, int position) {

        SyaratSidang objSyarat = syaratSidang.get(position);


        holder.tvSyarat.setText(objSyarat.getSyarat());
        if (objSyarat.getStatus().equalsIgnoreCase("Y")){
            holder.oke.setVisibility(View.VISIBLE);
            holder.notOke.setVisibility(View.GONE);
        } else {
            holder.oke.setVisibility(View.GONE);
            holder.notOke.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return syaratSidang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSyarat;
        ImageView oke, notOke;

        public ViewHolder(View itemView) {
            super(itemView);

            tvSyarat = itemView.findViewById(R.id.tvSyarat);
            oke = itemView.findViewById(R.id.oke);
            notOke = itemView.findViewById(R.id.notOke);
        }
    }
}
