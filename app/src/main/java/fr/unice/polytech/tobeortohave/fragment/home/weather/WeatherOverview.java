package fr.unice.polytech.tobeortohave.fragment.home.weather;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.unice.polytech.tobeortohave.R;

/**
 * @author Alexandre Clement
 *         Created the 12/05/2017.
 */

public class WeatherOverview extends Fragment
{
    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private ProgressDialog loadingDialog;
    private List<WeatherServiceListener> weatherServiceListener;
    private WeatherCache weatherCache;

    public WeatherOverview()
    {
        weatherServiceListener = new ArrayList<>();
        weatherServiceListener.add(new WeatherListener());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.weather_overview, container, false);
        weatherIconImageView = (ImageView) view.findViewById(R.id.weatherIconImageView);
        temperatureTextView = (TextView) view.findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView) view.findViewById(R.id.conditionTextView);
        locationTextView = (TextView) view.findViewById(R.id.locationTextView);
        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        loadingDialog = new ProgressDialog(getContext());
        loadingDialog.setMessage(getString(R.string.loading));
        loadingDialog.show();

        weatherCache = new WeatherCache(getContext());

        if (weatherCache.load().getDate().before(new Date(System.currentTimeMillis() - 3600 * 1000)))
            query();
        else
        {
            for (WeatherServiceListener serviceListener : weatherServiceListener)
            {
                serviceListener.success(weatherCache.load());
            }
        }
    }

    private void printWeather(Weather weather)
    {
        int weatherIconImageResource = getResources().getIdentifier("icon_" + weather.getCode(), "drawable", getActivity().getPackageName());

        weatherIconImageView.setImageResource(weatherIconImageResource);
        temperatureTextView.setText(getString(R.string.temperature_format, weather.getTemperature(), getString(R.string.temp_unit)));
        conditionTextView.setText(weather.getCondition());
        locationTextView.setText(weather.getLocation());
    }

    public void addWeatherServiceListener(WeatherServiceListener weatherServiceListener)
    {
        this.weatherServiceListener.add(weatherServiceListener);
    }

    private void query()
    {
        final AsyncTask<String, Void, Weather> query = new Query(weatherServiceListener).execute("antibes");
        loadingDialog.setOnCancelListener(new CancelListener(query));
    }

    private class CancelListener implements DialogInterface.OnCancelListener
    {
        private final AsyncTask<String, Void, Weather> query;

        private CancelListener(AsyncTask<String, Void, Weather> query)
        {
            this.query = query;
        }

        @Override
        public void onCancel(DialogInterface dialog)
        {
            query.cancel(true);
        }
    }

    private class WeatherListener implements WeatherServiceListener
    {
        @Override
        public void success(Weather weather)
        {
            loadingDialog.hide();

            weatherCache.save(weather);
            printWeather(weatherCache.load());
        }

        @Override
        public void failure(Exception exception)
        {
            Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            query();
        }
    }
}
