package slabko.vladislav.slabkovladlauncher.containers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;
import java.util.Random;

import slabko.vladislav.slabkovladlauncher.AppListActivity;
import slabko.vladislav.slabkovladlauncher.R;
import slabko.vladislav.slabkovladlauncher.additional.AppInfo;
import slabko.vladislav.slabkovladlauncher.global.Constants;
import slabko.vladislav.slabkovladlauncher.listeners.ContextMenuListener;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private Context mContext;
    private int view_width = 50;
    private int view_height = 50;
    private int numbCol;
    private int gap = 10;
    private AppInfo appInfo;
    private LayoutInflater ltInflater;
    private MenuInflater menuInflater;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public MyViewHolder(View v) {
            super(v);
            view = v;
        }
    }


    public RecycleAdapter(Context c, int numbCol, AppInfo appInfo, LayoutInflater ltInflater, MenuInflater menuInflater) {
        this.ltInflater = ltInflater;
        this.appInfo = appInfo;
        this.menuInflater = menuInflater;
        this.mContext = c;
        this.numbCol = numbCol;
        this.view_height = this.view_width = (Constants.screenWidth - (gap * (numbCol + 1))) / numbCol;
    }


    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View view = ltInflater.inflate(R.layout.vertical_avatar, null, false);
        view.setLongClickable(true);
        view.setMinimumWidth(this.view_width);
        view.setMinimumHeight(this.view_height);

        RecycleAdapter.MyViewHolder vh = new RecycleAdapter.MyViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final int pos = position;
        holder.view.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(appInfo.itemSet.get(pos).intent);
                appInfo.itemSet.get(pos).numOfLoads++;
            }
        });

        ImageView avatar = holder.view.findViewById(R.id.avatar_vert);
        TextView app_name = holder.view.findViewById(R.id.app_name_vert);
        avatar.setImageDrawable(appInfo.itemSet.get(position).image);
        app_name.setText(appInfo.itemSet.get(position).name);
        holder.itemView.setOnCreateContextMenuListener(new ContextMenuListener(menuInflater,
                appInfo.itemSet.get(position).intent, position, holder.itemView));
    }

    @Override
    public int getItemCount() {
        return appInfo.itemSet.size();
    }

}
