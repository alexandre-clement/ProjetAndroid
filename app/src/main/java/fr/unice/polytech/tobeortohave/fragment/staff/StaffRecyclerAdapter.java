package fr.unice.polytech.tobeortohave.fragment.staff;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.unice.polytech.tobeortohave.R;
import fr.unice.polytech.tobeortohave.database.Item;
import fr.unice.polytech.tobeortohave.database.Staff;
import fr.unice.polytech.tobeortohave.fragment.stock.StockFragment;

/**
 * @author Alexandre Clement
 *         Created the 04/06/2017.
 */

public class StaffRecyclerAdapter extends RecyclerView.Adapter<StaffHolder>
{
    private final List<Staff> staff;
    private final StaffFragment.EditListenerBuilder itemListener;

    StaffRecyclerAdapter(List<Staff> staff, StaffFragment.EditListenerBuilder itemListener)
    {
        this.staff = staff;
        this.itemListener = itemListener;
    }

    @Override
    public StaffHolder onCreateViewHolder(ViewGroup viewGroup, int itemType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.staff_card, viewGroup, false);
        return new StaffHolder(itemListener, view);
    }

    @Override
    public void onBindViewHolder(StaffHolder holder, int position)
    {
        Staff employee = staff.get(position);
        holder.bind(employee);
    }

    @Override
    public int getItemCount()
    {
        return staff.size();
    }
}
