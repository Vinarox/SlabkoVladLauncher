package slabko.vladislav.slabkovladlauncher.containers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import slabko.vladislav.slabkovladlauncher.R;
import slabko.vladislav.slabkovladlauncher.additional.AppInfo;
import slabko.vladislav.slabkovladlauncher.listeners.ContextMenuListener;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutInflater ltInflater;
    private AppInfo appInfo;
    private MenuInflater menuInflater;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public MyViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyListAdapter(Context c, LayoutInflater ltInflater, AppInfo appInfo, MenuInflater menuInflater) {
        this.appInfo = appInfo;
        this.mContext = c;
        this.ltInflater = ltInflater;
        this.menuInflater = menuInflater;
    }

    @Override
    public MyListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ltInflater.inflate(R.layout.three_line_list_avatar, null, false);
        view.setLongClickable(true);

        MyViewHolder vh = new MyViewHolder(view);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final int pos = position;
        holder.view.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(appInfo.intents.get(pos));
            }
        });
        //holder.view.setOnLongClickListener(new MyOnLongClickListener(position));
        ImageView avatar = holder.view.findViewById(R.id.avatar);
        TextView app_name = holder.view.findViewById(R.id.app_name);
        avatar.setImageDrawable(appInfo.images.get(position));
        app_name.setText(appInfo.names.get(position));
        ContextMenuListener contextMenuListener = new ContextMenuListener(menuInflater,
                appInfo.intents.get(position), position, holder.itemView);
        holder.view.setOnCreateContextMenuListener(contextMenuListener);
    }


    @Override
    public int getItemCount() {
        return appInfo.images.size();
    }

    class MyOnLongClickListener implements View.OnLongClickListener {
        private int pos;
        public MyOnLongClickListener(int pos){
            this.pos = pos;
        }

        @Override
        public boolean onLongClick(View v) {
            //v.getVerticalScrollbarPosition();
            Snackbar snackbar = Snackbar.make(v, "Delete item?", Snackbar.LENGTH_LONG)
                    .setAction("Да", new SnackbarOnClickListener(pos))
                    .setDuration(5000);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.argb(255, 0,215,208));
            snackbar.show();
            return false;
        }
    }

    class SnackbarOnClickListener implements View.OnClickListener {
        private int pos;
        public SnackbarOnClickListener(int pos){
            this.pos = pos;
        }
        @Override
        public void onClick(View view) {
            MyListAdapter.this.notifyItemRemoved(pos);
            //MyListAdapter.this.notifyItemInserted(0);
        }
    }


}
