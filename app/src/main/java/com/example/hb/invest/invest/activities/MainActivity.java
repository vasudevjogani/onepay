package com.example.hb.invest.invest.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityMainBinding;
import com.example.hb.invest.invest.fragments.FeedbackFragment;
import com.example.hb.invest.invest.fragments.HomeFragment;
import com.example.hb.invest.invest.fragments.OrderListFragment;
import com.example.hb.invest.invest.fragments.StaticPageFragment;
import com.example.hb.invest.invest.fragments.SupportFragment;
import com.example.hb.invest.invest.utiles.views.ResideMenu.ResideMenu;
import com.example.hb.invest.invest.utiles.views.ResideMenu.ResideMenuItem;
import com.example.hb.invest.invest.utiles.views.UserDetail;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    private ResideMenu resideMenu;
    private Toolbar actionBarToolbar;
    private ActionBar ab;

    private ResideMenuItem itemUser;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemOrderList;
    private ResideMenuItem itemAboutUs;
    private ResideMenuItem itemTerms;
    private ResideMenuItem itemPrivacy;
    private ResideMenuItem itemSupport;
//    private ResideMenuItem itemSitemap;
    private ResideMenuItem itemFaq;
    private ResideMenuItem itemFeedback;
    private ResideMenuItem itemLogout;
    private long backPressed = 0;
    private boolean isFromWeb=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        initComponents();
    }

    private void initComponents() {
        setupToolbar();
        setSlidingView();

        if (getIntent() != null) {
            isFromWeb = getIntent().getBooleanExtra("isFromWeb",false);
        }

        if (isFromWeb) {
            ab.setTitle("Your Orders");
            changeFragment(new OrderListFragment());
        } else {
            changeFragment(new HomeFragment());
        }
    }

    private void setupToolbar() {
        ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.btn_menu);
        ab.setTitle("Home");
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
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setSlidingView() {
        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setUse3D(true);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
//        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.8f);

        // create menu items;
        itemUser = new ResideMenuItem(this, false);
        itemHome = new ResideMenuItem(this, R.drawable.home, "Home");
        itemOrderList = new ResideMenuItem(this,R.drawable.order,"Orders");
        itemAboutUs = new ResideMenuItem(this, R.drawable.about, "About Us");
        itemSupport = new ResideMenuItem(this, R.drawable.icon_view, "Support");
//        itemSitemap = new ResideMenuItem(this, R.drawable.icon_view, "Sitemap");
        itemTerms = new ResideMenuItem(this, R.drawable.terms, "Terms");
        itemPrivacy = new ResideMenuItem(this, R.drawable.privacy, "Privacy");
        itemFaq = new ResideMenuItem(this, R.drawable.faq, "FAQ");
        itemFeedback = new ResideMenuItem(this, R.drawable.feedback, "Feedback");
        itemLogout = new ResideMenuItem(this, true);

        itemUser.setOnClickListener(this);
        itemHome.setOnClickListener(this);
        itemOrderList.setOnClickListener(this);
        itemAboutUs.setOnClickListener(this);
        itemSupport.setOnClickListener(this);
//        itemSitemap.setOnClickListener(this);
        itemTerms.setOnClickListener(this);
        itemPrivacy.setOnClickListener(this);
        itemFaq.setOnClickListener(this);
        itemFeedback.setOnClickListener(this);
        itemLogout.setOnClickListener(this);

        binding.ivWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddWalletBeneficiaryActivity.class));
            }
        });

        resideMenu.addMenuItem(itemUser, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemOrderList, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemAboutUs, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSupport, ResideMenu.DIRECTION_LEFT);
//        resideMenu.addMenuItem(itemSitemap, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemTerms, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemPrivacy, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemFaq, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemFeedback, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemLogout, ResideMenu.DIRECTION_LEFT);

    }

    @Override
    public void onClick(View view) {
        if (view == itemUser) {
           /* ab.setTitle("Profile");
            changeFragment(new HomeFragment());*/
        } else if (view == itemHome) {
            ab.setTitle("Home");
            changeFragment(new HomeFragment());
        } else if (view == itemOrderList) {
            ab.setTitle("My Orders");
            changeFragment(new OrderListFragment());
        }else if (view == itemAboutUs) {
            ab.setTitle("About Us");
            changeFragment(StaticPageFragment.getInstants("about"));
        } else if (view == itemSupport) {
            ab.setTitle("Support");
            changeFragment(new SupportFragment());
        } /*else if (view == itemSitemap) {
            ab.setTitle("SiteMap");
            changeFragment(StaticPageFragment.getInstants("sitemap"));
        }*/ else if (view == itemTerms) {
            ab.setTitle("Terms and Conditions");
            changeFragment(StaticPageFragment.getInstants("terms"));
        } else if (view == itemPrivacy) {
            ab.setTitle("Privacy Policy");
            changeFragment(StaticPageFragment.getInstants("privacy"));
        } else if (view == itemFaq) {
            ab.setTitle("FAQ");
            changeFragment(StaticPageFragment.getInstants("faq"));
        } else if (view == itemFeedback) {
            ab.setTitle("Feedback");
            changeFragment(new FeedbackFragment());
        } else if (view == itemLogout) {
            Intent intent = new Intent(this, LandingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            UserDetail.getInstance(MainActivity.this).clearAll();
            finish();
        }

        resideMenu.closeMenu();
    }

    private void changeFragment(Fragment targetFragment) {
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            if (resideMenu.isOpened()) {
                resideMenu.closeMenu();
            } else {
                fm.popBackStack();
            }
        } else {
            if (backPressed + 2000 > System.currentTimeMillis()) {
                finish();
            } else {
                Toast.makeText(this, getString(R.string.press_once_again_to_exit_), Toast.LENGTH_LONG).show();
            }
            backPressed = System.currentTimeMillis();
        }
    }
}
