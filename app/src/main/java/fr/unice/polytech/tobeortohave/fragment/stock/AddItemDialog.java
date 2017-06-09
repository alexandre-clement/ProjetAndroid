package fr.unice.polytech.tobeortohave.fragment.stock;

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
import fr.unice.polytech.tobeortohave.database.Item;

/**
 * @author Alexandre Clement
 *         Created the 13/05/2017.
 */

public class AddItemDialog extends DialogFragment
{
    private ImageView image;
    private TextView[] textViews;
    private StockFragment.ItemListener itemListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CardDialogAlert);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.add_item_dialog, null);
        builder.setView(view);
        builder.setPositiveButton(R.string.valid, new PositiveButtonListener());
        builder.setNegativeButton(R.string.cancel, new NegativeButtonListener());
        image = (ImageView) view.findViewById(R.id.photo);
        textViews = new TextView[5];
        textViews[0] = (TextView) view.findViewById(R.id.name);
        textViews[1] = (TextView) view.findViewById(R.id.item_url);
        textViews[2] = (TextView) view.findViewById(R.id.item_description);
        textViews[3] = (TextView) view.findViewById(R.id.item_category);
        textViews[4] = (TextView) view.findViewById(R.id.item_price);

        textViews[1].addTextChangedListener(new UrlListener());
        return builder.create();
    }

    public AddItemDialog withItemListener(StockFragment.ItemListener itemListener)
    {
        this.itemListener = itemListener;
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
            contentValues.put(Database.ItemEntry.COLUMN_NAME, values[0]);
            contentValues.put(Database.ItemEntry.COLUMN_URL, values[1]);
            contentValues.put(Database.ItemEntry.COLUMN_DESCRIPTION, values[2]);
            contentValues.put(Database.ItemEntry.COLUMN_CATEGORY, values[3]);
            contentValues.put(Database.ItemEntry.COLUMN_PRICE, Double.valueOf(values[4]));
            new Database(getContext()).getWritableDatabase().insert(Database.ItemEntry.TABLE_NAME, null, contentValues);
            itemListener.add(new Item(values[0], values[1], values[2], values[3], Double.valueOf(values[4])));
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
