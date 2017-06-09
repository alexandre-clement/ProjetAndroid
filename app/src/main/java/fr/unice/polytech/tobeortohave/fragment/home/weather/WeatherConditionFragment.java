package fr.unice.polytech.tobeortohave.fragment.home.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import fr.unice.polytech.tobeortohave.R;

/**
 * @author Alexandre Clement
 *         Created the 10/05/2017.
 */

public class WeatherConditionFragment extends Fragment
{
    private ImageView weatherIconImageView;
    private TextView dayLabelTextView;
    private TextView highTemperatureTextView;
    private TextView lowTemperatureTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_weather_condition, container, false);

        weatherIconImageView = (ImageView) view.findViewById(R.id.weatherIconImageView);
        dayLabelTextView = (TextView) view.findViewById(R.id.dayTextView);
        highTemperatureTextView = (TextView) view.findViewById(R.id.highTemperatureTextView);
        lowTemperatureTextView = (TextView) view.findViewById(R.id.lowTemperatureTextView);

        return view;
    }

    public void load(Forecast forecast)
    {
        int weatherIconImageResource = getResources().getIdentifier("icon_" + forecast.getCode(), "drawable", getActivity().getPackageName());

        weatherIconImageView.setImageResource(weatherIconImageResource);
        dayLabelTextView.setText(forecast.getDay());
        highTemperatureTextView.setText(getString(R.string.temperature_format, forecast.getHighTemperature(), getString(R.string.temp_unit)));
        lowTemperatureTextView.setText(getString(R.string.temperature_format, forecast.getLowTemperature(), getString(R.string.temp_unit)));
    }
}
