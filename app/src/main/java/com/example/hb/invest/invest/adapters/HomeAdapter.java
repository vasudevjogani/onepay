package com.example.hb.invest.invest.adapters;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.RowHomeBinding;
import com.example.hb.invest.invest.activities.BundleActivity;
import com.example.hb.invest.invest.activities.CouncilBillsActivity;
import com.example.hb.invest.invest.activities.DomesticTaxesActivity;
import com.example.hb.invest.invest.activities.ElectricityActivity;
import com.example.hb.invest.invest.activities.LandLineActivity;
import com.example.hb.invest.invest.activities.MobileActivity;
import com.example.hb.invest.invest.activities.PayTvActivity;
import com.example.hb.invest.invest.activities.WaterActivity;
import com.example.hb.invest.invest.fragments.HomeFragment;
import com.example.hb.invest.invest.models.Home;
import com.example.hb.invest.invest.models.HomeResponse;

import java.util.ArrayList;

/**
 * Created by hb on 24/1/18.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Home> homeList;
    private LayoutInflater inflater;
    HomeFragment fragment;

    public HomeAdapter(Context context, ArrayList<Home> homeList, HomeFragment fragment) {
        this.context = context;
        this.homeList = homeList;
        this.fragment = fragment;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Home home = homeList.get(position);

        holder.binding.tvName.setText(home.name);

        holder.binding.ivImage.setImageResource(home.image);

        holder.binding.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (home.name.equals("Mobile")) {
                    fragment.navigateToActivity("Mobile");
                } else if (home.name.equals("Water")) {
                    fragment.navigateToActivity("Water");
                } else if (home.name.equals("Pay TV")) {
                    fragment.navigateToActivity("Pay TV");
                } else if (home.name.equals("Domestic Taxes")) {
                    fragment.navigateToActivity("Domestic Taxes");
                } else if (home.name.equals("Council Bills")) {
                    fragment.navigateToActivity("Council Bills");
                } else if (home.name.equals("Electricity")) {
                    fragment.navigateToActivity("Electricity");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RowHomeBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
