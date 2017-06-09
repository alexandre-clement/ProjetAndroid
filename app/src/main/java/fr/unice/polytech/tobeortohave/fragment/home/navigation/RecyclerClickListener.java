package fr.unice.polytech.tobeortohave.fragment.home.navigation;

import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Alexandre Clement
 *         Created the 10/05/2017.
 */

public class RecyclerClickListener implements View.OnClickListener
{
    private final RecyclerView recyclerView;
    private final ViewPager viewPager;
    private final NavigationView navigationView;

    RecyclerClickListener(RecyclerView recyclerView, ViewPager viewPager, NavigationView navigationView)
    {
        this.recyclerView = recyclerView;
        this.viewPager = viewPager;
        this.navigationView = navigationView;
    }

    @Override
    public void onClick(View view)
    {
        int itemPosition = recyclerView.getChildLayoutPosition(view);
        navigationView.getMenu().getItem(0).setChecked(false);
        navigationView.getMenu().getItem(itemPosition + 1).setChecked(true);
        viewPager.setCurrentItem(itemPosition + 1);
    }
}
