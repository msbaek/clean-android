package com.github.msbaek.rxessentials;

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

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UserListFragment extends BaseFragment {
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerView;
    @InjectView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    private SoAdapter recyclerViewAdapter;

    @Inject
    SeApiManager mSeApiManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(UserComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_list, container, false);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initAdapter();
        initRecyclerView();
        initSwipe();

        if (savedInstanceState == null)
            refreshList(1);
    }

    private void initSwipe() {
        swipeRefreshLayout.setOnRefreshListener(this::onRefresh);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.clearOnScrollListeners();
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                refreshList(current_page);
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initAdapter() {
        recyclerViewAdapter = new SoAdapter(new ArrayList<>());
        recyclerViewAdapter.setOpenProfileListener(this::open);
    }

    private void refreshList(int pageNo) {
        showRefresh(true);
        mSeApiManager.getMostPopularSOusers(pageNo) //
                .subscribe(
                        users -> {
                            showRefresh(false);
                            recyclerViewAdapter.updateUsers(users);
                        },
                        error -> {
                            App.L.error(error.toString());
                            showRefresh(false);
                        }
                );
    }

    private void showRefresh(boolean show) {
        swipeRefreshLayout.setRefreshing(show);
        int visibility = show ? View.GONE : View.VISIBLE;
        recyclerView.setVisibility(visibility);
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
