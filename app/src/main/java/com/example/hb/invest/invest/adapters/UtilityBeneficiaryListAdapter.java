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
import com.example.hb.invest.databinding.RowOrderBinding;
import com.example.hb.invest.databinding.RowUtilityBeneficiaryBinding;
import com.example.hb.invest.invest.models.OrderListResponse;
import com.example.hb.invest.invest.models.UtilityBeneficiaryResponse;

import java.util.List;

/**
 * Created by hb on 24/1/18.
 */

public class UtilityBeneficiaryListAdapter extends RecyclerView.Adapter<UtilityBeneficiaryListAdapter.ViewHolder> {

    private Context context;
    private List<UtilityBeneficiaryResponse> UtilityList;
    private LayoutInflater inflater;

    public UtilityBeneficiaryListAdapter(Context context, List<UtilityBeneficiaryResponse> UtilityList) {
        this.context = context;
        this.UtilityList = UtilityList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_utility_beneficiary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final UtilityBeneficiaryResponse utility = UtilityList.get(position);

        holder.binding.tvUtilityId.setText(utility.id);



    }

    @Override
    public int getItemCount() {
        return UtilityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RowUtilityBeneficiaryBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
