package com.example.hb.invest.invest.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityUtilityBenificiaryListBinding;
import com.example.hb.invest.invest.adapters.UtilityBeneficiaryListAdapter;
import com.example.hb.invest.invest.models.UtilityBeneficiaryResponse;

import java.util.ArrayList;

public class WalletListActivity extends AppCompatActivity {

    ActivityUtilityBenificiaryListBinding binding;
    private Toolbar actionBarToolbar;
    private ActionBar ab;
    private ArrayList<UtilityBeneficiaryResponse> utilityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_utility_benificiary_list);
        setupToolbar();
        initComponent();
    }

    private void setupToolbar() {
        ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.btn_back);
        ab.setTitle("Wallet List");
        ab.setDisplayHomeAsUpEnabled(true);
    }

    protected ActionBar getActionBarToolbar() {
        if (actionBarToolbar == null) {
            actionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (actionBarToolbar != null) {
                actionBarToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                setSupportActionBar(actionBarToolbar);
            }
        }
        return getSupportActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initComponent() {
        binding.rvBanificiary.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 10; i++) {
            UtilityBeneficiaryResponse utility = new UtilityBeneficiaryResponse();
            utility.id = i + 1 + "";
            utilityList.add(utility);
        }

        UtilityBeneficiaryListAdapter adapter = new UtilityBeneficiaryListAdapter(this,utilityList);
        binding.rvBanificiary.setAdapter(adapter);
    }
}
