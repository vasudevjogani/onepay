package com.example.hb.invest.invest.activities;

import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityAddBeneficialyBinding;
import com.example.hb.invest.invest.adapters.HomeAdapter;
import com.example.hb.invest.invest.fragments.HomeFragment;
import com.example.hb.invest.invest.models.Home;
import com.example.hb.invest.invest.models.HomeResponse;
import com.example.hb.invest.invest.utiles.views.IParser;
import com.example.hb.invest.invest.utiles.views.LoadingUtils;
import com.example.hb.invest.invest.utiles.views.WSFactory;
import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.example.hb.invest.invest.utiles.views.WSUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddUtilityBeneficiaryActivity extends AppCompatActivity implements IParser<WSResponse> {

    ActivityAddBeneficialyBinding binding;
    private Toolbar actionBarToolbar;
    private ActionBar ab;
    private List<String> categoryList = new ArrayList<>();
    private ArrayList<HomeResponse.Mobile> mobileList = new ArrayList<>();
    private ArrayList<HomeResponse.Paytv> paytvList = new ArrayList<>();
    private ArrayList<HomeResponse.WaterBill> waterBillList = new ArrayList<>();
    private ArrayList<HomeResponse.DomesticTax> domesticTaxList = new ArrayList<>();
    private ArrayList<HomeResponse.CouncilBill> councilBillList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_beneficialy);
        setupToolbar();
        initComponent();
    }

    private void setupToolbar() {
        ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.btn_back);
        ab.setTitle("Add Utility Beneficiary");
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
        requestForCategoryWs();

        binding.categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<String> utilityList = new ArrayList<>();
                utilityList.add(0, "Select Utility");
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(AddUtilityBeneficiaryActivity.this, R.color.light_gray));
                } else {
                    ((TextView) view).setTextColor(ContextCompat.getColor(AddUtilityBeneficiaryActivity.this, R.color.colorPrimaryDark));
                }

                if (((TextView) view).getText().toString().equalsIgnoreCase("Mobile")) {
                    if (mobileList != null && mobileList.size() > 0) {
                        for (int i = 0; i < mobileList.size(); i++) {
                            utilityList.add(mobileList.get(i).getUtilityName());
                        }
                    }
                } else if (((TextView) view).getText().toString().equalsIgnoreCase("Pay TV")) {
                    if (paytvList != null && paytvList.size() > 0) {
                        for (int i = 0; i < paytvList.size(); i++) {
                            utilityList.add(paytvList.get(i).getUtilityName());
                        }
                    }
                } else if (((TextView) view).getText().toString().equalsIgnoreCase("Electricity")) {
                    utilityList.add("electricity");
                } else if (((TextView) view).getText().toString().equalsIgnoreCase("Council Bills")) {
                    if (councilBillList != null && councilBillList.size() > 0) {
                        for (int i = 0; i < councilBillList.size(); i++) {
                            utilityList.add(councilBillList.get(i).getUtilityName());
                        }
                    }
                } else if (((TextView) view).getText().toString().equalsIgnoreCase("Domestic Taxes")) {
                    if (domesticTaxList != null && domesticTaxList.size() > 0) {
                        for (int i = 0; i < domesticTaxList.size(); i++) {
                            utilityList.add(domesticTaxList.get(i).getUtilityName());
                        }
                    }

                } else if (((TextView) view).getText().toString().equalsIgnoreCase("Water")) {
                    if (waterBillList != null && waterBillList.size() > 0) {
                        for (int i = 0; i < waterBillList.size(); i++) {
                            utilityList.add(waterBillList.get(i).getUtilityName());
                        }
                    }
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddUtilityBeneficiaryActivity.this, android.R.layout.simple_spinner_item, utilityList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.utilitySpinner.setAdapter(dataAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.utilitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(AddUtilityBeneficiaryActivity.this, R.color.light_gray));
                } else {
                    ((TextView) view).setTextColor(ContextCompat.getColor(AddUtilityBeneficiaryActivity.this, R.color.colorPrimaryDark));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void requestForCategoryWs() {
        LoadingUtils.getInstance(this).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_HOME);
        HashMap<String, String> params = new HashMap<>();
        wsUtils.WSRequest(this, params, null, WSUtils.REQ_HOME, this);
    }

    @Override
    public void successResponse(int requestCode, WSResponse response) {
        switch (requestCode) {
            case WSUtils.REQ_HOME:
                parseHomeWs((HomeResponse) response);
                break;
            default:
                break;
        }
    }

    private void parseHomeWs(HomeResponse response) {
        try {
            if (response != null && response.getStatus() == 200) {

                if (response.getUtilityList().getMobile() != null && response.getUtilityList().getMobile().size() > 0) {
                    categoryList.add("Mobile");
                    mobileList.addAll(response.getUtilityList().getMobile());
                }

                if (response.getUtilityList().getPaytv() != null && response.getUtilityList().getPaytv().size() > 0) {
                    categoryList.add("Pay TV");
                    paytvList.addAll(response.getUtilityList().getPaytv());
                }

                if (response.getUtilityList().getElectricity() != null && response.getUtilityList().getElectricity().size() > 0) {
                    categoryList.add("Electricity");
                }

                if (response.getUtilityList().getCouncilBills() != null && response.getUtilityList().getCouncilBills().size() > 0) {
                    categoryList.add("Council Bills");
                    councilBillList.addAll(response.getUtilityList().getCouncilBills());
                }
                if (response.getUtilityList().getDomesticTax() != null && response.getUtilityList().getDomesticTax().size() > 0) {
                    categoryList.add("Domestic Taxes");
                    domesticTaxList.addAll(response.getUtilityList().getDomesticTax());
                }

                if (response.getUtilityList().getWaterBills() != null && response.getUtilityList().getWaterBills().size() > 0) {
                    categoryList.add("Water");
                    waterBillList.addAll(response.getUtilityList().getWaterBills());
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.categorySpinner.setAdapter(dataAdapter);

            } else {
                Toast.makeText(AddUtilityBeneficiaryActivity.this, response != null ? response.getMessage() : "", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadingUtils.getInstance(AddUtilityBeneficiaryActivity.this).hideLoading();
    }

    @Override
    public void errorResponse(int requestCode, Throwable t) {
        LoadingUtils.getInstance(AddUtilityBeneficiaryActivity.this).hideLoading();
        Toast.makeText(AddUtilityBeneficiaryActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noInternetConnection(int requestCode) {
        LoadingUtils.getInstance(AddUtilityBeneficiaryActivity.this).hideLoading();
        Toast.makeText(AddUtilityBeneficiaryActivity.this, R.string.internet_not_available, Toast.LENGTH_SHORT).show();
    }


}
