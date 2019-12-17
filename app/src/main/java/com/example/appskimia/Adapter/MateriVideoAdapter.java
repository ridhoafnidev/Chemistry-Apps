package com.example.appskimia.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appskimia.Model.Doa;
import com.example.appskimia.Model.Materi;
import com.example.appskimia.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MateriVideoAdapter extends RecyclerView.Adapter<MateriVideoAdapter.MateriVideoHolder>{

    List<Materi> semuaMateriItemList;
    Context mContext;

    public MateriVideoAdapter(Context context, List<Materi> semuaMateriItemList) {
        this.mContext = context;
        this.semuaMateriItemList = semuaMateriItemList;
    }

    @Override
    public MateriVideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_doa_video, parent, false);
        return new MateriVideoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MateriVideoHolder holder, int position){
        final Materi semuaMateriItem = semuaMateriItemList.get(position);
        holder.tvJudul.setText(semuaMateriItem.getJudulMateri());
        holder.tvDesc.setText(semuaMateriItem.getDeskripsiMateri());
        // setting
        holder.tvWebView.setWebViewClient(new WebViewClient());
        holder.tvWebView.setWebChromeClient(new WebChromeClient());
        holder.tvWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        holder.tvWebView.getSettings().setJavaScriptEnabled(true);
        holder.tvWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        holder.tvWebView.getSettings().setDefaultFontSize(18);

        String xml =  semuaMateriItem.getLinkYoutube();
        System.out.println("cacing yt: "+xml);
        String[] explode = xml.split("=");
        System.out.println("cacing: "+explode[1]);

        String kodeHTML = "<head></head><body>"+
                "<iframe width=\"346\" height=\"150\" src=\"https://www.youtube.com/embed/"+explode[1]+
                "\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>"+
                "</body>";
        holder.tvWebView.loadData(kodeHTML,"text/html; charset=utf-8",null);
    }

    @Override
    public int getItemCount() {
        return semuaMateriItemList.size();
    }

    public class MateriVideoHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_des)
        TextView tvDesc;
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.wv_yt)
        WebView tvWebView;

        public MateriVideoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
