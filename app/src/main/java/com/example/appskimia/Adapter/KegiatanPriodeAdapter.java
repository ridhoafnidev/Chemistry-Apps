package com.example.appskimia.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appskimia.Model.Kegiatan;
import com.example.appskimia.Model.KegiatanPriode;
import com.example.appskimia.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KegiatanPriodeAdapter extends RecyclerView.Adapter<KegiatanPriodeAdapter.KegiatanPriodeHolder>{

    List<KegiatanPriode> semuaKegiatanItemList;
    Context mContext;

    public KegiatanPriodeAdapter(Context context, List<KegiatanPriode> semuaKegiatanItemList) {
        this.mContext = context;
        this.semuaKegiatanItemList = semuaKegiatanItemList;
    }

    @Override
    public KegiatanPriodeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_kegiatan, parent, false);
        return new KegiatanPriodeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(KegiatanPriodeHolder holder, int position){
        final KegiatanPriode semuaKegiatanItem = semuaKegiatanItemList.get(position);
        holder.tvJudul.setText(semuaKegiatanItem.getJudulKegiatan());
        holder.tvDesc.setText(semuaKegiatanItem.getDeskripsiKegiatan());
    }

    @Override
    public int getItemCount() {
        return semuaKegiatanItemList.size();
    }

    public class KegiatanPriodeHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_des)
        TextView tvDesc;
        @BindView(R.id.tv_judul)
        TextView tvJudul;

        public KegiatanPriodeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
