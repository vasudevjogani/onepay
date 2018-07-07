package com.example.hb.invest.invest.activities;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityElectricityBinding;
import com.example.hb.invest.invest.models.PaymentResponse;
import com.example.hb.invest.invest.models.PriceWithTax;
import com.example.hb.invest.invest.utiles.views.Constant;
import com.example.hb.invest.invest.utiles.views.CustomTextView;
import com.example.hb.invest.invest.utiles.views.IParser;
import com.example.hb.invest.invest.utiles.views.LoadingUtils;
import com.example.hb.invest.invest.utiles.views.UserDetail;
import com.example.hb.invest.invest.utiles.views.WSFactory;
import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.example.hb.invest.invest.utiles.views.WSUtils;
import com.udux.ioslikeactionsheet.ActionSheetDialog;
import com.udux.ioslikeactionsheet.Menuitem;
import com.udux.ioslikeactionsheet.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ElectricityActivity extends AppCompatActivity implements IParser<WSResponse> {

    ActivityElectricityBinding binding;
    private Toolbar actionBarToolbar;
    private ActionBar ab;
    private boolean isManual;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_electricity);
        setupToolbar();
        initComponent();
    }

    private void initComponent() {
        binding.tvRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Menuitem> menuitems = new ArrayList<>();
                menuitems.add(new Menuitem("Airtel Money", R.drawable.airtel_logo));
                menuitems.add(new Menuitem("Mtn Money", R.drawable.mtn_logo));
                menuitems.add(new Menuitem("Zambia Kwacha", R.drawable.zamtel_logo));
                menuitems.add(new Menuitem("Card Payment", R.drawable.mastercard));

                if (TextUtils.isEmpty(binding.etMeterNo.getText().toString())) {
                    Toast.makeText(ElectricityActivity.this, "Please enter your meter number.", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(binding.etAmount.getText().toString())) {
                    Toast.makeText(ElectricityActivity.this, "Please enter amount.", Toast.LENGTH_SHORT).show();
                } else {
                    ActionSheetDialog.showDialog(ElectricityActivity.this, menuitems, new OnMenuItemClickListener() {
                        @Override
                        public void OnMenuItemClick(int position) {
                            switch (position) {
                                case 0:
                                    isManual = true;
                                    requestForPriceTaxWs();
                                    break;
                                case 1:
                                    isManual = true;
                                    requestForPriceTaxWs();
                                    break;
                                case 2:
                                    isManual = true;
                                    requestForPriceTaxWs();
                                    break;
                                case 3:
                                    isManual = false;
                                    requestForPriceTaxWs();
                                    break;
                            }
                        }
                    });
                }
            }
        });
    }

    private void setupToolbar() {
        ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.btn_back);
        ab.setTitle("Electricity");
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

    private void requestForPriceTaxWs() {
        LoadingUtils.getInstance(this).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_PRICE_WITH_TAX);
        HashMap<String, String> params = new HashMap<>();
        params.put("operator", "electricity");
        params.put("amount", binding.etAmount.getText().toString());
        wsUtils.WSRequest(this, params, null, WSUtils.REQ_PRICE_WITH_TAX, this);
    }

    private void requestForPaymentWs(String type, String amount, String taxAmount) {
        LoadingUtils.getInstance(ElectricityActivity.this).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_PAYMENT);
        HashMap<String, String> params = new HashMap<>();
        params.put(Constant.AMOUNT, amount);
        params.put(Constant.TYPE, "electricity");
        params.put(Constant.METER_NUMBER, binding.etMeterNo.getText().toString());
        params.put(Constant.DEVICE, "mobile");
        params.put(Constant.USER_ID, UserDetail.getInstance(this).getUserId());
        params.put(Constant.PAYMENT_TYPE, type);
        params.put(Constant.USER_EMAIL, UserDetail.getInstance(this).getUserName());
        params.put("tax_amount", taxAmount);
        wsUtils.WSRequest(this, params, null, WSUtils.REQ_PAYMENT, this);
    }

    @Override
    public void successResponse(int requestCode, WSResponse response) {
        switch (requestCode) {
            case WSUtils.REQ_PAYMENT:
                parsePaymentWs((PaymentResponse) response);
                LoadingUtils.getInstance(ElectricityActivity.this).hideLoading();
                break;
            case WSUtils.REQ_PRICE_WITH_TAX:
                parseSuccessWs((PriceWithTax) response);
                break;
            default:
                break;
        }
    }

    private void parseSuccessWs(PriceWithTax response) {
        if (response != null && response.getStatus() == 200) {
            PriceWithTax.AmountList amountList = response.getAmountList();
            openTaxDialog(amountList.getAmount(), amountList.getTaxAmount());
        } else {
            Toast.makeText(this, "Invalid data.", Toast.LENGTH_SHORT).show();
        }
        LoadingUtils.getInstance(this).hideLoading();
    }

    private void parsePaymentWs(PaymentResponse response) {
        if (response != null && response.getStatus() == 200) {
            dialog.dismiss();
            if (!isManual) {
                String url = response.getUrl();
                Intent intent = new Intent(ElectricityActivity.this, WebActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            } else {
                Intent intent = new Intent(ElectricityActivity.this, ManualPaymentActivity.class);
                intent.putExtra("accountNo", response.getAccountNumber());
                intent.putExtra("orderId", response.getOrderId() + "");
                intent.putExtra("label", response.getLabel());
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, response != null ? response.getMessage() : "", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void errorResponse(int requestCode, Throwable t) {
        Toast.makeText(ElectricityActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noInternetConnection(int requestCode) {
        Toast.makeText(ElectricityActivity.this, R.string.internet_not_available, Toast.LENGTH_SHORT).show();
    }

    private void openTaxDialog(final String amount, final String tax) {
        dialog = new Dialog(this, R.style.AlertDialogCustom);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_basic_info);
        dialog.getWindow().getAttributes().windowAnimations = R.style.AlertDialogCustom;
        WindowManager.LayoutParams mWindowLayoutParams = new WindowManager.LayoutParams();
        Window mWindow = dialog.getWindow();
        mWindowLayoutParams.copyFrom(mWindow.getAttributes());
        mWindowLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mWindowLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        mWindow.setAttributes(mWindowLayoutParams);
        mWindow.setBackgroundDrawableResource(android.R.color.transparent);

        CustomTextView tvName = dialog.findViewById(R.id.tvName);
        CustomTextView tvEmail = dialog.findViewById(R.id.tvEmail);

        CustomTextView tvMoNumber = dialog.findViewById(R.id.tvMoNumber);
        CustomTextView tvOperator = dialog.findViewById(R.id.tvOperator);
        CustomTextView tvAmount = dialog.findViewById(R.id.tvAmount);
        CustomTextView tvTax = dialog.findViewById(R.id.tvTax);

        CustomTextView tvContinue = dialog.findViewById(R.id.tvContinue);

        tvName.setText(UserDetail.getInstance(this).getFullname());
        tvEmail.setText(UserDetail.getInstance(this).getUserName());
        tvMoNumber.setText(UserDetail.getInstance(this).getMobile());
        tvOperator.setText("electricity");

        tvAmount.setText(amount);
        tvTax.setText(tax);

        tvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isManual) {
                    requestForPaymentWs("manual", amount, tax);
                } else {
                    requestForPaymentWs("", amount, tax);
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
