package slabko.vladislav.slabkovladlauncher.containers;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Patterns;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;

import slabko.vladislav.slabkovladlauncher.AsyncTasks.AsyncFavicon;
import slabko.vladislav.slabkovladlauncher.R;
import slabko.vladislav.slabkovladlauncher.global.Constants;


public class Desktop extends Fragment {
    private static Context mContext;
    private  static int TABLE_ROWS = 5;
    private static int TABLE_COLUMNS = 4;
    private static TableRow rows[] = new TableRow[TABLE_ROWS];
    private static DesktopElements mDesktop[][] = new DesktopElements[TABLE_ROWS][TABLE_COLUMNS];
    static final Bitmap[] dr = new Bitmap[1];
    private static LayoutInflater mInflater;

    public Desktop() {
        // Required empty public constructor
    }


    public static Desktop newInstance(Context c) {
        Desktop.mContext = c;
        Desktop pageFragment = new Desktop();
        Bundle arguments = new Bundle();
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mInflater = inflater;
        View v = inflater.inflate(R.layout.fragment_desktop, container, false);
        TableLayout tableLayout = v.findViewById(R.id.tableLayout);
        for (int i = 0; i < TABLE_ROWS; i++) {
            rows[i] = new TableRow(mContext);
            rows[i].setOrientation(LinearLayout.HORIZONTAL);
            rows[i].setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT));
            for (int j = 0; j < TABLE_COLUMNS; j++) {
                View item = inflater.inflate(R.layout.cell_box, null, false);
                item.setMinimumHeight(Constants.screenHight / (TABLE_ROWS + 1));
                item.setMinimumWidth(Constants.screenWidth / (TABLE_COLUMNS));
                mDesktop[i][j] = new DesktopElements(getActivity(), true, i, j, item);
                rows[i].addView(item, j);
            }
            tableLayout.addView(rows[i], i);
        }

        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });
        return v;
    }

    private void showInputDialog(){
        final String[] page = new String[1];

        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle("Web-page");
        alert.setMessage("Enter site address");

        final EditText input = new EditText(mContext);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                page[0] = String.valueOf(input.getText());
                if(page[0].length() != 0) {
                    AsyncFavicon faviconTask = new AsyncFavicon();
                    faviconTask.execute(page[0]);
                    try {
                        dr[0] = faviconTask.get();
                        setDesktopItem(dr[0], page[0]);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.show();
    }


    void setDesktopItem(Bitmap bt, String page){
        int[] cell = getFirstFreeCell();

        //final LayoutInflater inflater = getLayoutInflater();
        View item = mInflater.inflate(R.layout.vertical_avatar, null, false);

        item.setMinimumHeight(Constants.screenHight / (TABLE_ROWS + 1));

        if(cell != null) {
            ViewGroup container = (ViewGroup) mDesktop[cell[0]][cell[1]].getContainer();
            container.addView(item, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT));
            mDesktop[cell[0]][cell[1]].setVeiw(item, bt, page);
            mDesktop[cell[0]][cell[1]].setFree(false);
        }
    }


    int[] getFirstFreeCell(){
        int[] cell = new int[2];
        for (int i = 0; i < TABLE_ROWS; i++) {
            for (int j = 0; j < TABLE_COLUMNS; j++) {
                if(mDesktop[i][j].isFree()) {
                    cell[0] = mDesktop[i][j].getRow();
                    cell[1] = mDesktop[i][j].getColumn();
                    return cell;
                }
            }
        }
        return null;
    }

    void mStartActivity(Intent browserIntent){
        startActivity(browserIntent);
    }
}
