package com.github.msbaek.rxessentials.user.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.msbaek.rxessentials.R;
import com.github.msbaek.rxessentials.user.domain.User;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.android.view.ViewObservable;

import static com.google.common.base.Preconditions.checkNotNull;

public class SoAdapter extends RecyclerView.Adapter<SoAdapter.ViewHolder> {
    private static ViewHolder.OpenProfileListener mProfileListener;
    private List<User> mUsers = new ArrayList<>();

    public SoAdapter(List<User> users) {
        mUsers = users;
    }

    public void updateUsers(List<User> users) {
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public SoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.so_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SoAdapter.ViewHolder holder, int position) {
        if (position < mUsers.size()) {
            User user = mUsers.get(position);
            holder.setUser(user);
        }
    }

    @Override
    public int getItemCount() {
        return mUsers == null ? 0 : mUsers.size();
    }

    public void setOpenProfileListener(ViewHolder.OpenProfileListener listener) {
        mProfileListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.city)
        TextView city;
        @InjectView(R.id.reputation)
        TextView reputation;
        @InjectView(R.id.user_image)
        ImageView userImage;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            mView = view;
        }

        public void setUser(final User user) {
            name.setText(user.getDisplayName());
            city.setText(user.getLocation());
            reputation.setText(String.valueOf(user.getReputation()));

            ImageLoader.getInstance().displayImage(user.getProfileImage(), userImage);

            ViewObservable.clicks(mView) //
                    .subscribe(
                            onClickEvent -> {
                                checkNotNull(mProfileListener, "Must implement OpoenProfileListener");

                                String url = user.getWebsiteUrl();
                                if (url != null && !url.equals("") && !url.contains("search")) {
                                    mProfileListener.open(url);
                                } else {
                                    mProfileListener.open(user.getLink());
                                }
                            }
                    );
        }

        public interface OpenProfileListener {
            void open(String url);
        }
    }
}
