package fr.unice.polytech.tobeortohave.fragment.home.weather;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;

/**
 * @author Alexandre Clement
 *         Created the 14/05/2017.
 */

public class WeatherCache
{
    private static final String CACHED_WEATHER_FILE = "weather.data";
    private static final Logger LOGGER = Logger.getLogger(WeatherCache.class.getName());
    private final Context context;

    public WeatherCache(Context context)
    {
        this.context = context;
    }

    public void save(Weather weather)
    {
        try
        {
            final FileOutputStream fileOutputStream = context.openFileOutput(CACHED_WEATHER_FILE, Context.MODE_PRIVATE);
            new ObjectOutputStream(fileOutputStream).writeObject(weather);
        }
        catch (IOException exception)
        {
            LOGGER.warning(exception.getMessage());
        }
    }

    public Weather load()
    {
        try
        {
            File file = new File(context.getFilesDir().getPath(), CACHED_WEATHER_FILE);
            if (!file.exists() && file.createNewFile())
                new ObjectOutputStream(context.openFileOutput(CACHED_WEATHER_FILE, Context.MODE_PRIVATE)).writeObject(new Weather());
            final FileInputStream fileInputStream = context.openFileInput(CACHED_WEATHER_FILE);
            return (Weather) new ObjectInputStream(fileInputStream).readObject();
        }
        catch (Exception exception)
        {
            LOGGER.warning(exception.getMessage());
            return new Weather();
        }
    }
}
