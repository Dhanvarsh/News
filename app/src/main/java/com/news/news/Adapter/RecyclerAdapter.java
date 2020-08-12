package com.news.news.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.news.news.GroupModel.Data;
import com.news.news.R;
import com.thefinestartist.finestwebview.FinestWebView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewerHolder> {
    ArrayList<Data> data;
    Context context;
    public RecyclerAdapter(Context context, ArrayList<Data> data) {
        this.data=data;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_adapter,
                parent,false);
        return new MyViewerHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewerHolder holder, int position) {
        final Data data1=data.get(position);
        holder.tv_title.setText(data1.getTitle());
        holder.tv_desc.setText(data1.getDescription());
        holder.tv_url.setText(data1.getUrl());
        holder.tv_time.setText(data1.getPublishedAt());
        Glide.with(context).load(data1.getUrlToImage()).into(holder.img);
        holder.tv_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new FinestWebView.Builder(context).show(data1.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_desc)
        TextView tv_desc;
        @BindView(R.id.tv_Url)
        TextView tv_url;
        @BindView(R.id.img)
        ImageView img;


        public MyViewerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
