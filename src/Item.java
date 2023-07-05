public class Item
{
    private String articleName;
    private int partNumber;
    private double size;
    private int amount ;
    private int[] location;
    public Item(String articleName, int partNumber, double size, int amount, int shelf, int x, int y)
    {
        this.articleName = articleName;
        this.partNumber = partNumber;
        this.size = size;
        this.amount = amount;
        location = new int[]{shelf,x,y};
    }

    public String getArticleName()
    {
        return articleName;
    }

    public int getPartNumber()
    {
        return partNumber;
    }

    public double getSize()
    {
        return size;
    }

    public int getAmount()
    {
        return amount;
    }

    public int[] getLocation()
    {
        return location;
    }
}
