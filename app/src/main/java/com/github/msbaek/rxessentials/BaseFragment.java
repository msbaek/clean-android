package com.github.msbaek.rxessentials;

import android.app.Fragment;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment {
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
