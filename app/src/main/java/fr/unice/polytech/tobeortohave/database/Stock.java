package fr.unice.polytech.tobeortohave.database;

import android.content.Context;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author Alexandre Clement
 *         Created the 03/06/2017.
 */

public class Stock
{
    private static final Stock instance = new Stock();
    private final Map<Item, Map<Date, Integer>> storage;

    private Stock()
    {
        this.storage = new HashMap<>();
    }

    public static Stock getInstance()
    {
        return instance;
    }

    public Map<Date, Integer> get(Item item)
    {
        if (storage.containsKey(item))
            return storage.get(item);

        Random random = new Random();
        storage.put(item, new TreeMap<Date, Integer>(new DateComparator()));
        Calendar calendar = Calendar.getInstance(Locale.FRANCE);

        for (int i = 3; i <= 6; i++)
        {
            calendar.set(2017, i, 1);
            for (int j = 1; j <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); j++)
            {
                calendar.set(2017, i, j, 0, 0, 0);
                storage.get(item).put(calendar.getTime(), random.nextInt(10));
            }
        }
        return storage.get(item);
    }

    private class DateComparator implements Comparator<Date>
    {
        @Override
        public int compare(Date o1, Date o2)
        {
            Calendar c1 = Calendar.getInstance(Locale.FRANCE);
            c1.setTime(o1);
            Calendar c2 = Calendar.getInstance(Locale.FRANCE);
            c2.setTime(o2);
            if (c1.get(Calendar.YEAR) < c2.get(Calendar.YEAR))
                return -1;
            if (c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR))
                return 1;
            if (c1.get(Calendar.MONTH) < c2.get(Calendar.MONTH))
                return -1;
            if (c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR))
                return 1;
            if (c1.get(Calendar.DAY_OF_MONTH) < c2.get(Calendar.DAY_OF_MONTH))
                return -1;
            if (c1.get(Calendar.DAY_OF_MONTH) > c2.get(Calendar.DAY_OF_MONTH))
                return 1;
            return 0;
        }
    }
}
