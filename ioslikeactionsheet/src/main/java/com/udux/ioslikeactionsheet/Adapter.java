package com.udux.ioslikeactionsheet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hb on 24/8/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Menuitem> menuitems = new ArrayList<>();
    private OnMenuItemClickListener onMenuItemClickListener;

    public Adapter(Context context, ArrayList<Menuitem> menuitems, OnMenuItemClickListener onMenuItemClickListener) {
        this.context = context;
        this.menuitems = menuitems;
        this.onMenuItemClickListener = onMenuItemClickListener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.menu_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Menuitem menuitem = menuitems.get(holder.getAdapterPosition());
        holder.tvMenuItem.setText(menuitem.text);
        if (menuitem.drawableResID > 0) {
            holder.tvMenuItem.setGravity(Gravity.LEFT);
            holder.tvMenuItem.setCompoundDrawablesWithIntrinsicBounds(menuitem.drawableResID, 0, 0, 0);
        } else {
            holder.tvMenuItem.setGravity(Gravity.CENTER);
        }

        if (menuitems.size() > 1) {
            if (holder.getAdapterPosition() == 0) {
                holder.tvMenuItem.setBackground(context.getResources().getDrawable(R.drawable.actionsheet_top_selector));
            } else if (holder.getAdapterPosition() == (menuitems.size() - 1)) {
                holder.tvMenuItem.setBackground(context.getResources().getDrawable(R.drawable.actionsheet_bottom_selector));
            } else {
                holder.tvMenuItem.setBackground(context.getResources().getDrawable(R.drawable.actionsheet_selector_flat));
            }
        } else {
            holder.tvMenuItem.setBackground(context.getResources().getDrawable(R.drawable.actionsheet_selector));
        }

        if (holder.getAdapterPosition() == (menuitems.size() - 1)) {
            holder.ivDivider.setVisibility(View.GONE);
        } else {
            holder.ivDivider.setVisibility(View.VISIBLE);
        }

//        int topPadding;
//        if (holder.getAdapterPosition() == 0) {
//            topPadding = context.getResources().getDimension()
//        }
//
//        holder.tvMenuItem.setPadding();
    }

    @Override
    public int getItemCount() {
        return menuitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tvMenuItem;
        ImageView ivDivider;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            tvMenuItem = (TextView) view.findViewById(R.id.tvMenuItem);
            ivDivider = (ImageView) view.findViewById(R.id.ivDivider);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onMenuItemClickListener != null) {
                        onMenuItemClickListener.OnMenuItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
