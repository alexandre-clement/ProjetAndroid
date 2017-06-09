package fr.unice.polytech.tobeortohave.fragment.home.weather;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * @author Alexandre Clement
 *         Created the 13/05/2017.
 */

public class Forecast implements Serializable
{
    private int code;
    private int highTemperature;
    private int lowTemperature;
    private String day;

    public Forecast(JSONObject data)
    {
        code = data.optInt(Yahoo.CODE.toString());
        highTemperature = data.optInt(Yahoo.HIGH.toString());
        lowTemperature = data.optInt(Yahoo.LOW.toString());
        day = data.optString(Yahoo.DAY.toString());
    }

    public int getCode()
    {
        return code;
    }

    public int getHighTemperature()
    {
        return highTemperature;
    }

    public int getLowTemperature()
    {
        return lowTemperature;
    }

    public String getDay()
    {
        return day;
    }
}
