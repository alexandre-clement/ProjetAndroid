package fr.unice.polytech.tobeortohave.fragment.staff;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.unice.polytech.tobeortohave.R;
import fr.unice.polytech.tobeortohave.database.Staff;

/**
 * @author Alexandre Clement
 *         Created the 04/06/2017.
 */

class StaffHolder extends RecyclerView.ViewHolder
{
    private final StaffFragment.EditListenerBuilder editListenerBuilder;
    private final TextView nameView;
    private final ImageView photo;
    private final TextView jobView;
    private final Button edit;
    private final Button statistic;
    private final TextView openView;
    private final TextView closeView;

    public StaffHolder(StaffFragment.EditListenerBuilder itemListener, View itemView)
    {
        super(itemView);
        this.editListenerBuilder = itemListener;

        nameView = (TextView) itemView.findViewById(R.id.name);
        photo = (ImageView) itemView.findViewById(R.id.photo);
        jobView = (TextView) itemView.findViewById(R.id.job);
        openView = (TextView) itemView.findViewById(R.id.open);
        closeView = (TextView) itemView.findViewById(R.id.close);
        edit = (Button) itemView.findViewById(R.id.edit);
        statistic = (Button) itemView.findViewById(R.id.statistic);
    }

    public void bind(Staff employee)
    {
        nameView.setText(employee.getName());
        jobView.setText(employee.getJob());
        openView.setText(openView.getContext().getString(R.string.open_format, employee.getOpen()));
        closeView.setText(closeView.getContext().getString(R.string.close_format, employee.getClose()));
        Picasso.with(photo.getContext()).load(employee.getPhoto()).centerCrop().fit().into(photo);
        edit.setOnClickListener(editListenerBuilder.build(employee));
    }
}
