package slabko.vladislav.slabkovladlauncher.AsyncTasks;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import java.util.List;

import slabko.vladislav.slabkovladlauncher.additional.AppInfo;
import slabko.vladislav.slabkovladlauncher.additional.ItemsInfo.ItemInfo;
import slabko.vladislav.slabkovladlauncher.global.Constants;
import slabko.vladislav.slabkovladlauncher.global.MyLoads;

public class AsyncAppsInfo extends AsyncTask<Context, Void, AppInfo> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected AppInfo doInBackground(Context... context) {
        AppInfo info = new AppInfo();
        final PackageManager pm = context[0].getPackageManager();
        final List<ApplicationInfo> appInfo = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        int index = 0;
        for (ApplicationInfo app: appInfo){
            try {
                if (!isSystemPackage(pm.getPackageInfo(app.packageName, 0)) &&
                        !(app.packageName.equals(context[0].getPackageName()))) {
                    ItemInfo itemInfo = new ItemInfo(
                            app.loadIcon(pm),
                            app.loadLabel(context[0].getPackageManager()).toString(),
                            pm.getLaunchIntentForPackage(app.packageName),
                            app.packageName,
                            pm.getPackageInfo(app.packageName, 0).firstInstallTime,
                            index
                            );
                    index++;
                    info.itemSet.add(itemInfo);
                }
                AppInfo.sortInfo(false);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return info;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    @Override
    protected void onPostExecute(AppInfo result) {
        super.onPostExecute(result);
        Constants.apps = result;
    }
}
