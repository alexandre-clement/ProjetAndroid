package fr.unice.polytech.tobeortohave.fragment.home.dashboard;

import fr.unice.polytech.tobeortohave.R;

/**
 * @author Alexandre Clement
 *         Created the 12/05/2017.
 */

enum Overview
{
    TRAFFIC(R.id.traffic),
    WEATHER(R.id.weather),
    OUT_OF_STOCK(R.id.out_of_stock),
    COMMAND(R.id.command),
    DELIVERY(R.id.delivery);

    private final int id;

    Overview(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }
}
