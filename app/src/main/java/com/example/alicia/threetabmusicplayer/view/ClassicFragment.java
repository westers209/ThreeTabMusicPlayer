package com.example.alicia.threetabmusicplayer.view;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.alicia.threetabmusicplayer.R;
import com.example.alicia.threetabmusicplayer.model.ClassicPojo;
import com.example.alicia.threetabmusicplayer.presenter.Presenter;

import java.util.List;

import okhttp3.Cache;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassicFragment extends Fragment implements ClassicViewContract {

    private Presenter presenter;
    private ClassicAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public ClassicFragment() {
        // Required empty public constructor
    }

//    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
//            int position = viewHolder.getAdapterPosition();
//            // viewHolder.getItemId();
//            // viewHolder.getItemViewType();
//            // viewHolder.itemView;
 //onclicklistener to be continued           TestItem thisItem = mTestItemList.get(position);
//        }
//    };
    //https://hackernoon.com/android-recyclerview-onitemclicklistener-getadapterposition-a-better-way-3c789baab4db

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View classicRoot = inflater.inflate(R.layout.fragment_classic, container, false);
        bindPresenter();

        adapter = new ClassicAdapter();
        adapter.setHasStableIds(true);
        recyclerView = classicRoot.findViewById(R.id.rv_classic);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
 //onclicklistener to be continued       adapter.setOnItemClickListener(onItemClickListener);
        populateClassic();

        swipeRefreshLayout = classicRoot.findViewById(R.id.srl_classic);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                populateClassic();
            }
        });
        return classicRoot;
    }

    public static ClassicFragment newInstance() {
        return new ClassicFragment();
    }

    @Override
    public void getClassicList(List<ClassicPojo> classicPojoList) {
        adapter.setClassicDataSet(classicPojoList);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindPresenter() {
        presenter = new Presenter();
        presenter.bind(this);
    }

    @Override
    public void populateClassic() {
        if (hasNetwork()) {
            long cacheSize = (5 * 1024 * 1024);
            Cache myCache = new Cache(getActivity().getCacheDir(), cacheSize);
            presenter.initClassicNetworkConnection(myCache);
        } else {
            onError("No network found");
        }
    }

    boolean hasNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}
