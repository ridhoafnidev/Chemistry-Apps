package com.example.hajidanumroh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.hajidanumroh.Model.Doa;
import com.example.hajidanumroh.Model.Larangan;
import com.example.hajidanumroh.R;
import com.example.hajidanumroh.Util.Api.ServerConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoaAdapter extends RecyclerView.Adapter<DoaAdapter.DoaHolder>{

    List<Doa> semuaDoaItemList;
    Context mContext;

    public DoaAdapter(Context context, List<Doa> semuaDoaItemList) {
        this.mContext = context;
        this.semuaDoaItemList = semuaDoaItemList;
    }

    @Override
    public DoaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_doa, parent, false);
        return new DoaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DoaHolder holder, int position){
        final Doa semuaDoaItem = semuaDoaItemList.get(position);
        holder.tvJudul.setText(semuaDoaItem.getJudulDoa());
        holder.tvDesc.setText(semuaDoaItem.getDeskripsiDoa());
    }

    @Override
    public int getItemCount() {
        return semuaDoaItemList.size();
    }

    public class DoaHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_des)
        TextView tvDesc;
        @BindView(R.id.tv_judul)
        TextView tvJudul;

        public DoaHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
