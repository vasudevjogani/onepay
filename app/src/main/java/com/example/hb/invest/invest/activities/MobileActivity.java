package com.example.hb.invest.invest.activities;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityMobileBinding;
import com.example.hb.invest.invest.models.HomeResponse;
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

public class MobileActivity extends AppCompatActivity implements IParser<WSResponse> {

    ActivityMobileBinding binding;
    private Toolbar actionBarToolbar;
    private ActionBar ab;
    private ArrayList<HomeResponse.Mobile> mobileArrayList = new ArrayList<>();
    private ArrayList<String> operatorList = new ArrayList<>();
    private Dialog dialog;
    private String paymentType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mobile);
        setupToolbar();
        initComponent();
    }

    private void setupToolbar() {
        ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.btn_back);
        ab.setTitle("Mobile");
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
        Intent intent = getIntent();
        if (intent != null) {

            Bundle args = intent.getBundleExtra("BUNDLE");
            mobileArrayList = (ArrayList<HomeResponse.Mobile>) args.getSerializable("Mobile");

            for (HomeResponse.Mobile mobile : mobileArrayList) {
                operatorList.add(mobile.getUtilityName());
            }
            operatorList.add(0, "Select operator");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, operatorList);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinner1.setAdapter(dataAdapter);
            binding.spinner2.setAdapter(dataAdapter);

            binding.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        ((TextView) view).setTextColor(ContextCompat.getColor(MobileActivity.this, R.color.light_gray));
                    } else {
                        ((TextView) view).setTextColor(ContextCompat.getColor(MobileActivity.this, R.color.colorPrimaryDark));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            binding.spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        ((TextView) view).setTextColor(ContextCompat.getColor(MobileActivity.this, R.color.light_gray));
                    } else {
                        ((TextView) view).setTextColor(ContextCompat.getColor(MobileActivity.this, R.color.colorPrimaryDark));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i) {
                        case R.id.radioPre:
                            binding.llPostpaid.setVisibility(View.GONE);
                            binding.llPrepaid.setVisibility(View.VISIBLE);
                            break;

                        case R.id.radioPost:
                            binding.llPostpaid.setVisibility(View.VISIBLE);
                            binding.llPrepaid.setVisibility(View.GONE);
                            break;
                    }
                }
            });

            binding.tvPayBill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<Menuitem> menuitems = new ArrayList<>();
                    menuitems.add(new Menuitem("Airtel Money", R.drawable.airtel_logo));
                    menuitems.add(new Menuitem("MTN Mobile Money", R.drawable.mtn_logo));
                    menuitems.add(new Menuitem("Zamtel Kwacha", R.drawable.zamtel_logo));
                    menuitems.add(new Menuitem("Card Payment", R.drawable.mastercard));

                    if (TextUtils.isEmpty(binding.etNumber.toString())) {
                        Toast.makeText(MobileActivity.this, "Please enter valid mobile number.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (binding.etNumber.length() != 12) {
                        Toast.makeText(MobileActivity.this, "Mobile number should be 12 digit.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(binding.etAmount.toString())) {
                        Toast.makeText(MobileActivity.this, "Please enter amount.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!binding.spinner1.getSelectedItem().equals("Select operator") || !binding.spinner2.getSelectedItem().equals("Select operator")) {
                        ActionSheetDialog.showDialog(MobileActivity.this, menuitems, new OnMenuItemClickListener() {
                            @Override
                            public void OnMenuItemClick(int position) {
                                switch (position) {
                                    case 0:
                                        paymentType = "manual";
                                        requestForPriceTaxWs();
                                        break;
                                    case 1:
                                        paymentType = "mtn_money";
                                        requestForPriceTaxWs();
                                        break;
                                    case 2:
                                        paymentType = "manual";
                                        requestForPriceTaxWs();
                                        break;
                                    case 3:
                                        paymentType = "cards";
                                        requestForPriceTaxWs();
                                        break;
                                }
                            }
                        });
                    } else {
                        Toast.makeText(MobileActivity.this, "Please select operator.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void requestForPriceTaxWs() {
        LoadingUtils.getInstance(this).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_PRICE_WITH_TAX);
        HashMap<String, String> params = new HashMap<>();
        params.put("operator", binding.spinner1.getSelectedItem().toString());
        params.put("amount", binding.etAmount.getText().toString());
        wsUtils.WSRequest(this, params, null, WSUtils.REQ_PRICE_WITH_TAX, this);
    }

    private void requestForPaymentWs(String amount, String taxAmount) {
        LoadingUtils.getInstance(MobileActivity.this).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_PAYMENT);
        HashMap<String, String> params = new HashMap<>();
        params.put(Constant.AMOUNT, amount);
        params.put(Constant.TYPE, "mobile");
        params.put(Constant.MOBILE_NUMBER, binding.etNumber.getText().toString());
        params.put(Constant.OPERATOR, binding.spinner1.getSelectedItem().toString());
        params.put(Constant.DEVICE, "mobile");
        params.put(Constant.USER_ID, UserDetail.getInstance(this).getUserId());
        params.put(Constant.PAYMENT_TYPE, paymentType);
        params.put(Constant.USER_EMAIL, UserDetail.getInstance(this).getUserName());
        params.put("tax_amount", taxAmount);
        wsUtils.WSRequest(this, params, null, WSUtils.REQ_PAYMENT, this);
    }

    @Override
    public void successResponse(int requestCode, WSResponse response) {
        switch (requestCode) {
            case WSUtils.REQ_PAYMENT:
                parsePaymentWs((PaymentResponse) response);
                LoadingUtils.getInstance(MobileActivity.this).hideLoading();
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
            openTaxDialog(amountList);
        } else {
            Toast.makeText(this, "Invalid data.", Toast.LENGTH_SHORT).show();
        }
        LoadingUtils.getInstance(this).hideLoading();
    }

    private void parsePaymentWs(PaymentResponse response) {
        if (response != null && response.getStatus() == 200) {
            dialog.dismiss();
            if (paymentType.equalsIgnoreCase("cards")) {
                String url = response.getUrl();
                Intent intent = new Intent(MobileActivity.this, WebActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            } else if (paymentType.equalsIgnoreCase("manual")) {
                Intent intent = new Intent(MobileActivity.this, ManualPaymentActivity.class);
                intent.putExtra("accountNo", response.getAccountNumber());
                intent.putExtra("orderId", response.getOrderId() + "");
                intent.putExtra("label", response.getLabel());
                startActivity(intent);
            } else if (paymentType.equalsIgnoreCase("mtn_money")) {
                Intent intent = new Intent(MobileActivity.this, MTNPaymentActivity.class);
                intent.putExtra("orderId", response.getOrderId() + "");
                intent.putExtra("mobile", binding.etNumber.getText().toString());
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, response != null ? response.getMessage() : "", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void errorResponse(int requestCode, Throwable t) {
        Toast.makeText(MobileActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noInternetConnection(int requestCode) {
        Toast.makeText(MobileActivity.this, R.string.internet_not_available, Toast.LENGTH_SHORT).show();
    }

    private void openTaxDialog(final PriceWithTax.AmountList amountList) {
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
        CustomTextView tvDeductAmount = dialog.findViewById(R.id.tvDeductAmount);
        ImageView ivBack = dialog.findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        CustomTextView tvContinue = dialog.findViewById(R.id.tvContinue);

        tvName.setText(UserDetail.getInstance(this).getFullname());
        tvEmail.setText(UserDetail.getInstance(this).getUserName());
        tvMoNumber.setText(binding.etNumber.getText().toString());
        tvOperator.setText(binding.spinner1.getSelectedItem().toString());

        tvAmount.setText(amountList.getAmount());
        tvTax.setText(amountList.getTaxAmount());
        tvDeductAmount.setText(amountList.getTotalAmount());

        tvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestForPaymentWs(amountList.getTotalAmount(), amountList.getTaxAmount());
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
