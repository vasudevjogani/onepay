package com.example.hb.invest.invest.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.FragmentSupportBinding;
import com.example.hb.invest.invest.activities.MainActivity;
import com.example.hb.invest.invest.models.CommonResponse;
import com.example.hb.invest.invest.models.LoginResponse;
import com.example.hb.invest.invest.utiles.views.Constant;
import com.example.hb.invest.invest.utiles.views.IParser;
import com.example.hb.invest.invest.utiles.views.LoadingUtils;
import com.example.hb.invest.invest.utiles.views.WSFactory;
import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.example.hb.invest.invest.utiles.views.WSUtils;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupportFragment extends Fragment implements IParser<WSResponse> {

    FragmentSupportBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_support, container, false);
        initComponent();
        return binding.getRoot();
    }

    private void initComponent() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.support_spinner));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(dataAdapter);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(getActivity(), R.color.light_gray));
                } else {
                    ((TextView) view).setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.tvCreateTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestForSupportWs();
            }
        });
    }

    private void requestForSupportWs() {
        if (TextUtils.isEmpty(binding.etName.getText().toString())) {
            Toast.makeText(getActivity(), "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.etEmail.getText().toString())) {
            Toast.makeText(getActivity(), "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.etNumber.getText().toString())) {
            Toast.makeText(getActivity(), "Please enter your numer", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.etDetail.getText().toString())) {
            Toast.makeText(getActivity(), "Please enter description", Toast.LENGTH_SHORT).show();
            return;
        }

        LoadingUtils.getInstance(getActivity()).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_SUPPORT);
        HashMap<String, String> params = new HashMap<>();
        params.put(Constant.TICKET_TYPE, binding.spinner.getSelectedItem().toString());
        params.put(Constant.NAME, binding.etName.getText().toString());
        params.put(Constant.EMAIL, binding.etEmail.getText().toString());
        params.put(Constant.MOBILE_NUMBER, binding.etNumber.getText().toString());
        params.put(Constant.DESCRIPTION, binding.etDetail.getText().toString());
        wsUtils.WSRequest(getActivity(), params, null, WSUtils.REQ_SUPPORT, this);
    }

    @Override
    public void successResponse(int requestCode, WSResponse response) {
        switch (requestCode) {
            case WSUtils.REQ_SUPPORT:
                parseSupportWs((CommonResponse) response);
                break;
            default:
                break;
        }
    }

    private void parseSupportWs(CommonResponse response) {
        if (response != null && response.getStatus() == 200) {
            Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            if (response != null) {
                Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        LoadingUtils.getInstance(getActivity()).hideLoading();
    }

    @Override
    public void errorResponse(int requestCode, Throwable t) {
        LoadingUtils.getInstance(getActivity()).hideLoading();
//        Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noInternetConnection(int requestCode) {
        LoadingUtils.getInstance(getActivity()).hideLoading();
        Toast.makeText(getActivity(), R.string.internet_not_available, Toast.LENGTH_SHORT).show();
    }

}
