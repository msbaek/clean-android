package com.github.msbaek.rxessentials;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UserListFragment extends Fragment {
    @InjectView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @InjectView(R.id.swipe)
    SwipeRefreshLayout mSwipe;

    private SoAdapter mAdapter;
    private SeApiManager mSeApiManager = new SeApiManager(new RetrofitServiceFactory());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_list, container, false);
        ButterKnife.inject(this, view);
        mAdapter = new SoAdapter(new ArrayList<>());
        mAdapter.setOpenProfileListener(this::open);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearOnScrollListeners();
        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                refreshList(current_page);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mSwipe.setOnRefreshListener(this::onRefresh);

        if (savedInstanceState == null)
            refreshList(1);
        return view;
    }

    private void refreshList(int pageNo) {
        showRefresh(true);
        mSeApiManager.getMostPopularSOusers(pageNo) //
                .subscribe(
                        users -> {
                            showRefresh(false);
                            mAdapter.updateUsers(users);
                        },
                        error -> {
                            App.L.error(error.toString());
                            showRefresh(false);
                        }
                );
    }

    private void showRefresh(boolean show) {
        mSwipe.setRefreshing(show);
        int visibility = show ? View.GONE : View.VISIBLE;
        mRecyclerView.setVisibility(visibility);
    }

    public void open(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void onRefresh() {
        refreshList(1);
    }
}
