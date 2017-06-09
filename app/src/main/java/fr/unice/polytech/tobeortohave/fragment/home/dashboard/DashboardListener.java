package fr.unice.polytech.tobeortohave.fragment.home.dashboard;

import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @author Alexandre Clement
 *         Created the 12/05/2017.
 */

class DashboardListener implements View.OnClickListener
{
    private final ViewPager viewPager;
    private final int ordinal;

    DashboardListener(ViewPager viewPager, int ordinal)
    {
        this.viewPager = viewPager;
        this.ordinal = ordinal;
    }

    @Override
    public void onClick(View v)
    {
        viewPager.setCurrentItem(ordinal + 1);
    }
}
