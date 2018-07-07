package com.example.hb.invest.invest.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.FragmentSettingBinding;
import com.example.hb.invest.invest.models.StaticPageResponse;
import com.example.hb.invest.invest.utiles.views.IParser;
import com.example.hb.invest.invest.utiles.views.LoadingUtils;
import com.example.hb.invest.invest.utiles.views.WSFactory;
import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.example.hb.invest.invest.utiles.views.WSUtils;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class StaticPageFragment extends Fragment implements IParser<WSResponse> {

    FragmentSettingBinding binding;

    public static StaticPageFragment getInstants(String type) {
        StaticPageFragment fragment = new StaticPageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        initComponent();
        return binding.getRoot();
    }

    private void initComponent() {
        WSFactory.WSType wsType = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            String pageType = bundle.getString("type");
            if (pageType.equalsIgnoreCase("terms")) {
                wsType = WSFactory.WSType.WS_TERMS;
            } else if (pageType.equalsIgnoreCase("privacy")) {
                wsType = WSFactory.WSType.WS_POLICY;
            } else if (pageType.equalsIgnoreCase("about")) {
                wsType = WSFactory.WSType.WS_ABOUT;
            } else if (pageType.equalsIgnoreCase("faq")) {
                wsType = WSFactory.WSType.WS_FAQ;
            } else if (pageType.equalsIgnoreCase("sitemap")) {
                wsType = WSFactory.WSType.WS_SITEMAP;
            }
            requestForLoginWs(wsType);
        }
    }

    private void requestForLoginWs(WSFactory.WSType wsType) {
        LoadingUtils.getInstance(getActivity()).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(wsType);
        HashMap<String, String> params = new HashMap<>();
        wsUtils.WSRequest(getActivity(), params, null, WSUtils.REQ_STATIC_PAGE, this);
    }

    @Override
    public void successResponse(int requestCode, WSResponse response) {
        switch (requestCode) {
            case WSUtils.REQ_STATIC_PAGE:
                parseStaticPageWs((StaticPageResponse) response);
                break;
            default:
                break;
        }
    }

    private void parseStaticPageWs(StaticPageResponse response) {
        if (response != null) {
            String content = response.getHtml();
            binding.webview.loadData(content, "text/html", "UTF-8");
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
