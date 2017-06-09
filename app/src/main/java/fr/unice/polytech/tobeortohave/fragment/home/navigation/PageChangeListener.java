package fr.unice.polytech.tobeortohave.fragment.home.navigation;

import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

/**
 * @author Alexandre Clement
 *         Created the 10/05/2017.
 */

public class PageChangeListener implements ViewPager.OnPageChangeListener
{
    private final NavigationView navigationView;
    private MenuItem previous;

    public PageChangeListener(NavigationView navigationView)
    {
        this.navigationView = navigationView;
        previous = navigationView.getMenu().getItem(0);
        previous.setChecked(true);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {

    }

    @Override
    public void onPageSelected(int position)
    {
        previous.setChecked(false);
        navigationView.getMenu().getItem(position).setChecked(true);
        previous = navigationView.getMenu().getItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state)
    {

    }
}
