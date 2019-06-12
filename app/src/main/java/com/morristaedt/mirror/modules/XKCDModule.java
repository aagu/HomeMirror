package com.morristaedt.mirror.modules;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.morristaedt.mirror.configuration.ConfigurationSettings;
import com.morristaedt.mirror.requests.XKCDRequest;
import com.morristaedt.mirror.requests.XKCDResponse;

import java.util.Calendar;

import retrofit2.Retrofit;

/**
 * Created by HannahMitt on 8/22/15.
 */
public class XKCDModule {

    public interface XKCDListener {
        void onNewXKCDToday(String url);
    }

    /**
     * Fetch the the latest xkcd comic, but only show it if its new today
     *
     * @param listener
     */
    public static void getXKCDForToday(final XKCDListener listener) {
        new AsyncTask<Void, Void, XKCDResponse>() {

            @Override
            protected XKCDResponse doInBackground(Void... params) {
                Retrofit restAdapter = new Retrofit.Builder()
                        .baseUrl("http://xkcd.com")
                        .build();

                XKCDRequest service = restAdapter.create(XKCDRequest.class);
                return service.getLatestXKCD();
            }

            @Override
            protected void onPostExecute(@Nullable XKCDResponse xkcdResponse) {
                if (xkcdResponse != null && !TextUtils.isEmpty(xkcdResponse.img)) {
                    if (ConfigurationSettings.isDemoMode() || isTodaysXKCD(xkcdResponse)) {
                        listener.onNewXKCDToday(xkcdResponse.img);
                        return;
                    }
                }
                listener.onNewXKCDToday(null);
            }

            private boolean isTodaysXKCD(@NonNull XKCDResponse xkcdResponse) {
                Calendar today = Calendar.getInstance();
                return xkcdResponse.day == today.get(Calendar.DAY_OF_MONTH) && xkcdResponse.month == (today.get(Calendar.MONTH) + 1) && xkcdResponse.year == today.get(Calendar.YEAR);
            }
        }.execute();

    }
}
