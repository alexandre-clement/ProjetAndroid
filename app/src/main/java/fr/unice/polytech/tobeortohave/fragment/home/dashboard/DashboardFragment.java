package fr.unice.polytech.tobeortohave.fragment.home.dashboard;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.unice.polytech.tobeortohave.R;

/**
 * @author Alexandre Clement
 *         Created the 10/05/2017.
 */

public class DashboardFragment extends Fragment
{
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.home_dashboard, container, false);
        for (Overview overview : Overview.values())
        {
            view.findViewById(overview.getId()).setOnClickListener(new DashboardListener(viewPager, overview.ordinal()));
        }
        return view;
    }

    public DashboardFragment with(ViewPager viewPager)
    {
        this.viewPager = viewPager;
        return this;
    }
}
