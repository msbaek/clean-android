package com.github.msbaek.rxessentials.common;

import android.app.Fragment;
import android.widget.Toast;

import com.github.msbaek.rxessentials.di.HasComponent;

public abstract class BaseFragment extends Fragment {
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
