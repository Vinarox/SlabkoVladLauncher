package slabko.vladislav.slabkovladlauncher.additional;

import android.content.Intent;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class AppInfo {
    public static ArrayList<Drawable> images;
    public static ArrayList<String> names;
    public static ArrayList<Intent> intents;

    public AppInfo(){
        this.images = new ArrayList<>();
        this.names = new ArrayList<>();
        this.intents = new ArrayList<>();
    }
}
