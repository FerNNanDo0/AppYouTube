package com.app.youtube.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.app.youtube.R;
import com.app.youtube.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.myViewHolder> {

    List<Item> listVideos;
    Context context;

    public AdapterMain(Context context, List<Item> listVideos ){
        this.context = context;
        this.listVideos = listVideos;
    }

    @NonNull
    @Override
    public AdapterMain.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from( parent.getContext() )
                .inflate(R.layout.adpter_list_videos, parent,false);

        return new AdapterMain.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMain.myViewHolder holder, int position) {
        Item video = listVideos.get(position);
        holder.titulo.setText( video.snippet.title );
        String url = video.snippet.thumbnails.high.url;
        Picasso.get().load( url ).into(holder.capa);
    }

    @Override
    public int getItemCount() {
        return listVideos.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
        TextView titulo ;
        TextView descricao ;
        TextView data;
        ImageView capa;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
//            descricao = itemView.findViewById(R.id.descricao);
//            data = itemView.findViewById(R.id.data);
            capa = itemView.findViewById(R.id.imageCapa);
        }
    }
}
