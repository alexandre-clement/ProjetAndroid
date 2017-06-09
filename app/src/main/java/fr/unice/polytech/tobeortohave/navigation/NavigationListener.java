package fr.unice.polytech.tobeortohave.navigation;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

/**
 * @author Alexandre Clement
 *         Created the 09/05/2017.
 */

public class NavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener
{
    private final ViewPager viewPager;
    private final SparseArrayCompat<Integer> menu;

    public NavigationListener(ViewPager viewPager, Menu menu)
    {
        this.viewPager = viewPager;
        this.menu = new SparseArrayCompat<>();
        for (int i = 0; i < menu.size(); i++)
        {
            this.menu.append(menu.getItem(i).getItemId(), i);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int position = menu.get(item.getItemId());
        viewPager.setCurrentItem(position);
        return true;
    }
}
