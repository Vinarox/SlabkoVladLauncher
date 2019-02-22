package slabko.vladislav.slabkovladlauncher.containers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import slabko.vladislav.slabkovladlauncher.R;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutInflater ltInflater;
    private FloatingActionButton fab;
    private int itemNumber = 1000;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public MyViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyListAdapter(Context c, LayoutInflater ltInflater) {
        this.mContext = c;
        this.ltInflater = ltInflater;
    }

    @Override
    public MyListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ltInflater.inflate(R.layout.three_line_list_avatar, null, false);
        GradientDrawable drawable = (GradientDrawable) view.getResources().getDrawable(R.drawable.circle);
        Random rand = new Random();
        StringBuffer str = new StringBuffer();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        str.append("#");
        str.append(Integer.toHexString(Color.red(r)) + ";");
        str.append(Integer.toHexString(Color.green(g)) + ";");
        str.append(Integer.toHexString(Color.blue(b)));

        TextView txt = view.findViewById(R.id.rgb);
        txt.setText(str);

        view.setLongClickable(true);
        drawable.setColor(Color.argb(255,r, g, b));
        MyViewHolder vh = new MyViewHolder(view);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.view.setOnLongClickListener(new MyOnLongClickListener(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemNumber;
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
        }
    };
}
