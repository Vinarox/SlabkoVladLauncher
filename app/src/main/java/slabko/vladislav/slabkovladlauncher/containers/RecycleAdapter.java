package slabko.vladislav.slabkovladlauncher.containers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import slabko.vladislav.slabkovladlauncher.R;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private Context mContext;
    private int view_width = 50;
    private int view_height = 50;
    private int numbCol;
    private Point size;
    private int gap = 10;
    private List<ApplicationInfo> packages;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public MyViewHolder(View v) {
            super(v);
            mView = v;

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycleAdapter(Context c, int numbCol, List<ApplicationInfo> packages) {
        this.packages = packages;
        mContext = c;
        this.numbCol = numbCol;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        this.view_height = this.view_width = (size.x - gap * (numbCol + 1)) / numbCol;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View view;
            // if it's not recycled, initialize some attributes
            view = new View(mContext);
            view.setLayoutParams(new ViewGroup.LayoutParams(view_width, view_height));
            //view.setScaleType(View.ScaleType.CENTER_CROP);
            view.setPadding(8, 8, 8, 8);


        //view.setImageResource(R.drawable.square);
        view.setBackgroundResource(R.drawable.square);
        Random rand = new Random();
        view.setBackgroundColor(Color.argb(255,rand.nextInt(256),
                rand.nextInt(256),
                rand.nextInt(256)));
        ColorDrawable colorDrawable = (ColorDrawable)view.getBackground();
        //view.setTest(String.valueOf(colorDrawable.getColor()));
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mView.setOnLongClickListener (new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               // Toast.makeText(mContext, String.valueOf(v.getBackground()), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }
}
