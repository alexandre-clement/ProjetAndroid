package fr.unice.polytech.tobeortohave.database;

/**
 * @author Alexandre Clement
 *         Created the 05/06/2017.
 */

public class Command
{
    private final State state;
    private final String client;
    private final String date;
    private final String status;

    public Command(State state, String client, String date, String status)
    {
        this.state = state;
        this.client = client;
        this.date = date;
        this.status = status;
    }

    public State getState()
    {
        return state;
    }

    public String getClient()
    {
        return client;
    }

    public String getDate()
    {
        return date;
    }

    public String getStatus()
    {
        return status;
    }

    public enum State
    {
        NONE,
        REGISTERED,
        DELIVERING,
        DONE
    }
}
