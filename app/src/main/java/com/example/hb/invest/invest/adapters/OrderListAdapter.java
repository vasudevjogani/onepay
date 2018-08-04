package com.example.hb.invest.invest.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hb.invest.R;
import com.example.hb.invest.databinding.RowHomeBinding;
import com.example.hb.invest.databinding.RowOrderBinding;
import com.example.hb.invest.invest.fragments.HomeFragment;
import com.example.hb.invest.invest.models.Home;
import com.example.hb.invest.invest.models.OrderListResponse;
import com.example.hb.invest.invest.utiles.views.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 24/1/18.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {

    private Context context;
    private List<OrderListResponse.OrderList> orderList;
    private LayoutInflater inflater;

    public OrderListAdapter(Context context, List<OrderListResponse.OrderList> orderList) {
        this.context = context;
        this.orderList = orderList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final OrderListResponse.OrderList order = orderList.get(position);

        if (order.getType().equalsIgnoreCase("electricity")) {
            holder.binding.tvOrderType.setText("Electricity");
        } else if (order.getType().equalsIgnoreCase("mobile")) {
            holder.binding.tvOrderType.setText("Mobile");
        } else if (order.getType().equalsIgnoreCase("paytv")) {
            holder.binding.tvOrderType.setText("PayTv");
        } else if (order.getType().equalsIgnoreCase("council_bill")) {
            holder.binding.tvOrderType.setText("Council Bill");
        }

        holder.binding.tvOrderId.setText("Order ID : "+order.getOrderId());

        if (!TextUtils.isEmpty(order.getAmount())) {
            holder.binding.tvAmount.setText(order.getAmount());
        }

        if (!TextUtils.isEmpty(order.getTaxAmount())) {
            holder.binding.tvTaxAmount.setText(order.getTaxAmount());
        }

        if (!TextUtils.isEmpty(order.getTotalAmount())) {
            holder.binding.tvTotalAmount.setText(order.getTotalAmount());
        }

        if (order.getType().equalsIgnoreCase("electricity") && !TextUtils.isEmpty(order.getMeterNumber())) {
            holder.binding.tvNumberTitle.setText("Meter Number : ");
            holder.binding.tvNumber.setText(order.getMeterNumber());
            holder.binding.llMobile.setVisibility(View.VISIBLE);
        } else if (order.getType().equalsIgnoreCase("mobile") && !TextUtils.isEmpty(order.getMobileNumber())) {
            holder.binding.tvNumberTitle.setText("Mobile Number : ");
            holder.binding.tvNumber.setText(order.getMobileNumber());
            holder.binding.llMobile.setVisibility(View.VISIBLE);
        } else {
            holder.binding.llMobile.setVisibility(View.GONE);
        }

        if (order.getType().equalsIgnoreCase("paytv") && !TextUtils.isEmpty(order.getAccountNumber())) {
            holder.binding.tvAccountTitle.setText("Account Number : ");
            holder.binding.tvAccNumber.setText(order.getAccountNumber());
            holder.binding.llAccount.setVisibility(View.VISIBLE);
        } else if (order.getType().equalsIgnoreCase("council_bill") && !TextUtils.isEmpty(order.getPlotNumber())) {
            holder.binding.tvAccountTitle.setText("Plot Number : ");
            holder.binding.tvAccNumber.setText(order.getPlotNumber());
            holder.binding.llAccount.setVisibility(View.VISIBLE);
        } else {
            holder.binding.llAccount.setVisibility(View.GONE);
        }

        holder.binding.tvOrderStatus.setText(order.getStatus());

        if (order.getStatus().equalsIgnoreCase("completed")) {
            holder.binding.tvOrderStatus.setTextColor(context.getResources().getColor(R.color.green));
            holder.binding.tvMoreInfo.setVisibility(View.VISIBLE);
        } else if (order.getStatus().equalsIgnoreCase("initiated")) {
            holder.binding.tvOrderStatus.setTextColor(context.getResources().getColor(R.color.white));
            holder.binding.tvMoreInfo.setVisibility(View.GONE);
        } else if (order.getStatus().equalsIgnoreCase("cancelled")) {
            holder.binding.tvOrderStatus.setTextColor(context.getResources().getColor(R.color.red));
            holder.binding.tvMoreInfo.setVisibility(View.GONE);
        } else {
            holder.binding.tvMoreInfo.setVisibility(View.GONE);
        }

        holder.binding.tvDate.setText(order.getCreatedDate());

        holder.binding.tvForeignNo.setText(order.getTransactionId());
        holder.binding.tvPStatus.setText(order.getTransactionMessage());

        holder.binding.tvMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(context)
                        .title(order.getTransactionReference())
                        .backgroundColor(ContextCompat.getColor(context, R.color.white))
                        .contentColor(ContextCompat.getColor(context, R.color.grey_dialog))
                        .titleColor(ContextCompat.getColor(context, R.color.app_color))
                        .positiveColor(context.getResources().getColor(R.color.app_color))
                        .content(Html.fromHtml(order.getResponseMessage()))
                        .positiveText("OK")
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RowOrderBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
