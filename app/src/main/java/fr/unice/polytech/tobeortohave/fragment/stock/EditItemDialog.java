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

public class EditItemDialog extends DialogFragment
{
    private ImageView image;
    private TextView[] textViews;
    private StockFragment.ItemListener itemListener;
    private Item old;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CardDialogAlert);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.edit_item_dialog, null);
        builder.setView(view);
        builder.setPositiveButton(R.string.valid, new PositiveButtonListener());
        builder.setNegativeButton(R.string.cancel, new NegativeButtonListener());
        image = (ImageView) view.findViewById(R.id.photo);
        Picasso.with(image.getContext()).load(old.getImageUrl()).fit().into(image);

        int[] id = new int[]{R.id.name, R.id.item_url, R.id.item_description, R.id.item_category, R.id.item_price};
        textViews = new TextView[id.length];
        for (int i = 0; i < id.length; i++)
            textViews[i] = (TextView) view.findViewById(id[i]);

        textViews[0].setText(old.getText());
        textViews[1].setText(old.getImageUrl());
        textViews[2].setText(old.getDescription());
        textViews[3].setText(old.getCategory());
        textViews[4].setText(String.valueOf(old.getPrice()));

        textViews[1].addTextChangedListener(new UrlListener());
        return builder.create();
    }

    public EditItemDialog withItemListener(StockFragment.ItemListener itemListener)
    {
        this.itemListener = itemListener;
        return this;
    }

    public EditItemDialog forItem(Item item)
    {
        this.old = item;
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
            Item young = new Item(values[0], values[1], values[2], values[3], Double.valueOf(values[4]));
            Database database = new Database(getContext());
            contentValues.put(Database.ItemEntry.COLUMN_NAME, values[0]);
            contentValues.put(Database.ItemEntry.COLUMN_URL, values[1]);
            contentValues.put(Database.ItemEntry.COLUMN_DESCRIPTION, values[2]);
            contentValues.put(Database.ItemEntry.COLUMN_CATEGORY, values[3]);
            contentValues.put(Database.ItemEntry.COLUMN_PRICE, Double.valueOf(values[4]));
            database.getReadableDatabase().update(Database.ItemEntry.TABLE_NAME, contentValues, where(), null);
            itemListener.edit(old, young);
            dialog.dismiss();
        }

        private String where()
        {
            return String.format("%s=\"%s\" AND %s=\"%s\" AND %s=\"%s\" AND %s=\"%s\" AND %s=%s",
                    Database.ItemEntry.COLUMN_NAME, old.getText(),
                    Database.ItemEntry.COLUMN_DESCRIPTION, old.getDescription(),
                    Database.ItemEntry.COLUMN_URL, old.getImageUrl(),
                    Database.ItemEntry.COLUMN_CATEGORY, old.getCategory(),
                    Database.ItemEntry.COLUMN_PRICE, old.getPrice());
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
