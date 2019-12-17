package com.example.appskimia.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appskimia.Model.Kegiatan;
import com.example.appskimia.Model.Larangan;
import com.example.appskimia.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KegiatanAdapter extends RecyclerView.Adapter<KegiatanAdapter.KegiatanHolder>{

    List<Kegiatan> semuaKegiatanItemList;
    Context mContext;

    public KegiatanAdapter(Context context, List<Kegiatan> semuaKegiatanItemList) {
        this.mContext = context;
        this.semuaKegiatanItemList = semuaKegiatanItemList;
    }

    @Override
    public KegiatanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_kegiatan, parent, false);
        return new KegiatanHolder(itemView);
    }

    @Override
    public void onBindViewHolder(KegiatanHolder holder, int position){
        final Kegiatan semuaKegiatanItem = semuaKegiatanItemList.get(position);
        holder.tvJudul.setText(semuaKegiatanItem.getJudulKegiatan());
        holder.tvDesc.setText(semuaKegiatanItem.getDeskripsiKegiatan());
    }

    @Override
    public int getItemCount() {
        return semuaKegiatanItemList.size();
    }

    public class KegiatanHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_des)
        TextView tvDesc;
        @BindView(R.id.tv_judul)
        TextView tvJudul;

        public KegiatanHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
