package com.morristaedt.mirror.modules;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;

import com.morristaedt.mirror.utils.LangUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by HannahMitt on 8/23/15.
 */
public class DayModule {

    public static Spanned getDay(Context context) {
        if (LangUtil.isZh(context)) {
            //中文
            SimpleDateFormat formatDayOfMonth = new SimpleDateFormat("EEEE", Locale.CHINA);
            Calendar now = Calendar.getInstance();
            int month = now.get(Calendar.MONTH) + 1;
            int dayOfMonth = now.get(Calendar.DAY_OF_MONTH);
            return Html.fromHtml(month + "月" + dayOfMonth + "日" + " " +
                    formatDayOfMonth.format(now.getTime()));
        } else {
            //其他语言
            SimpleDateFormat formatDayOfMonth = new SimpleDateFormat("EEEE", Locale.US);
            Calendar now = Calendar.getInstance();
            int dayOfMonth = now.get(Calendar.DAY_OF_MONTH);
            return Html.fromHtml(formatDayOfMonth.format(now.getTime()) + " the " +
                    dayOfMonth + "<sup><small>" + getDayOfMonthSuffix(dayOfMonth) + "</small></sup>");
        }
    }

    private static String getDayOfMonthSuffix(final int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }
}
