package fr.unice.polytech.tobeortohave.fragment.home.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.unice.polytech.tobeortohave.R;

/**
 * @author Alexandre Clement
 *         Created the 10/05/2017.
 */

public class WeatherFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.weather_fragment, container, false);
        final WeatherOverview overview = (WeatherOverview) getChildFragmentManager().findFragmentById(R.id.overview);
        overview.addWeatherServiceListener(new WeatherListener());
        return view;
    }

    private class WeatherListener implements WeatherServiceListener
    {
        @Override
        public void success(Weather weather)
        {
            Forecast[] forecasts = weather.getForecast();
            for (int day = 0; day < Math.min(5, forecasts.length); day++)
            {
                WeatherConditionFragment fragment = (WeatherConditionFragment) getChildFragmentManager().getFragments().get(day + 1);
                getChildFragmentManager().getFragments().get(day + 1);
                if (fragment != null)
                    fragment.load(forecasts[day]);
            }
        }

        @Override
        public void failure(Exception exception)
        {

        }
    }
}
