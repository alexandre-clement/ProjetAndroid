package fr.unice.polytech.tobeortohave.fragment.home.weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Alexandre Clement
 *         Created the 13/05/2017.
 */

public class Weather implements Serializable
{
    private String location;
    private int code;
    private Date date;
    private int temperature;
    private String condition;
    private Forecast[] forecast;

    public Weather()
    {
        date = new Date(0);
    }

    public void refresh(JSONObject jsonObject)
    {
        location = jsonObject.optJSONObject(Yahoo.LOCATION.toString()).optString(Yahoo.CITY.toString());
        JSONObject item = jsonObject.optJSONObject(Yahoo.ITEM.toString()).optJSONObject(Yahoo.CONDITION.toString());
        String yahooDate = item.optString(Yahoo.DATE.toString());
        date = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm a", Locale.US).parse(yahooDate.substring(0, yahooDate.length() - 5), new ParsePosition(0));
        code = item.optInt(Yahoo.CODE.toString());
        temperature = item.optInt(Yahoo.TEMP.toString());
        condition = item.optString(Yahoo.TEXT.toString());
        JSONArray forecasts = jsonObject.optJSONObject(Yahoo.ITEM.toString()).optJSONArray(Yahoo.FORECAST.toString());
        forecast = new Forecast[forecasts.length()];
        for (int i = 0; i < forecasts.length(); i++)
        {
            forecast[i] = new Forecast(forecasts.optJSONObject(i));
        }
    }

    public void refresh(Weather weather)
    {
        location = weather.getLocation();
        code = weather.getCode();
        date = weather.getDate();
        temperature = weather.getTemperature();
        condition = weather.getCondition();
        forecast = weather.getForecast();
    }

    public Date getDate()
    {
        return date;
    }

    public int getCode()
    {
        return code;
    }

    public int getTemperature()
    {
        return temperature;
    }

    public String getCondition()
    {
        return condition;
    }

    public String getLocation()
    {
        return location;
    }

    public Forecast[] getForecast()
    {
        return forecast;
    }
}
