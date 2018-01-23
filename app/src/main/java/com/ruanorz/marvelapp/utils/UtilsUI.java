package com.ruanorz.marvelapp.utils;

import android.content.res.Resources;

/**
 * Created by ruano on 23/01/2018.
 */

public class UtilsUI {

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

}
