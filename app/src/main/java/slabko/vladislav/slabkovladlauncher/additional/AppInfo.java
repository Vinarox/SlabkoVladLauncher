package slabko.vladislav.slabkovladlauncher.additional;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Collections;

import slabko.vladislav.slabkovladlauncher.additional.ItemsInfo.ItemInfo;
import slabko.vladislav.slabkovladlauncher.additional.comparators.OrderComparator;
import slabko.vladislav.slabkovladlauncher.containers.GridFragment;
import slabko.vladislav.slabkovladlauncher.containers.ListFragment;


public class AppInfo {
    static AppInfo appInfo;

    public static ArrayList<ItemInfo> itemSet;

   

    public AppInfo(){
        this.appInfo = this;
        this.itemSet = new ArrayList<>();
    }

    public static void deleteFromLists(String packageName){
        int delIndex = findDeleteIndex(packageName);
        if(delIndex != -1) {
            itemSet.remove(delIndex);
            ListFragment.notifyMe(null, 0);
            GridFragment.notifyMe(null, 0);
        }
    }

    public static void deleteItem(int pos, Intent intent){
        ListFragment.notifyMe(intent, 1);
    }

    public static void addItem(int pos){
        ListFragment.notifyMe(null, 0);
        GridFragment.notifyMe(null, 0);
    }

   /* public static void mDeletePackage(String delPackage){
        int delIndex = packages.indexOf(delPackage);
        if(delIndex != -1) {
            names.remove(delIndex);
            images.remove(delIndex);
            intents.remove(delIndex);
            packages.remove(delIndex);

            ListFragment.notifyMe(null, 0);
            GridFragment.notifyMe(null, 0);
        }
    }*/

    static int findDeleteIndex(String delPackage){
       for(int i = 0; i < itemSet.size(); i++) {
           if(itemSet.get(i).mPackage.equals(delPackage))
               return i;
       }
       return -1;
   }
   

    public static void mAddPackage(Context context, Intent intent) throws PackageManager.NameNotFoundException {
        String addedPackage = String.valueOf(intent.getData()).split(":")[1];
        final PackageManager pm = context.getPackageManager();

        ApplicationInfo app = pm.getApplicationInfo(addedPackage, PackageManager.GET_META_DATA);
        itemSet.add(new ItemInfo(
                app.loadIcon(pm),
                app.loadLabel(context.getPackageManager()).toString(),
                pm.getLaunchIntentForPackage(app.packageName),
                app.packageName,
                pm.getPackageInfo(app.packageName, 0).firstInstallTime,
                itemSet.size()
        ));

        sortInfo(true);

        ListFragment.notifyAdd(1);
        GridFragment.notifyAdd(1);
    }
    

    public static void startSettings(Intent intent){
        ListFragment.notifyMe(intent, 1);
    }


    public static void sortInfo(boolean needNotify){
        Collections.sort(itemSet, new OrderComparator());
        if(needNotify) {
            GridFragment.notifyAllData();
            ListFragment.notifyAllData();
        }
    }
}
