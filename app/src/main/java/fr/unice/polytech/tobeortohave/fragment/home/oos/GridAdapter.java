package fr.unice.polytech.tobeortohave.fragment.home.oos;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.unice.polytech.tobeortohave.R;
import fr.unice.polytech.tobeortohave.database.Item;

/**
 * @author Alexandre Clement
 *         Created the 08/06/2017.
 */

public class GridAdapter extends BaseAdapter
{
    private final List<Item> items;

    public GridAdapter(List<Item> items)
    {
        this.items = items;
    }

    @Override
    public int getCount()
    {
        return items.size();
    }

    @Override
    public Object getItem(int position)
    {
        return items.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        if (view == null)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.out_of_stock_card, parent, false);

        final ImageView photo = (ImageView) view.findViewById(R.id.photo);
        Picasso.with(parent.getContext()).load(items.get(position).getImageUrl()).fit().into(photo);
        ((TextView) view.findViewById(R.id.name)).setText(items.get(position).getText());
        return view;
    }
}
