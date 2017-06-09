package fr.unice.polytech.tobeortohave.fragment.home.weather;

/**
 * @author Alexandre Clement
 *         Created the 10/05/2017.
 */

public enum Yahoo
{
    QUERY, COUNT, CREATED, LANG, RESULTS, CHANNEL, UNITS, DISTANCE, PRESSURE, SPEED, TEMPERATURE,
    LOCATION, CITY, COUNTRY, REGION, WIND, CHILL, DIRECTION, ATMOSPHERE, HUMIDITY, RISING,
    VISIBILITY, ASTRONOMY, SUNRISE, SUNSET, ITEM, LAT, LONG, CONDITION, CODE, DATE, TEMP, TEXT,
    FORECAST, HIGH, LOW, DAY;

    @Override
    public String toString()
    {
        return super.toString().toLowerCase();
    }
}
