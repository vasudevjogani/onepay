package com.example.hb.invest.invest.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.FragmentHomeBinding;
import com.example.hb.invest.invest.activities.CouncilBillsActivity;
import com.example.hb.invest.invest.activities.DomesticTaxesActivity;
import com.example.hb.invest.invest.activities.ElectricityActivity;
import com.example.hb.invest.invest.activities.MobileActivity;
import com.example.hb.invest.invest.activities.PayTvActivity;
import com.example.hb.invest.invest.activities.WaterActivity;
import com.example.hb.invest.invest.adapters.HomeAdapter;
import com.example.hb.invest.invest.models.Home;
import com.example.hb.invest.invest.models.HomeResponse;
import com.example.hb.invest.invest.models.StaticPageResponse;
import com.example.hb.invest.invest.utiles.views.IParser;
import com.example.hb.invest.invest.utiles.views.LoadingUtils;
import com.example.hb.invest.invest.utiles.views.WSFactory;
import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.example.hb.invest.invest.utiles.views.WSUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements IParser<WSResponse> {

    FragmentHomeBinding binding;
    private ArrayList<Home> homeList = new ArrayList<>();
    private ArrayList<HomeResponse.Mobile> mobileList = new ArrayList<>();
    private ArrayList<HomeResponse.Paytv> paytvList = new ArrayList<>();
    private ArrayList<HomeResponse.WaterBill> waterBillList = new ArrayList<>();
    private ArrayList<HomeResponse.DomesticTax> domesticTaxList = new ArrayList<>();
    private ArrayList<HomeResponse.CouncilBill> councilBillList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvHome.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        requestForHomeWs();
    }

    private void requestForHomeWs() {
        LoadingUtils.getInstance(getActivity()).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_HOME);
        HashMap<String, String> params = new HashMap<>();
        wsUtils.WSRequest(getActivity(), params, null, WSUtils.REQ_HOME, this);
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
            if (response != null && response.getStatus()==200) {
                if (response.getUtilityList().getMobile() != null && response.getUtilityList().getMobile().size() > 0) {
                    homeList.add(new Home("Mobile", R.drawable.mobile));
                    mobileList.addAll(response.getUtilityList().getMobile());
                }

                if (response.getUtilityList().getPaytv() != null && response.getUtilityList().getPaytv().size() > 0) {
                    homeList.add(new Home("Pay TV", R.drawable.bundle));
                    paytvList.addAll(response.getUtilityList().getPaytv());
                }

                if (response.getUtilityList().getElectricity() != null && response.getUtilityList().getElectricity().size() > 0) {
                    homeList.add(new Home("Electricity", R.drawable.elecricity));
                }

                if (response.getUtilityList().getCouncilBills() != null && response.getUtilityList().getCouncilBills().size() > 0) {
                    homeList.add(new Home("Council Bills", R.drawable.tax_white));
                    councilBillList.addAll(response.getUtilityList().getCouncilBills());
                }
                if (response.getUtilityList().getDomesticTax() != null && response.getUtilityList().getDomesticTax().size() > 0) {
                    homeList.add(new Home("Domestic Taxes", R.drawable.elecricity));
                    domesticTaxList.addAll(response.getUtilityList().getDomesticTax());
                }

                if (response.getUtilityList().getWaterBills() != null && response.getUtilityList().getWaterBills().size() > 0) {
                    homeList.add(new Home("Water", R.drawable.water));
                    waterBillList.addAll(response.getUtilityList().getWaterBills());
                }

                HomeAdapter adapter = new HomeAdapter(getActivity(), homeList, HomeFragment.this);
                binding.rvHome.setAdapter(adapter);
            }else {
                Toast.makeText(getActivity(), response != null ? response.getMessage() : "", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadingUtils.getInstance(getActivity()).hideLoading();
    }

    @Override
    public void errorResponse(int requestCode, Throwable t) {
        LoadingUtils.getInstance(getActivity()).hideLoading();
        Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noInternetConnection(int requestCode) {
        LoadingUtils.getInstance(getActivity()).hideLoading();
        Toast.makeText(getActivity(), R.string.internet_not_available, Toast.LENGTH_SHORT).show();
    }


    public void navigateToActivity(String type) {
        if (type.equalsIgnoreCase("Mobile")) {
            Intent intent1 = new Intent(getActivity(), MobileActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("Mobile", mobileList);
            intent1.putExtra("BUNDLE", args);
            getActivity().startActivity(intent1);
        } else if (type.equalsIgnoreCase("Water")) {
            Intent intent = new Intent(getActivity(), WaterActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("Water", waterBillList);
            intent.putExtra("BUNDLE", args);
            getActivity().startActivity(intent);
        } else if (type.equalsIgnoreCase("Pay TV")) {
            Intent intent1 = new Intent(getActivity(), PayTvActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("TV", paytvList);
            intent1.putExtra("BUNDLE", args);
            getActivity().startActivity(intent1);
        } else if (type.equalsIgnoreCase("Domestic Taxes")) {
            Intent intent1 = new Intent(getActivity(), DomesticTaxesActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("Taxes", domesticTaxList);
            intent1.putExtra("BUNDLE", args);
            getActivity().startActivity(intent1);
        } else if (type.equalsIgnoreCase("Council Bills")) {
            Intent intent1 = new Intent(getActivity(), CouncilBillsActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("Bills", councilBillList);
            intent1.putExtra("BUNDLE", args);
            getActivity().startActivity(intent1);
        } else if (type.equalsIgnoreCase("Electricity")) {
            Intent intent1 = new Intent(getActivity(), ElectricityActivity.class);
            getActivity().startActivity(intent1);
        }
    }
}
