package fr.unice.polytech.tobeortohave.fragment.staff;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.unice.polytech.tobeortohave.R;
import fr.unice.polytech.tobeortohave.database.Database;
import fr.unice.polytech.tobeortohave.database.Staff;

/**
 * @author Alexandre Clement
 *         Created the 13/05/2017.
 */

public class AddStaffDialog extends DialogFragment
{
    private ImageView image;
    private TextView[] textViews;
    private StaffFragment.StaffListener staffListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CardDialogAlert);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.add_staff_dialog, null);
        builder.setView(view);
        builder.setPositiveButton(R.string.valid, new PositiveButtonListener());
        builder.setNegativeButton(R.string.cancel, new NegativeButtonListener());
        image = (ImageView) view.findViewById(R.id.photo);
        textViews = new TextView[5];
        textViews[0] = (TextView) view.findViewById(R.id.name);
        textViews[1] = (TextView) view.findViewById(R.id.url);
        textViews[2] = (TextView) view.findViewById(R.id.job);
        textViews[3] = (TextView) view.findViewById(R.id.open);
        textViews[4] = (TextView) view.findViewById(R.id.close);
        textViews[1].addTextChangedListener(new UrlListener());
        return builder.create();
    }

    public AddStaffDialog withStaffListener(StaffFragment.StaffListener itemListener)
    {
        this.staffListener = itemListener;
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
            contentValues.put(Database.StaffEntry.COLUMN_NAME, values[0]);
            contentValues.put(Database.StaffEntry.COLUMN_PHOTO, values[1]);
            contentValues.put(Database.StaffEntry.COLUMN_JOB, values[2]);
            contentValues.put(Database.StaffEntry.COLUMN_OPEN, values[3]);
            contentValues.put(Database.StaffEntry.COLUMN_CLOSE, values[4]);
            new Database(getContext()).getWritableDatabase().insert(Database.StaffEntry.TABLE_NAME, null, contentValues);
            staffListener.add(new Staff(values[0], values[1], values[2], values[3], values[4]));
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

    private class UrlListener implements TextWatcher
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        @Override
        public void afterTextChanged(Editable s)
        {
            Picasso.with(image.getContext()).load(s.toString()).fit().into(image);
        }
    }
}
