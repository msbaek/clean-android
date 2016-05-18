package com.github.msbaek.rxessentials.user;

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
import android.widget.Toast;

import com.github.msbaek.rxessentials.R;
import com.github.msbaek.rxessentials.common.BaseFragment;
import com.github.msbaek.rxessentials.common.EndlessRecyclerOnScrollListener;
import com.github.msbaek.rxessentials.common.RxBus;
import com.github.msbaek.rxessentials.di.UserComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UserListFragment extends BaseFragment implements UserListView {
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerView;
    @InjectView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    private SoAdapter recyclerViewAdapter;
    @Inject
    UserListPresenter presenter;
    @Inject
    RxBus rxBus;

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

        rxBus.toObserverable().subscribe(
                o -> {
                    Toast.makeText(getActivity(), "UserListFragment: event received [" + o + "]", Toast.LENGTH_SHORT).show();
                }
        );
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initAdapter();
        initRecyclerView();
        initSwipe();

        presenter.setView(this);

        if (savedInstanceState == null)
            refreshList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
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
                presenter.loadUserList(current_page);
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initAdapter() {
        recyclerViewAdapter = new SoAdapter(new ArrayList<>());
        recyclerViewAdapter.setOpenProfileListener(this::open);
    }

    private void refreshList() {
        presenter.initialize();
    }

    public void showRefresh(boolean show) {
        swipeRefreshLayout.setRefreshing(show);
        int visibility = show ? View.GONE : View.VISIBLE;
        recyclerView.setVisibility(visibility);
    }

    @Override
    public void updateUsers(List<User> users) {
        recyclerViewAdapter.updateUsers(users);
    }

    public void open(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void onRefresh() {
        refreshList();
    }
}
