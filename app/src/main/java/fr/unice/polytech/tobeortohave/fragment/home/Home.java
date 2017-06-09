package fr.unice.polytech.tobeortohave.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.unice.polytech.tobeortohave.R;
import fr.unice.polytech.tobeortohave.fragment.home.command.CommandFragment;
import fr.unice.polytech.tobeortohave.fragment.home.dashboard.DashboardFragment;
import fr.unice.polytech.tobeortohave.fragment.home.delivery.DeliveryFragment;
import fr.unice.polytech.tobeortohave.fragment.home.navigation.NavigationListener;
import fr.unice.polytech.tobeortohave.fragment.home.navigation.PageChangeListener;
import fr.unice.polytech.tobeortohave.fragment.home.oos.OutOfStockFragment;
import fr.unice.polytech.tobeortohave.fragment.home.settings.SettingsFragment;
import fr.unice.polytech.tobeortohave.fragment.home.traffic.TrafficFragment;
import fr.unice.polytech.tobeortohave.fragment.home.weather.WeatherFragment;
import fr.unice.polytech.tobeortohave.navigation.ViewPagerAdapter;

/**
 * @author Alexandre Clement
 *         Created the 09/05/2017.
 */

public class Home extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        NavigationView navigationView = (NavigationView) view.findViewById(R.id.home_navigation);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.home_pager);

        SparseArrayCompat<Fragment> fragments = createHomeFragments(viewPager, navigationView);

        viewPager.setAdapter(new ViewPagerAdapter(getFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(new PageChangeListener(navigationView));
        viewPager.setCurrentItem(0);

        navigationView.setNavigationItemSelectedListener(new NavigationListener(viewPager, navigationView.getMenu()));
        return view;
    }

    private SparseArrayCompat<Fragment> createHomeFragments(ViewPager viewPager, NavigationView navigationView)
    {
        SparseArrayCompat<Fragment> fragments = new SparseArrayCompat<>();
        fragments.append(0, new DashboardFragment().with(viewPager));
        fragments.append(1, new TrafficFragment());
        fragments.append(2, new WeatherFragment());
        fragments.append(3, new OutOfStockFragment());
        fragments.append(4, new CommandFragment());
        fragments.append(5, new DeliveryFragment());
        fragments.append(6, new SettingsFragment());
        return fragments;
    }
}
