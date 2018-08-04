package com.example.hb.invest.invest.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.FragmentFeedbackBinding;
import com.example.hb.invest.invest.models.CommonResponse;
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
public class FeedbackFragment extends Fragment implements IParser<WSResponse> {

    FragmentFeedbackBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feedback, container, false);
        initComponent();
        return binding.getRoot();
    }

    private void initComponent() {

        binding.tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestForFeedbackWs();
            }
        });
    }

    private void requestForFeedbackWs() {
        if (TextUtils.isEmpty(binding.etFirstName.getText().toString())) {
            Toast.makeText(getActivity(), "Please enter your firstname", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.etLastName.getText().toString())) {
            Toast.makeText(getActivity(), "Please enter your lastname", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.etEmail.getText().toString())) {
            Toast.makeText(getActivity(), "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.etNumber.getText().toString())) {
            Toast.makeText(getActivity(), "Please enter your mobile number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(binding.etDetail.getText().toString())) {
            Toast.makeText(getActivity(), "Please enter description", Toast.LENGTH_SHORT).show();
            return;
        }

        LoadingUtils.getInstance(getActivity()).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_FEEDBACK);
        HashMap<String, String> params = new HashMap<>();
        params.put(Constant.FIRST_NAME, binding.etFirstName.getText().toString());
        params.put(Constant.LAST_NAME, binding.etLastName.getText().toString());
        params.put(Constant.EMAIL, binding.etEmail.getText().toString());
        params.put(Constant.MOBILE_NUMBER, binding.etNumber.getText().toString());
        params.put(Constant.FEEDBACK_MESSAGE, binding.etDetail.getText().toString());
        wsUtils.WSRequest(getActivity(), params, null, WSUtils.REQ_FEEDBACK, this);
    }

    @Override
    public void successResponse(int requestCode, WSResponse response) {
        switch (requestCode) {
            case WSUtils.REQ_FEEDBACK:
                parseFeedbackWs((CommonResponse) response);
                break;
            default:
                break;
        }
    }

    private void parseFeedbackWs(CommonResponse response) {
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
        Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noInternetConnection(int requestCode) {
        LoadingUtils.getInstance(getActivity()).hideLoading();
        Toast.makeText(getActivity(), R.string.internet_not_available, Toast.LENGTH_SHORT).show();
    }
}
