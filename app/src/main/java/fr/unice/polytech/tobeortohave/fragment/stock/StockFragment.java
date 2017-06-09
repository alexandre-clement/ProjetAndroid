package fr.unice.polytech.tobeortohave.fragment.stock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.unice.polytech.tobeortohave.R;
import fr.unice.polytech.tobeortohave.database.Database;
import fr.unice.polytech.tobeortohave.database.Item;

/**
 * @author Alexandre Clement
 *         Created the 09/05/2017.
 */

public class StockFragment extends Fragment
{
    private BottomNavigationView navigationView;
    private RecyclerView recycler;
    private List<Item> items;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        CoordinatorLayout view = (CoordinatorLayout) inflater.inflate(R.layout.stock_fragment, container, false);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        items = new Database(getContext()).getItems();

        recycler.setAdapter(new StockRecyclerAdapter(items, new EditListenerBuilder()));
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new FabListener());
        return view;
    }

    public StockFragment withNavigationBar(BottomNavigationView navigationView)
    {
        this.navigationView = navigationView;
        return this;
    }

    private class FabListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            new AddItemDialog().withItemListener(new ItemListener()).show(getFragmentManager(), getTag());
        }
    }

    public class EditListenerBuilder
    {
        public EditListener build(Item item)
        {
            return new EditListener(item);
        }
    }

    public class EditListener implements View.OnClickListener
    {
        private final Item item;

        public EditListener(Item item)
        {
            this.item = item;
        }

        @Override
        public void onClick(View v)
        {
            new EditItemDialog().withItemListener(new ItemListener()).forItem(item).show(getFragmentManager(), getTag());
        }
    }

    public class ItemListener
    {
        public int add(Item item)
        {
            items.add(item);
            recycler.getAdapter().notifyItemInserted(items.size() - 1);
            return items.size() - 1;
        }

        public int edit(Item old, Item young)
        {
            int index = items.indexOf(old);
            items.remove(old);
            items.add(index, young);
            recycler.getAdapter().notifyItemChanged(index);
            return index;
        }

        public int remove(Item item)
        {
            int index = items.indexOf(item);
            items.remove(index);
            recycler.getAdapter().notifyItemRemoved(index);
            return index;
        }
    }
}