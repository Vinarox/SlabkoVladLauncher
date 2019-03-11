package slabko.vladislav.slabkovladlauncher.AsyncTasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import slabko.vladislav.slabkovladlauncher.containers.MyListAdapter;

public class DeleteControlReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            String str = intent.getPackage();
        }

        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String str = intent.getPackage();
        }
    }
}
