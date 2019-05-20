package com.example.alicia.threetabmusicplayer.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alicia.threetabmusicplayer.R;
import com.example.alicia.threetabmusicplayer.model.ClassicPojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ClassicAdapter extends RecyclerView.Adapter<AllViewHolder> {

    List<ClassicPojo> classicDataSet;
    public static View.OnClickListener mOnItemClickListener;

    public void setClassicDataSet(List<ClassicPojo> classicPojoList) {
        this.classicDataSet = classicPojoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AllViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllViewHolder allViewHolder, int i) {
        allViewHolder.artistName.setText(classicDataSet.get(i).getArtistName());
        allViewHolder.collectionName.setText(classicDataSet.get(i).getCollectionName());
        allViewHolder.trackPrice.setText(classicDataSet.get(i).getTrackPrice().toString());
        Picasso.get().load(classicDataSet.get(i).getArtworkUrl60()).into(allViewHolder.artwork);

    }

    @Override
    public int getItemCount() {
        return classicDataSet != null ? classicDataSet.size() : 0;
    }

    public void clear(){
        classicDataSet.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<ClassicPojo> classicPojoList){
        classicDataSet.addAll(classicPojoList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
}
