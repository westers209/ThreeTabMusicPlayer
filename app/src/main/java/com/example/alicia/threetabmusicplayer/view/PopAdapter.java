package com.example.alicia.threetabmusicplayer.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.alicia.threetabmusicplayer.R;
import com.example.alicia.threetabmusicplayer.model.PopPojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopAdapter extends RecyclerView.Adapter<AllViewHolder> {

    List<PopPojo> popDataSet;

    public void setPopDataSet(List<PopPojo> popPojoList) {
        this.popDataSet = popPojoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AllViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllViewHolder allViewHolder, int i) {
        allViewHolder.artistName.setText(popDataSet.get(i).getArtistName());
        allViewHolder.collectionName.setText(popDataSet.get(i).getCollectionName());
        allViewHolder.trackPrice.setText(popDataSet.get(i).getTrackPrice().toString());
        Picasso.get().load(popDataSet.get(i).getArtworkUrl60()).into(allViewHolder.artwork);
    }

    @Override
    public int getItemCount() {
        return popDataSet != null ? popDataSet.size() : 0;
    }
}
