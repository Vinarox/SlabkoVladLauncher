package slabko.vladislav.slabkovladlauncher.containers;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import slabko.vladislav.slabkovladlauncher.AppListActivity;
import slabko.vladislav.slabkovladlauncher.MainActivity;
import slabko.vladislav.slabkovladlauncher.R;
import slabko.vladislav.slabkovladlauncher.global.Constants;

public class DesktopElements {
    private boolean isFree;
    private int row;
    private int column;
    private View view;
    private Context mContext;

    public void setContainer(View container) {
        this.container = container;
    }

    private View container;

    public void setVeiw(View veiw) {
        this.view = veiw;
    }


    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setVeiw(View view, Bitmap bt, String page) {
        this.view = view;
        ItemClickListener lstnr = new ItemClickListener();
        this.view.setOnClickListener(lstnr);
        this.view.setOnLongClickListener(lstnr);
        ImageView image = this.view.findViewById(R.id.avatar_vert);
        if(bt != null)
            image.setImageBitmap(bt);
        else
            image.setImageResource(R.drawable.i1);
        TextView text = this.view.findViewById(R.id.app_name_vert);
        text.setText(page);
    }

    public DesktopElements(Context c, boolean isFree, int row, int column, View cont){
        this.mContext = c;
        this.isFree = isFree;
        this.row = row;
        this.column = column;
        this.container = cont;
        this.container.setOnDragListener(new MyDragListener());
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public boolean isFree() {
        return isFree;
    }

    public View getContainer() {
        return container;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public View getVeiw() {
        return view;
    }



    class MyDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    if(isFree) {
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                        isFree = false;
                    }
                    else {
                        view.setVisibility(View.VISIBLE);
                        return false;
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }
    }


    class ItemClickListener implements View.OnClickListener, View.OnLongClickListener {

        @Override
        public void onClick(View v) {
            TextView textView = v.findViewById(R.id.app_name_vert);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + String.valueOf(textView.getText())));
            mContext.startActivity(browserIntent);
        }

        @Override
        public boolean onLongClick(View view) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            //view.setVisibility(View.INVISIBLE);
            isFree = true;
            view.startDrag(data, shadowBuilder, view, 0);
            return true;
        }
    }

}
