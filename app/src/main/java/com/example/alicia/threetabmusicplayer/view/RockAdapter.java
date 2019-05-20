package com.example.alicia.threetabmusicplayer.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.alicia.threetabmusicplayer.R;
import com.example.alicia.threetabmusicplayer.model.RockPojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RockAdapter extends RecyclerView.Adapter<AllViewHolder> {

    List<RockPojo> rockDataSet;

    public void setRockDataSet(List<RockPojo> popPojoList) {
        this.rockDataSet = popPojoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AllViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllViewHolder allViewHolder, int i) {
        allViewHolder.artistName.setText(rockDataSet.get(i).getArtistName());
        allViewHolder.collectionName.setText(rockDataSet.get(i).getCollectionName());
        allViewHolder.trackPrice.setText(rockDataSet.get(i).getTrackPrice().toString());
        Picasso.get().load(rockDataSet.get(i).getArtworkUrl60()).into(allViewHolder.artwork);
    }

    @Override
    public int getItemCount() {
        return rockDataSet != null ? rockDataSet.size() : 0;
    }
}
