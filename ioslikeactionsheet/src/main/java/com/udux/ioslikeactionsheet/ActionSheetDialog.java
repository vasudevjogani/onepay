package com.udux.ioslikeactionsheet;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hb on 24/8/17.
 */

public class ActionSheetDialog {
    ArrayList<Menuitem> menuitems = new ArrayList<>();
    Menuitem cancelItem;
    OnMenuItemClickListener onMenuItemClickListener;

    public static Dialog showDialog(Context context, ArrayList<Menuitem> menuitems, OnMenuItemClickListener onMenuItemClickListener) {
       return showDialog(context, menuitems, onMenuItemClickListener, new Menuitem("Cancel", 0));
    }

    public static Dialog showDialog(Context context, ArrayList<Menuitem> menuitems, final OnMenuItemClickListener onMenuItemClickListener, Menuitem cancelItem) {
        ActionSheetDialog actionSheetDialog = new ActionSheetDialog();
        actionSheetDialog.menuitems = menuitems;
        actionSheetDialog.cancelItem = cancelItem;
        actionSheetDialog.onMenuItemClickListener = onMenuItemClickListener;
//        actionSheetDialog.show(fragmentManager, ActionSheetDialog.class.getSimpleName());

        final Dialog dialog = new Dialog(context, R.style.ActionSheetTheme);
        dialog.setContentView(R.layout.layout_menu);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.setCancelable(true);


        RecyclerView rvMenu = (RecyclerView) dialog.findViewById(R.id.rvMenu);
        TextView txtcancel = (TextView) dialog.findViewById(R.id.txtcancel);

        rvMenu.setLayoutManager(new LinearLayoutManager(context));

        Adapter adapter = new Adapter(context, menuitems, new OnMenuItemClickListener() {
            @Override
            public void OnMenuItemClick(int position) {
                if (onMenuItemClickListener != null) {
                    onMenuItemClickListener.OnMenuItemClick(position);
                }
                dialog.dismiss();
            }
        });
        rvMenu.setAdapter(adapter);
        txtcancel.setText(cancelItem.text);
        if (cancelItem.drawableResID > 0) {
            txtcancel.setCompoundDrawablesWithIntrinsicBounds(cancelItem.drawableResID, 0, 0, 0);
        }

        txtcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        return dialog;
    }

}
