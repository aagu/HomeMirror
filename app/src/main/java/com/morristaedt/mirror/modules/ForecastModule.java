package com.morristaedt.mirror.modules;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.morristaedt.mirror.requests.ForecastRequest;
import com.morristaedt.mirror.requests.ForecastResponse;
import com.morristaedt.mirror.requests.OpenWeatherRequest;
import com.morristaedt.mirror.requests.OpenWeatherResponse;
import com.morristaedt.mirror.utils.HttpUtil;

import java.io.IOException;
import java.util.Locale;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HannahMitt on 8/22/15.
 * Updated by aagu on 6/12/2019
 */
public class ForecastModule {

    public interface ForecastListener {
        void onWeatherToday(String weatherToday);

        void onShouldBike(boolean showToday, boolean shouldBike);
    }

    /**
     * @param apiKey   The api key for the forecast.io weather api
     * @param units
     * @param lat
     * @param lon
     * @param listener
     */
    public static void getForecastIOHourlyForecast(final String apiKey, final String units, final String lat, final String lon, final ForecastListener listener) {
        new AsyncTask<Void, Void, ForecastResponse>() {

            @Override
            protected ForecastResponse doInBackground(Void... params) {
                OkHttpClient okHttpClient = HttpUtil.createOkHttpClient();
                Retrofit restAdapter = new Retrofit.Builder()
                        .baseUrl("https://free-api.heweather.net")
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ForecastRequest service = restAdapter.create(ForecastRequest.class);
                String excludes = "minutely,daily,flags";

                Call<ForecastResponse> call = service.getHourlyForecast("Hangzhou", apiKey, units);
                try {
                    Response<ForecastResponse> response = call.execute();
                    return response.body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(ForecastResponse forecastResponse) {
                if (forecastResponse != null) {
                    String tmp_unit = "℃";
                    if (units.equals(ForecastRequest.UNITS_I)) tmp_unit = "℉";
                    if (forecastResponse.heWeather6.get(0).now != null) {
                        listener.onWeatherToday(forecastResponse.heWeather6.get(0).now.tmp +
                                tmp_unit + " " + forecastResponse.heWeather6.get(0).now.condTxt);
                    }

                    /*if (forecastResponse.hourly != null && forecastResponse.hourly.data != null && (ConfigurationSettings.isDemoMode() || WeekUtil.isWeekdayBeforeFive())) {
                        listener.onShouldBike(true, shouldBikeToday(forecastResponse.hourly.data));
                    } else {
                        listener.onShouldBike(false, true);
                    }*/
                }
            }

            /*private boolean shouldBikeToday(List<ForecastResponse.Hour> hours) {
                int dayOfMonthToday = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

                for (ForecastResponse.Hour hour : hours) {
                    Calendar hourCalendar = hour.getCalendar();

                    // Only check hourly forecast for today
                    if (hourCalendar.get(Calendar.DAY_OF_MONTH) == dayOfMonthToday) {
                        int hourOfDay = hourCalendar.get(Calendar.HOUR_OF_DAY);
                        if (hourOfDay >= 7 && hourOfDay <= 11) {
                            if (hour.precipProbability >= 0.3) {
                                return false;
                            }
                        } else if (hourOfDay >= 17 && hourOfDay <= 19) {
                            if (hour.precipProbability >= 0.3) {
                                return false;
                            }
                        }
                    }
                }

                return true;
            }*/
        }.execute();

    }

    /**
     * @param apiKey   The api key for the openweather api
     * @param units
     * @param lat
     * @param lon
     * @param listener
     */
    public static void getOpenWeatherForecast(final String apiKey, final String units, final String lat, final String lon, final ForecastListener listener) {
        new AsyncTask<Void, Void, OpenWeatherResponse>() {

            @Override
            protected OpenWeatherResponse doInBackground(Void... params) {
                Retrofit restAdapter = new Retrofit.Builder()
                        .baseUrl("http://api.openweathermap.org")
                        .build();

                OpenWeatherRequest service = restAdapter.create(OpenWeatherRequest.class);

                return service.getCurrentForecast(apiKey, lat, lon, getOpenWeatherUnits(units), Locale.getDefault().getLanguage());
            }

            @Override
            protected void onPostExecute(OpenWeatherResponse response) {
                if (response != null) {
                    if (response.main != null) {
                        listener.onWeatherToday(response.main.getDisplayTemperature() + " " + response.getWeatherDescription());
                    }
                }
            }

        }.execute();

    }

    @NonNull
    private static String getOpenWeatherUnits(String units) {
        if (units.equalsIgnoreCase(ForecastRequest.UNITS_M)) {
            return OpenWeatherRequest.UNITS_METRIC;
        } else if (units.equalsIgnoreCase(ForecastRequest.UNITS_I)) {
            return OpenWeatherRequest.UNITS_IMPERIAL;
        }
        return units;
    }
}
