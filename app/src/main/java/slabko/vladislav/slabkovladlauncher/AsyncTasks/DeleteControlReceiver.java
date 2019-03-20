package slabko.vladislav.slabkovladlauncher.AsyncTasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import slabko.vladislav.slabkovladlauncher.additional.AppInfo;
import slabko.vladislav.slabkovladlauncher.containers.MyListAdapter;

public class DeleteControlReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            String delPackage = String.valueOf(intent.getData());
            AppInfo.deleteFromLists((delPackage.split(":"))[1]);
        } else if (intent.getAction().equals("android.intent.action.PACKAGE_INSTALL")) {
            try {
                AppInfo.mAddPackage(context, intent);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
