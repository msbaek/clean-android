package com.github.msbaek.rxessentials.user.view;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Navigator {
    @Inject
    public Navigator() {
        //empty
    }

    /**
     * Goes to the user list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToUserList(Context context) {
//        if (context != null) {
//            Intent intentToLaunch = UserActivity.getCallingIntent(context);
//            context.startActivity(intentToLaunch);
//        }
    }

    /**
     * Goes to the user details screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToUserDetails(Context context, int userId) {
//        if (context != null) {
//            Intent intentToLaunch = UserDetailsActivity.getCallingIntent(context, userId);
//            context.startActivity(intentToLaunch);
//        }
    }
}
