import java.util.Arrays;

public class Item
{
    private String articleName;
    private int partNumber;
    private double size;
    private int amount ;
    private char shelf;
    private int xCoordinate;
    private int yCoordinate;
    public Item(String articleName, int partNumber, double size, int amount, char shelf, int x, int y)
    {
        this.articleName = articleName;
        this.partNumber = partNumber;
        this.size = size;
        this.amount = amount;
        this.shelf = shelf;
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public void printDetails()
    {
        System.out.println(articleName + " \t| " + partNumber + " \t| " + size + " \t| " + shelf + " [ " + xCoordinate + " | " + yCoordinate + " ]" );
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

    public char getShelf() {return shelf;}
    public int getXcoord()
    {
        return xCoordinate;
    }
    public int getYcoord()
    {
        return yCoordinate;
    }

    public void increaseSize(double add)
    {size += add;}

    public void increaseAmount(double add)
    { amount += add;}
}
