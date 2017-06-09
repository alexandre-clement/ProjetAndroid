package fr.unice.polytech.tobeortohave.fragment.command;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import fr.unice.polytech.tobeortohave.R;
import fr.unice.polytech.tobeortohave.database.Command;
import fr.unice.polytech.tobeortohave.database.Database;

/**
 * @author Alexandre Clement
 *         Created the 10/05/2017.
 */

public class CommandFragment extends android.support.v4.app.Fragment
{
    private List<Command> commands;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.request_fragment, container, false);
        Database database = new Database(getContext());
        commands = database.getCommand();
        ((ListView) view.findViewById(R.id.list)).setAdapter(new CommandAdapter(commands));
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new FabListener());
        return view;
    }

    private class FabListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            new AddRequestDialog().withCommands(commands).show(getFragmentManager(), getTag());
        }
    }
}
