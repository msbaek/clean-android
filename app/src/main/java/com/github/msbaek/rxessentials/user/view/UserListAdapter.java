package com.github.msbaek.rxessentials.user.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.msbaek.rxessentials.R;
import com.github.msbaek.rxessentials.user.domain.User;
import com.nostra13.universalimageloader.core.ImageLoader;
import rx.Observer;
import rx.Subscription;
import rx.android.view.ViewObservable;
import rx.functions.Action1;
import rx.observers.Observers;
import rx.observers.Subscribers;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private List<User> mUsers = new ArrayList<>();
    private Observer<User> clickObserver = Observers.empty();

    public UserListAdapter(List<User> users) {
        mUsers = users;
    }

    public void updateUsers(List<User> users) {
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.so_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserListAdapter.ViewHolder holder, int position) {
        if (position < mUsers.size()) {
            User user = mUsers.get(position);
            holder.setUser(user);
        }
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.recycled();
    }

    @Override
    public int getItemCount() {
        return mUsers == null ? 0 : mUsers.size();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    public void onItemClick(final Action1<User> clickAction) {
        this.clickObserver = Observers.create(clickAction);
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.city)
        TextView city;
        @BindView(R.id.reputation)
        TextView reputation;
        @BindView(R.id.user_image)
        ImageView userImage;
        private Subscription subscribe = Subscribers.empty();

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }

        public void setUser(final User user) {
            name.setText(user.getDisplayName());
            city.setText(user.getLocation());
            reputation.setText(String.valueOf(user.getReputation()));

            ImageLoader.getInstance().displayImage(user.getProfileImage(), userImage);

            subscribe = ViewObservable.clicks(mView).map(onClickEvent -> user).subscribe(clickObserver);
        }

        public void recycled() {
            subscribe.unsubscribe();
        }
    }
}
