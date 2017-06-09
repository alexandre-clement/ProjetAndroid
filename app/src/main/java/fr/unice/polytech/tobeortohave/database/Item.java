package fr.unice.polytech.tobeortohave.database;

/**
 * @author Alexandre Clement
 *         Created the 10/05/2017.
 */

public class Item
{
    private final String text;
    private final String imageUrl;
    private final String description;
    private final String category;
    private final double price;

    public Item(String text, String imageUrl, String description, String category, double price)
    {
        this.text = text;
        this.imageUrl = imageUrl;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public String getText()
    {
        return text;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public String getDescription()
    {
        return description;
    }

    public double getPrice()
    {
        return price;
    }

    public String getCategory()
    {
        return category;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (Double.compare(item.price, price) != 0) return false;
        if (!text.equals(item.text)) return false;
        if (!imageUrl.equals(item.imageUrl)) return false;
        if (!description.equals(item.description)) return false;
        return category.equals(item.category);

    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        result = text.hashCode();
        result = 31 * result + imageUrl.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + category.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
