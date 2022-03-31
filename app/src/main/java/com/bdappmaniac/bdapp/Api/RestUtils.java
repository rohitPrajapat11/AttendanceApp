package com.bdappmaniac.bdapp.Api;

public class RestUtils {

    public static String getEndPoint(String URL_Type) {
        String baseUrl = "http://3.142.235.1/login_system_api/";
//        switch (URL_Type) {
//            case Constants.GOOD_API_DEFAULT:
//                baseUrl = BuildConfig.DEBUG ? BuildConfig.STAGING_END_POINT : BuildConfig.PRODUCTION_END_POINT;
//                break;
//            case Constants.GOOD_API_CO:
//                baseUrl = Constants.GOOD_API_CO;
//                break;
//            case Constants.GOOD_API_PH:
//                baseUrl = Constants.GOOD_API_PH;
//                break;
//        }
        return baseUrl;
    }
}
