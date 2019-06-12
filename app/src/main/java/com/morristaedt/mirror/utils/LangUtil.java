package com.morristaedt.mirror.utils;

import android.content.Context;

import java.util.Locale;

public class LangUtil {

    public static boolean isZh(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        return language.endsWith("zh");
    }
}
