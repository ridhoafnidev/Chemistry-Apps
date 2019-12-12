package com.example.hajidanumroh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hajidanumroh.Model.Larangan;
import com.example.hajidanumroh.Model.Materi;
import com.example.hajidanumroh.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LaranganAdapter extends RecyclerView.Adapter<LaranganAdapter.LaranganHolder>{

    List<Larangan> semualaranganItemList;
    Context mContext;

    public LaranganAdapter(Context context, List<Larangan> semualaranganItemList) {
        this.mContext = context;
        this.semualaranganItemList = semualaranganItemList;
    }

    @Override
    public LaranganHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_larangan, parent, false);
        return new LaranganHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LaranganHolder holder, int position){
        final Larangan semuamateriitem = semualaranganItemList.get(position);
        holder.tvJudul.setText(semuamateriitem.getJudulLarangan());
        holder.tvDesc.setText(semuamateriitem.getDeskripsiLarangan());
    }

    @Override
    public int getItemCount() {
        return semualaranganItemList.size();
    }

    public class LaranganHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_des)
        TextView tvDesc;
        @BindView(R.id.tv_judul)
        TextView tvJudul;

        public LaranganHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
