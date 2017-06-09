package fr.unice.polytech.tobeortohave.fragment.command;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import fr.unice.polytech.tobeortohave.R;
import fr.unice.polytech.tobeortohave.database.Command;

/**
 * @author Alexandre Clement
 *         Created the 09/06/2017.
 */

public class CommandAdapter extends BaseAdapter
{
    private final List<Command> commands;

    public CommandAdapter(List<Command> commands)
    {
        this.commands = commands;
    }

    @Override
    public int getCount()
    {
        return commands.size();
    }

    @Override
    public Object getItem(int position)
    {
        return commands.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        Command command = commands.get(position);

        if (view == null)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.command_card, parent, false);

        if (command.getState().ordinal() > 0)
            ((ImageView) view.findViewById(R.id.registered)).setImageResource(R.drawable.ic_receipt_yellow_24dp);
        if (command.getState().ordinal() > 1)
            ((ImageView) view.findViewById(R.id.delivery)).setImageResource(R.drawable.ic_local_shipping_yellow_24dp);
        if (command.getState().ordinal() > 2)
            ((ImageView) view.findViewById(R.id.done)).setImageResource(R.drawable.ic_done_yellow_24dp);
        ((TextView) view.findViewById(R.id.client)).setText(command.getClient());
        ((TextView) view.findViewById(R.id.date)).setText(command.getDate());
        ((TextView) view.findViewById(R.id.status)).setText(command.getStatus());
        return view;
    }
}
