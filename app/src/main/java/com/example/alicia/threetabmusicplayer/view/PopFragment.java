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
import com.example.alicia.threetabmusicplayer.model.PopPojo;
import com.example.alicia.threetabmusicplayer.presenter.Presenter;

import java.util.List;

import okhttp3.Cache;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopFragment extends Fragment implements PopViewContract{

    private Presenter presenter;
    private PopAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public PopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View popRoot = inflater.inflate(R.layout.fragment_pop, container, false);
        bindPresenter();

        adapter = new PopAdapter();
        adapter.setHasStableIds(true);
        recyclerView = popRoot.findViewById(R.id.rv_pop);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = popRoot.findViewById(R.id.srl_pop);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                populatePop();
            }
        });
        populatePop();

        return popRoot;
    }

    public static PopFragment newInstance() {
        return new PopFragment();
    }

    @Override
    public void getPopList(List<PopPojo> popPojoList) {
        adapter.setPopDataSet(popPojoList);
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
    public void populatePop() {
        if (hasNetwork()) {
            long cacheSize = (5 * 1024 * 1024);
            Cache myCache = new Cache(getActivity().getCacheDir(), cacheSize);
            presenter.initPopNetworkConnection(myCache);
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
