package com.example.hb.invest.invest.utiles.views;

/**
 * Created by hb on 28-May-16.
 */
public class WSFactory {
    public enum WSType {
        WS_LOGIN,
        WS_REGISTRATION,
        WS_TERMS,
        WS_POLICY,
        WS_ABOUT,
        WS_FAQ,
        WS_SITEMAP,
        WS_PAYMENT,
        WS_SUPPORT,
        WS_FEEDBACK,
        WS_HOME,
        WS_VIEW_BILL,
        WS_ORDER_LIST,
        WS_EMAIL_VERIFY,
        WS_SUCCESS,
        WS_PRICE_WITH_TAX
    }


    public WSUtils getWsUtils(WSType wsType) {
        WSUtils wsUtils = null;

        switch (wsType) {
            case WS_LOGIN:
                wsUtils = new WSLogin();
                break;

            case WS_REGISTRATION:
                wsUtils = new WSRegistration();
                break;

            case WS_TERMS:
                wsUtils = new WSTerms();
                break;

            case WS_POLICY:
                wsUtils = new WSPolicy();
                break;

            case WS_ABOUT:
                wsUtils = new WSAbout();
                break;
            case WS_FAQ:
                wsUtils = new WSFaq();
                break;
            case WS_SITEMAP:
                wsUtils = new WSSitemap();
                break;
            case WS_PAYMENT:
                wsUtils = new WSPayment();
                break;
            case WS_SUPPORT:
                wsUtils = new WSSupport();
                break;
            case WS_FEEDBACK:
                wsUtils = new WSFeedback();
                break;
            case WS_HOME:
                wsUtils = new WSHome();
                break;
            case WS_VIEW_BILL:
                wsUtils = new WSViewBill();
                break;
            case WS_ORDER_LIST:
                wsUtils = new WSOrderList();
                break;

            case WS_EMAIL_VERIFY:
                wsUtils  = new WSEmailVerify();
                break;

            case WS_SUCCESS:
                wsUtils = new WSSuccess();
                break;

            case WS_PRICE_WITH_TAX:
                wsUtils = new WSPriceWithTax();
                break;
        }
        return wsUtils;

    }
}
