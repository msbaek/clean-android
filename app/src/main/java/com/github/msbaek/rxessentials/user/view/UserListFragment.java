package com.github.msbaek.rxessentials.user.view;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.github.msbaek.rxessentials.App;
import com.github.msbaek.rxessentials.R;
import com.github.msbaek.rxessentials.common.view.BaseFragment;
import com.github.msbaek.rxessentials.common.view.EndlessRecyclerOnScrollListener;
import com.github.msbaek.rxessentials.di.UserComponent;
import com.github.msbaek.rxessentials.user.domain.User;
import com.github.msbaek.rxessentials.user.domain.UserListPresenter;
import com.github.msbaek.rxessentials.user.domain.UserListView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class UserListFragment extends BaseFragment implements UserListView {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    private UserListAdapter recyclerViewAdapter;
    @Inject
    UserListPresenter presenter;
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(UserComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initAdapter();
        initRecyclerView();
        recyclerViewAdapter.onItemClick(user ->  presenter.openProfile(user));
        initSwipe();

        presenter.setView(this);

        if (savedInstanceState == null)
            refreshList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
        recyclerViewAdapter = new UserListAdapter(new ArrayList<>());
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

    public void openProfile(String url) {
        App.L.error("called with url=" + url);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void onRefresh() {
        refreshList();
    }
}
