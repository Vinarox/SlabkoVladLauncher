package slabko.vladislav.slabkovladlauncher.listeners;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import slabko.vladislav.slabkovladlauncher.R;
import slabko.vladislav.slabkovladlauncher.additional.AppInfo;

public class ContextMenuListener extends AppCompatActivity
        implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
    private MenuInflater menuInflater;
    private Intent intent;
    private int position;

    public ContextMenuListener(MenuInflater menuInflater, Intent intent, int position, View v) {
        this.menuInflater = menuInflater;
        this.intent = intent;
        this.position = position;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem deleteMenuItem = menu.add(
                Menu.NONE,
                R.id.context_delete,
                Menu.NONE,
                R.string.context_delete
        );
        deleteMenuItem.setOnMenuItemClickListener(this);

        MenuItem numberMenuItem = menu.add(
                Menu.NONE,
                R.id.context_number,
                Menu.NONE,
                R.string.context_number
        );
        numberMenuItem.setOnMenuItemClickListener(this);

        MenuItem infoMenuItem = menu.add(
                Menu.NONE,
                R.id.context_info,
                Menu.NONE,
                R.string.context_info
        );
        infoMenuItem.setOnMenuItemClickListener(this);
        //menuInflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_delete:
                Intent delIntent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
                delIntent.setData(Uri.parse("package:" + intent.getPackage()));
                delIntent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
                delIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //startActivityForResult(delIntent, 1);

                AppInfo.deleteItem(position);
                return true;
            case R.id.context_number:
                return true;
            case R.id.context_info:
                return true;
            default:
                return false;
        }
    }
}
