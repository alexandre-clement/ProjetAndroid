package fr.unice.polytech.tobeortohave.fragment.stock;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;

import fr.unice.polytech.tobeortohave.R;
import fr.unice.polytech.tobeortohave.database.Item;
import fr.unice.polytech.tobeortohave.database.Stock;

/**
 * @author Alexandre Clement
 *         Created the 10/05/2017.
 */

class ItemHolder extends RecyclerView.ViewHolder
{
    private final TextView descriptionView;
    private final StockFragment.EditListenerBuilder editListenerBuilder;
    private final TextView textView;
    private final ImageView imageView;
    private final TextView categoryView;
    private final TextView priceView;
    private final Button edit;
    private final Button statistic;
    private final TextView available;

    public ItemHolder(StockFragment.EditListenerBuilder itemListener, View itemView)
    {
        super(itemView);
        this.editListenerBuilder = itemListener;

        textView = (TextView) itemView.findViewById(R.id.name);
        imageView = (ImageView) itemView.findViewById(R.id.photo);
        descriptionView = (TextView) itemView.findViewById(R.id.description);
        categoryView = (TextView) itemView.findViewById(R.id.job);
        priceView = (TextView) itemView.findViewById(R.id.price);
        available = (TextView) itemView.findViewById(R.id.available);
        edit = (Button) itemView.findViewById(R.id.edit);
        statistic = (Button) itemView.findViewById(R.id.statistic);
    }

    public void bind(Item item)
    {
        textView.setText(item.getText());
        descriptionView.setText(item.getDescription());
        categoryView.setText(item.getCategory());
        priceView.setText(priceView.getContext().getString(R.string.to_euro, String.valueOf(item.getPrice())));
        Picasso.with(imageView.getContext()).load(item.getImageUrl()).centerCrop().fit().into(imageView);
        edit.setOnClickListener(editListenerBuilder.build(item));
        available.setText(available.getContext().getString(R.string.format_available, Stock.getInstance().get(item).get(new Date())));
    }
}
