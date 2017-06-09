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

public class EditStaffDialog extends DialogFragment
{
    private ImageView image;
    private TextView[] textViews;
    private StaffFragment.StaffListener itemListener;
    private Staff old;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CardDialogAlert);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.edit_staff_dialog, null);
        builder.setView(view);
        builder.setPositiveButton(R.string.valid, new PositiveButtonListener());
        builder.setNegativeButton(R.string.cancel, new NegativeButtonListener());
        image = (ImageView) view.findViewById(R.id.photo);
        Picasso.with(image.getContext()).load(old.getPhoto()).fit().into(image);

        int[] id = new int[]{R.id.name, R.id.photo_url, R.id.job, R.id.open, R.id.close};
        textViews = new TextView[id.length];
        for (int i = 0; i < id.length; i++)
            textViews[i] = (TextView) view.findViewById(id[i]);

        textViews[0].setText(old.getName());
        textViews[1].setText(old.getPhoto());
        textViews[2].setText(old.getJob());
        textViews[3].setHint(getString(R.string.open_format, old.getOpen()));
        textViews[4].setHint(getString(R.string.close_format, old.getClose()));

        textViews[1].addTextChangedListener(new UrlListener());
        return builder.create();
    }

    public EditStaffDialog withStaffListener(StaffFragment.StaffListener itemListener)
    {
        this.itemListener = itemListener;
        return this;
    }

    public EditStaffDialog forEmployee(Staff employee)
    {
        this.old = employee;
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
            Staff young = new Staff(values[0], values[1], values[2], values[3], values[4]);
            Database database = new Database(getContext());
            contentValues.put(Database.StaffEntry.COLUMN_NAME, values[0]);
            contentValues.put(Database.StaffEntry.COLUMN_PHOTO, values[1]);
            contentValues.put(Database.StaffEntry.COLUMN_JOB, values[2]);
            contentValues.put(Database.StaffEntry.COLUMN_OPEN, values[3]);
            contentValues.put(Database.StaffEntry.COLUMN_CLOSE, values[4]);
            database.getReadableDatabase().update(Database.StaffEntry.TABLE_NAME, contentValues, where(), null);
            itemListener.edit(old, young);
            dialog.dismiss();
        }

        private String where()
        {
            return String.format("%s=\"%s\" AND %s=\"%s\" AND %s=\"%s\" AND %s=\"%s\" AND %s=\"%s\"",
                    Database.StaffEntry.COLUMN_NAME, old.getName(),
                    Database.StaffEntry.COLUMN_PHOTO, old.getPhoto(),
                    Database.StaffEntry.COLUMN_JOB, old.getJob(),
                    Database.StaffEntry.COLUMN_OPEN, old.getOpen(),
                    Database.StaffEntry.COLUMN_CLOSE, old.getClose());
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
