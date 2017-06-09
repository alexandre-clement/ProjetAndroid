package fr.unice.polytech.tobeortohave.navigation;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import java.util.Deque;

/**
 * @author Alexandre Clement
 *         Created the 10/05/2017.
 */

public class PageChangeListener implements ViewPager.OnPageChangeListener
{
    private final Deque<Integer> navigation;
    private final BottomNavigationView navigationView;
    private MenuItem previous;

    public PageChangeListener(Deque<Integer> navigation, BottomNavigationView navigationView)
    {
        this.navigation = navigation;
        this.navigationView = navigationView;
        previous = navigationView.getMenu().getItem(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {

    }

    @Override
    public void onPageSelected(int position)
    {
        previous.setChecked(false);
        navigation.push(position);
        navigationView.getMenu().getItem(position).setChecked(true);
        previous = navigationView.getMenu().getItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state)
    {

    }
}
