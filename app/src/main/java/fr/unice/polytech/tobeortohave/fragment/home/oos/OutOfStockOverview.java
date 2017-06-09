package fr.unice.polytech.tobeortohave.fragment.home.oos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.unice.polytech.tobeortohave.R;
import fr.unice.polytech.tobeortohave.database.Database;
import fr.unice.polytech.tobeortohave.database.Item;
import fr.unice.polytech.tobeortohave.database.Stock;

/**
 * @author Alexandre Clement
 *         Created the 12/05/2017.
 */

public class OutOfStockOverview extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.out_of_stock_overview, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.grid);
        TextView textView = (TextView) view.findViewById(R.id.title);

        List<Item> items = new ArrayList<>();
        final Database database = new Database(getContext());
        final Stock stock = Stock.getInstance();
        for (Item item : database.getItems())
        {
            if (stock.get(item).get(new Date()) == 0)
                items.add(item);
        }
        textView.setText(getString(R.string.out_of_stock_format, items.size()));
        gridView.setAdapter(new GridAdapter(items));
        return view;
    }
}
