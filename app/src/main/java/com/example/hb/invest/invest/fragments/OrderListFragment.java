package com.example.hb.invest.invest.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.FragmentOrderListBinding;
import com.example.hb.invest.invest.adapters.OrderListAdapter;
import com.example.hb.invest.invest.models.Home;
import com.example.hb.invest.invest.models.OrderListResponse;
import com.example.hb.invest.invest.utiles.views.IParser;
import com.example.hb.invest.invest.utiles.views.LoadingUtils;
import com.example.hb.invest.invest.utiles.views.UserDetail;
import com.example.hb.invest.invest.utiles.views.WSFactory;
import com.example.hb.invest.invest.utiles.views.WSResponse;
import com.example.hb.invest.invest.utiles.views.WSUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderListFragment extends BaseFragment implements IParser<WSResponse> {

    FragmentOrderListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        requestForOrderWs();
    }

    private void requestForOrderWs() {
        LoadingUtils.getInstance(getActivity()).showLoading();
        WSFactory wsFactory = new WSFactory();
        WSUtils wsUtils = wsFactory.getWsUtils(WSFactory.WSType.WS_ORDER_LIST);
        HashMap<String, String> params = new HashMap<>();
        params.put("access_token", UserDetail.getInstance(getActivity()).getToken());
        wsUtils.WSRequest(getActivity(), params, null, WSUtils.REQ_ORDER_LIST, this);
    }

    @Override
    public void successResponse(int requestCode, WSResponse response) {
        switch (requestCode) {
            case WSUtils.REQ_ORDER_LIST:
                parseOrderListWs((OrderListResponse) response);
                break;
            default:
                break;
        }
    }

    private void parseOrderListWs(OrderListResponse response) {
        try {
            if (response != null && response.getStatus()==200) {
                List<OrderListResponse.OrderList> orderList = response.getOrderList();
                OrderListAdapter adapter = new OrderListAdapter(getActivity(), orderList);
                binding.rvOrder.setAdapter(adapter);
            } else {
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
}
