package fr.unice.polytech.tobeortohave;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayDeque;
import java.util.Deque;

import fr.unice.polytech.tobeortohave.database.Stock;
import fr.unice.polytech.tobeortohave.fragment.command.CommandFragment;
import fr.unice.polytech.tobeortohave.fragment.home.Home;
import fr.unice.polytech.tobeortohave.fragment.staff.StaffFragment;
import fr.unice.polytech.tobeortohave.fragment.statistic.StatisticFragment;
import fr.unice.polytech.tobeortohave.fragment.stock.StockFragment;
import fr.unice.polytech.tobeortohave.navigation.NavigationListener;
import fr.unice.polytech.tobeortohave.navigation.PageChangeListener;
import fr.unice.polytech.tobeortohave.navigation.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity
{
    private final SparseArrayCompat<Fragment> fragments = new SparseArrayCompat<>();
    private final Deque<Integer> navigation = new ArrayDeque<>();
    private ViewPager viewPager;
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);

        setUpFragment();

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(new PageChangeListener(navigation, navigationView));

        navigationView.setOnNavigationItemSelectedListener(new NavigationListener(viewPager, navigationView.getMenu()));

        navigation.add(0);
        selectFragment(navigation.peek());
    }

    @Override
    public void onBackPressed()
    {
        int peek = navigation.pop();
        if (navigation.isEmpty())
            super.onBackPressed();
        else
        {
            navigationView.getMenu().getItem(peek).setChecked(false);
            selectFragment(navigation.peek());
            navigation.pop();
        }
    }

    private void selectFragment(int position)
    {
        viewPager.setCurrentItem(position);
        navigationView.getMenu().getItem(position).setChecked(true);
    }

    private void setUpFragment()
    {
        fragments.append(0, new Home());
        fragments.append(1, new StockFragment().withNavigationBar(navigationView));
        fragments.append(2, new StaffFragment());
        fragments.append(3, new CommandFragment());
        fragments.append(4, new StatisticFragment());
    }
}
