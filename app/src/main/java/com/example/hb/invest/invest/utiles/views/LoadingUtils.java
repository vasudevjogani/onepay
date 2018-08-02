package com.example.hb.invest.invest.utiles.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.hb.invest.R;

/**
 */
public class LoadingUtils {
    private static LoadingUtils loadingUtils;
    private Dialog progressDialog;
    public static Context context;

    private LoadingUtils() {
    }

    public static LoadingUtils getInstance(Context mContext) {
        context = mContext;
        if (loadingUtils == null) {
            loadingUtils = new LoadingUtils();
        }
        return loadingUtils;
    }

    public void showLoading() {
        try {
            if (progressDialog == null || !progressDialog.isShowing()) {
                progressDialog = new Dialog(context, android.R.style.Theme_Light);
                progressDialog.setCancelable(true);
                progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View progressBar = inflater.inflate(R.layout.progressbar, null);
                progressBar.setVisibility(View.VISIBLE);
                progressDialog.setContentView(progressBar);
                Window window = progressDialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                progressDialog.show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideLoading() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isShowing() {
        return (progressDialog != null && progressDialog.isShowing());
    }
}
