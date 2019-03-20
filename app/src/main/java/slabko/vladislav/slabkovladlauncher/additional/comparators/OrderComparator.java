package slabko.vladislav.slabkovladlauncher.additional.comparators;

import java.util.Comparator;

import slabko.vladislav.slabkovladlauncher.additional.ItemsInfo.ItemInfo;
import slabko.vladislav.slabkovladlauncher.global.Constants;

public class OrderComparator implements Comparator<ItemInfo> {

    public int compare(ItemInfo item1, ItemInfo item2) {
        switch (Constants.SORT_CURRENT){
            case Constants.NO_SORT:
                return noSort(item1, item2);
            case Constants.DATE_SORT:
                return dateSort(item1, item2);
            case Constants.AZ_SORT:
                return azSort(item1, item2);
            case Constants.ZA_SORT:
                return zaSort(item1, item2);
        }
        return 0;
    }

    int noSort(ItemInfo item1, ItemInfo item2){
        if(item1.index > item2.index)
            return 1;
        else
            return -1;
    }

    int dateSort(ItemInfo item1, ItemInfo item2){
        if(item1.firstInstallTime > item2.firstInstallTime)
        return 1;
    else
        return -1;
    }

    int azSort(ItemInfo item1, ItemInfo item2){
        return (item1.name).compareTo(item2.name);
    }

    int zaSort(ItemInfo item1, ItemInfo item2){
        return (item1.name).compareTo(item2.name) * -1;
    }

}