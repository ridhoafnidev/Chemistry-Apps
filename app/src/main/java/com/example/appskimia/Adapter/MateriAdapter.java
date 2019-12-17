package com.example.appskimia.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appskimia.Model.Materi;
import com.example.appskimia.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MateriAdapter extends RecyclerView.Adapter<MateriAdapter.MateriHolder>{

    List<Materi> semuamateriItemList;
    Context mContext;

    public MateriAdapter(Context context, List<Materi> semuamateriItemList) {
        this.mContext = context;
        this.semuamateriItemList = semuamateriItemList;
    }

    @Override
    public MateriHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_materi, parent, false);
        return new MateriHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MateriHolder holder, int position){
        final Materi semuamateriitem = semuamateriItemList.get(position);
        holder.tvJudul.setText(semuamateriitem.getJudulMateri());
        holder.tvJenis.setText(semuamateriitem.getJenisMateri());
        holder.tvDesc.setText(semuamateriitem.getDeskripsiMateri());
    }

    @Override
    public int getItemCount() {
        return semuamateriItemList.size();
    }

    public class MateriHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_des)
        TextView tvDesc;
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tv_jenis)
        TextView tvJenis;

        public MateriHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
