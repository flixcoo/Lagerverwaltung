import javax.lang.model.type.NullType;
import java.util.*;
public class LagerverwaltungData {
    private HashMap<Character, Item[][]> storage; // Key ist Regalname, Array ist Regalinhalt
    private final char[] shelfnames = {'A','B','C','D','E','F','G','H'};
    double maxShelfUnitSize = 8.0;

    public LagerverwaltungData() {
        storage = new HashMap<Character, Item[][]>();
        for( char c : shelfnames)
            createShelf(c);

        Item item = new Item("Metalleimer", 125124,3.0,3,'A',1,1);
        System.out.println("Item eingefuegt: "+insertItem(item,item.getShelf(),item.getXcoord(),item.getYcoord()));
    }

    public boolean insertItem(Item item, char shelfname, int x, int y)
    {
        if(!isShelfUnitEmpty(shelfname,x,y))    //Das Regal ist belegt
            if( ( getItem(shelfname,x,y).getArticleName() == item.getArticleName() ) && (((getItem(shelfname,x,y).getSize() + item.getSize())) < maxShelfUnitSize) ) //Das Regal enthält das gleiche Item und hat noch Platz
            {
                getItem(shelfname,x,y).increaseAmount(item.getAmount());
                getItem(shelfname,x,y).increaseSize(item.getSize());
            }
            else
                return false;
        swapItem(item, shelfname, x,y);
        return true;
    }

    public void swapItem(Item newItem, char shelfname, int x, int y)
    {
        Item temp[][] = storage.get(shelfname);
        temp[y][x] = newItem;
        storage.put(shelfname, temp);
    }
    public boolean removeItem(char shelfname, int x, int y)
    {
        if(isShelfUnitEmpty(shelfname,x,y))
            return false;
        swapItem(null, shelfname, x,y);
        return true;
    }
    private void createShelf(char shelfname)
    {
        Item items[][] = new Item[9][9];
        storage.put(shelfname, items);
    }

    public Item getItem(char shelfname, int x, int y)
    {
        Item[][] shelf = storage.get(shelfname);
        return shelf[y][x];

    }

    public boolean isShelfUnitEmpty(char shelfname, int x, int y)
    {
        Item[][] shelf = storage.get(shelfname);
        if (shelf[y][x] == null)
            return true;
        return false;
    }

    public char[] getShelfnames()
    {
        return shelfnames;
    }

    public void saveData()
    {
        //ToDo
    }
}
