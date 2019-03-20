package slabko.vladislav.slabkovladlauncher.additional.ItemsInfo;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class ItemInfo {
    public Drawable image;
    public String name;
    public Intent intent;
    public String mPackage;
    public int numOfLoads;
    public long firstInstallTime;
    public int index;

    public ItemInfo(Drawable image, String name, Intent intent, String mPackage, long firstInstallTime, int index){
        this.numOfLoads = 0;
        this.image = image;
        this.name = name;
        this.intent = intent;
        this.mPackage = mPackage;
        this.firstInstallTime = firstInstallTime;
        this.index = index;
    }
}
