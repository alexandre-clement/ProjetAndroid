package fr.unice.polytech.tobeortohave.fragment.stock;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.unice.polytech.tobeortohave.R;
import fr.unice.polytech.tobeortohave.database.Item;

/**
 * @author Alexandre Clement
 *         Created the 10/05/2017.
 */

class StockRecyclerAdapter extends RecyclerView.Adapter<ItemHolder>
{
    private final List<Item> cards;
    private final StockFragment.EditListenerBuilder itemListener;

    StockRecyclerAdapter(List<Item> cards, StockFragment.EditListenerBuilder itemListener)
    {
        this.cards = cards;
        this.itemListener = itemListener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int itemType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stock_card, viewGroup, false);
        return new ItemHolder(itemListener, view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position)
    {
        Item card = cards.get(position);
        holder.bind(card);
    }

    @Override
    public int getItemCount()
    {
        return cards.size();
    }
}
