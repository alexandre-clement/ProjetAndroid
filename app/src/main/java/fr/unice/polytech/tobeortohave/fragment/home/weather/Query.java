package fr.unice.polytech.tobeortohave.fragment.home.weather;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * @author Alexandre Clement
 *         Created the 13/05/2017.
 */

public class Query extends AsyncTask<String, Void, Weather>
{
    private static final String SQL = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='C'";
    private static final String REQUEST = "https://query.yahooapis.com/v1/public/yql?q=%s&format=json";
    private final List<WeatherServiceListener> listener;
    private Exception exception;

    public Query(List<WeatherServiceListener> listener)
    {
        this.listener = listener;
    }

    @Override
    protected Weather doInBackground(String... params)
    {
        String location = params[0];
        Weather weather = new Weather();

        String sql = String.format(SQL, location);
        String request = String.format(REQUEST, Uri.encode(sql));

        try
        {
            URL url = new URL(request);
            URLConnection connection = url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1)
            {
                result.write(buffer, 0, length);
            }
            inputStream.close();

            JSONObject data = new JSONObject(result.toString("UTF-8")).optJSONObject(Yahoo.QUERY.toString());
            if (!data.has(Yahoo.COUNT.toString()))
            result.close();

            if (data.getInt(Yahoo.COUNT.toString()) == 0)
                throw new Exception("Request failed, please check your connection");

            weather.refresh(data.optJSONObject(Yahoo.RESULTS.toString()).optJSONObject(Yahoo.CHANNEL.toString()));
        }
        catch (Exception exception)
        {
            this.exception = exception;
        }
        return weather;
    }

    @Override
    protected void onPostExecute(Weather weather)
    {
        if (exception != null)
            for (WeatherServiceListener weatherServiceListener : listener)
                weatherServiceListener.failure(exception);
        else
            for (WeatherServiceListener weatherServiceListener : listener)
                weatherServiceListener.success(weather);
    }
}
