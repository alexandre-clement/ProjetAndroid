package fr.unice.polytech.tobeortohave.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.SparseArrayCompat;

/**
 * @author Alexandre Clement
 *         Created the 09/05/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter
{
    private final SparseArrayCompat<Fragment> array;

    public ViewPagerAdapter(FragmentManager fragmentManager, SparseArrayCompat<Fragment> array)
    {
        super(fragmentManager);
        this.array = array;
    }

    @Override
    public Fragment getItem(int position)
    {
        return array.get(position);
    }

    @Override
    public int getCount()
    {
        return array.size();
    }
}
