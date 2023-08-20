import java.io.*;
import java.util.*;

public class LagerverwaltungData {
    private HashMap<Character, Item[][]> storage; // Key ist Regalname, Array ist Regalinhalt
    private final char[] shelfnames = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    double maxShelfUnitSize = 8.0;
    private File file = new File("./data/data.txt");

    public LagerverwaltungData() {
        storage = new HashMap<Character, Item[][]>();
        loadData();
    }

    public boolean isPartNoEqual(Item item1, Item item2){
        if(item1.getPartNumber() == item2.getPartNumber())
            return true;
        return false;
    }
    public boolean insertItem(Item item, char shelfname, int x, int y) {
        if (!isShelfUnitEmpty(shelfname, x, y))    //Das Regal ist belegt
        {
            if (    (isPartNoEqual(getItem(shelfname, x, y), item)) &&
                    (((getItem(shelfname, x, y).getSize() + item.getSize())) <= maxShelfUnitSize)) //Das Regal enthält das gleiche Item und hat noch Platz
            {
                getItem(shelfname, x, y).increaseAmount(item.getAmount());
                getItem(shelfname, x, y).increaseSize(item.getSize());
                return true;
            } else {//Regal enthält nicht das gleiche Item oder hat keinen Platz mehr
                return false;
            }
        } else {
            storage.get(shelfname)[y][x] = item;
            return true;
        }
    }

    public boolean removeItem(char shelfname, int x, int y) {
        if (isShelfUnitEmpty(shelfname, x, y))
            return false;
        storage.get(shelfname)[y][x] = null;
        return true;
    }

    private void createShelf(char shelfname) {
        Item items[][] = new Item[9][9];
        storage.put(shelfname, items);
    }

    public Item getItem(char shelfname, int x, int y) {
        return storage.get(shelfname)[y][x];
    }

    public boolean isShelfUnitEmpty(char shelfname, int x, int y) {
        Item[][] shelf = storage.get(shelfname);
        if (shelf[y][x] == null)
            return true;
        return false;
    }

    public char[] getShelfnames() {
        return shelfnames;
    }

    public void saveData() {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(storage);
            System.out.println("[System]: Data successfully written to storage!");
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("[Error]: IOException");
            e.printStackTrace();
        }
    }

    public void loadData() {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            storage = (HashMap<Character, Item[][]>) ois.readObject();
            System.out.println("[System]: Data successfully read from storage");
            return;

        } catch (IOException e) {
            System.err.println("[Error]: IOException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("[Error]: ClassNotFoundException");
            e.printStackTrace();
        }
        for (char c : shelfnames)
            createShelf(c);
    }
}
