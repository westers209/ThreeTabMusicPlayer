package com.example.alicia.threetabmusicplayer.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alicia.threetabmusicplayer.R;

public class AllViewHolder extends RecyclerView.ViewHolder {
    public ImageView artwork;
    public TextView artistName;
    public TextView collectionName;
    public TextView trackPrice;

    public AllViewHolder(@NonNull View itemView) {
        super(itemView);

        artwork = itemView.findViewById(R.id.iv_artwork);
        artistName = itemView.findViewById(R.id.tv_artist_name);
        collectionName = itemView.findViewById(R.id.tv_collection_name);
        trackPrice = itemView.findViewById(R.id.tv_track_price);
        itemView.setTag(this);
        itemView.setOnClickListener(ClassicAdapter.mOnItemClickListener);
    }
}
