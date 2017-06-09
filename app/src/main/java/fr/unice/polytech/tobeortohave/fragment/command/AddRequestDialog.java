package fr.unice.polytech.tobeortohave.fragment.command;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.unice.polytech.tobeortohave.R;
import fr.unice.polytech.tobeortohave.database.Command;
import fr.unice.polytech.tobeortohave.database.Database;

/**
 * @author Alexandre Clement
 *         Created the 09/06/2017.
 */

public class AddRequestDialog extends DialogFragment
{
    private static final int[] grey_drawable = {R.drawable.ic_receipt_black_24dp, R.drawable.ic_local_shipping_grey_24dp, R.drawable.ic_done_black_24dp};
    private static final int[] yellow_drawable = {R.drawable.ic_receipt_yellow_24dp, R.drawable.ic_local_shipping_yellow_24dp, R.drawable.ic_done_yellow_24dp};
    private ImageView[] imageViews;
    private TextView[] textViews;
    private List<Command> commands;
    private int state;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CardDialogAlert);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.add_request, null);
        builder.setView(view);
        textViews = new TextView[3];
        textViews[0] = (TextView) view.findViewById(R.id.client);
        textViews[1] = (TextView) view.findViewById(R.id.date);
        textViews[2] = (TextView) view.findViewById(R.id.status);
        imageViews = new ImageView[3];
        imageViews[0] = (ImageView) view.findViewById(R.id.registered);
        imageViews[1] = (ImageView) view.findViewById(R.id.delivery);
        imageViews[2] = (ImageView) view.findViewById(R.id.done);

        for (int i = 0; i < imageViews.length; i++)
        {
            imageViews[i].setOnClickListener(new ImageClickListener(i + 1));
        }

        builder.setPositiveButton(R.string.valid, new PositiveButtonListener());
        builder.setNegativeButton(R.string.cancel, new NegativeButtonListener());
        return builder.create();
    }

    public AddRequestDialog withCommands(List<Command> commands)
    {
        this.commands = commands;
        return this;
    }

    private class PositiveButtonListener implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            ContentValues contentValues = new ContentValues();
            String[] values = new String[textViews.length];
            for (int i = 0; i < textViews.length; i++)
            {
                values[i] = textViews[i].getText().toString();
                if (values[i].isEmpty())
                    return;
            }
            contentValues.put(Database.CommandEntry.COLUMN_STATE, Command.State.values()[state].toString());
            contentValues.put(Database.CommandEntry.COLUMN_CLIENT, values[0]);
            contentValues.put(Database.CommandEntry.COLUMN_DATE, values[1]);
            contentValues.put(Database.CommandEntry.COLUMN_STATUS, values[2]);
            new Database(getContext()).getWritableDatabase().insert(Database.CommandEntry.TABLE_NAME, null, contentValues);
            commands.add(new Command(Command.State.values()[state], values[0], values[1], values[2]));
            dialog.dismiss();
        }
    }

    private class NegativeButtonListener implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            dialog.cancel();
        }
    }

    private class ImageClickListener implements View.OnClickListener
    {
        private final int position;

        public ImageClickListener(int position)
        {
            this.position = position;
        }

        @Override
        public void onClick(View v)
        {
            if (state >= position)
                state = position - 1;
            else
                state = position;
            for (int i = 0; i < state; i++)
            {
                imageViews[i].setImageResource(yellow_drawable[i]);
            }
            for (int i = state; i < grey_drawable.length; i++)
            {
                imageViews[i].setImageResource(grey_drawable[i]);
            }
        }
    }
}
