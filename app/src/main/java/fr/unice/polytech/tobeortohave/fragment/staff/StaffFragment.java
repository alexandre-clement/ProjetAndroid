package fr.unice.polytech.tobeortohave.fragment.staff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.unice.polytech.tobeortohave.R;
import fr.unice.polytech.tobeortohave.database.Database;
import fr.unice.polytech.tobeortohave.database.Staff;
import fr.unice.polytech.tobeortohave.fragment.stock.StockFragment;

/**
 * @author Alexandre Clement
 *         Created the 10/05/2017.
 */

public class StaffFragment extends android.support.v4.app.Fragment
{
    private BottomNavigationView navigationView;
    private RecyclerView recycler;
    private List<Staff> staff;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        CoordinatorLayout view = (CoordinatorLayout) inflater.inflate(R.layout.staff_fragment, container, false);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        staff = new Database(getContext()).getStaff();

        recycler.setAdapter(new StaffRecyclerAdapter(staff, new EditListenerBuilder()));
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new FabListener());
        return view;
    }

    private class FabListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            new AddStaffDialog().withStaffListener(new StaffListener()).show(getFragmentManager(), getTag());
        }
    }

    public class EditListener implements View.OnClickListener
    {
        private final Staff employee;

        public EditListener(Staff employee)
        {
            this.employee = employee;
        }

        @Override
        public void onClick(View v)
        {
            new EditStaffDialog().withStaffListener(new StaffListener()).forEmployee(employee).show(getFragmentManager(), getTag());
        }
    }

    public class EditListenerBuilder
    {
        public EditListener build(Staff employee)
        {
            return new EditListener(employee);
        }
    }

    public class StaffListener
    {
        public int add(Staff employee)
        {
            staff.add(employee);
            recycler.getAdapter().notifyItemInserted(staff.size() - 1);
            return staff.size() - 1;
        }

        public int edit(Staff old, Staff young)
        {
            int index = staff.indexOf(old);
            staff.remove(old);
            staff.add(index, young);
            recycler.getAdapter().notifyItemChanged(index);
            return index;
        }

        public int remove(Staff employee)
        {
            int index = staff.indexOf(employee);
            staff.remove(index);
            recycler.getAdapter().notifyItemRemoved(index);
            return index;
        }
    }
}
