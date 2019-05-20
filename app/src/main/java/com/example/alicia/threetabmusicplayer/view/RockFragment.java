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
import com.example.alicia.threetabmusicplayer.model.RockPojo;
import com.example.alicia.threetabmusicplayer.presenter.Presenter;

import java.util.List;

import okhttp3.Cache;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class RockFragment extends Fragment implements RockViewContract{

    private Presenter presenter;
    private RockAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public RockFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rockRoot = inflater.inflate(R.layout.fragment_rock, container, false);
        bindPresenter();

        adapter = new RockAdapter();
        adapter.setHasStableIds(true);
        recyclerView = rockRoot.findViewById(R.id.rv_rock);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = rockRoot.findViewById(R.id.srl_rock);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                populateRock();
            }
        });
        populateRock();

        return rockRoot;
    }

    public static RockFragment newInstance() {
        return new RockFragment();
    }

    @Override
    public void getRockList(List<RockPojo> rockPojoList) {
        adapter.setRockDataSet(rockPojoList);
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
    public void populateRock() {
        if (hasNetwork()) {
            long cacheSize = (5 * 1024 * 1024);
            Cache myCache = new Cache(getActivity().getCacheDir(), cacheSize);
            presenter.initRockNetworkConnection(myCache);
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
