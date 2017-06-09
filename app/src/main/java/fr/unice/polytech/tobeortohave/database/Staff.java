package fr.unice.polytech.tobeortohave.database;

/**
 * @author Alexandre Clement
 *         Created the 04/06/2017.
 */

public class Staff
{
    private final String photo;
    private final String name;
    private final String job;
    private final String open;
    private final String close;

    public Staff(String name, String photo, String job, String open, String close)
    {
        this.photo = photo;
        this.name = name;
        this.job = job;
        this.open = open;
        this.close = close;
    }

    public String getJob()
    {
        return job;
    }

    public String getName()
    {
        return name;
    }

    public String getPhoto()
    {
        return photo;
    }

    public String getClose()
    {
        return close;
    }

    public String getOpen()
    {
        return open;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        if (!photo.equals(staff.photo)) return false;
        if (!name.equals(staff.name)) return false;
        return job.equals(staff.job);

    }

    @Override
    public int hashCode()
    {
        int result = photo.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + job.hashCode();
        return result;
    }
}
