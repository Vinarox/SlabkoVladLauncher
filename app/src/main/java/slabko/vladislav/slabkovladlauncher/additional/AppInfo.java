package slabko.vladislav.slabkovladlauncher.additional;

import android.content.Intent;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

import slabko.vladislav.slabkovladlauncher.containers.GridFragment;
import slabko.vladislav.slabkovladlauncher.containers.ListFragment;

public class AppInfo {
    public static ArrayList<Drawable> images;
    public static ArrayList<String> names;
    public static ArrayList<Intent> intents;

    public AppInfo(){
        this.images = new ArrayList<>();
        this.names = new ArrayList<>();
        this.intents = new ArrayList<>();
    }

    public static void deleteItem(int pos){
        ListFragment.notifyMe();
        GridFragment.notifyMe();

        images.remove(pos);
        names.remove(pos);
        intents.remove(pos);
    }

    public static void addItem(int pos){
        ListFragment.notifyMe();
        GridFragment.notifyMe();
    }
}
