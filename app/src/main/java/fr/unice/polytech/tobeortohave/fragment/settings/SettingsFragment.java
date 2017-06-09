package fr.unice.polytech.tobeortohave.fragment.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.unice.polytech.tobeortohave.R;

/**
 * @author Alexandre Clement
 *         Created the 10/05/2017.
 */

public class SettingsFragment extends android.support.v4.app.Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }
}
