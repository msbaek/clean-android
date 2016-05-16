package com.github.msbaek.rxessentials;

import android.os.Bundle;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        super.changeFragment(R.id.user_list, new UserListFragment());
    }
}
