package utils;

import android.content.Context;

import java.util.ArrayList;

import utils.sp.SharedPreferencesHelper;
import utils.sp.SharedPrefsName;

/**
 * Created by zpw on 2016/9/14.
 */

public class Utils {


    //<!-- ui util start -->
    /**
     * px-->dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * dp-->px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    //<!-- ui util end -->

    public static SharedPreferencesHelper getSpHelper(Context context) {
        return SharedPreferencesHelper.getInstance(context, SharedPrefsName.z_SP);
    }

    public static boolean isListHavaData(ArrayList list){
        return  list != null && !list.isEmpty();
    }


}
