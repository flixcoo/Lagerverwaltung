import java.io.*;
import java.util.HashMap;

public class LagerverwaltungData {
    private HashMap<Character, Item[][]> storage; // Key ist Regalname, Array ist Regalinhalt
    private final char[] shelfnames = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    double maxShelfUnitSize = 8.0;
    private final File file = new File("./data/data.txt");

    public LagerverwaltungData() {
        storage = new HashMap<Character, Item[][]>();
        loadData();
    }

    public HashMap<Character, Item[][]> getStorage() {
        return storage;
    }

    public boolean isPartNoEqual(Item item1, Item item2) {
        try {
            return item1.getPartNumber() == item2.getPartNumber();
        }catch(NullPointerException e)
        {
            System.out.println("[Error]: NullPointerException");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @return 0 = successful insert, 1 = item size is too big, 2 = shelf unit is occupied
     */
    public int insertItem(Item item, char shelfname, int x, int y) {
        if (!isShelfUnitEmpty(shelfname, x, y))    //Is shelf occupied?
        {
            System.out.println("Shelf unit is not empty");
            if (isPartNoEqual(getItem(shelfname, x, y), item)) //is partnumber equal?
                if (getItem(shelfname, x, y).getSize() + item.getSize() <= maxShelfUnitSize) //is space left?
                {
                    getItem(shelfname, x, y).increaseAmount(item.getAmount());
                    getItem(shelfname, x, y).increaseSize(item.getSize());
                    return 0; //successful insert
                } else {
                    return 1; //no space left
                }
            else {
                return 2; //
            }
        } else {
            System.out.println("Shelf unit is empty");
            //shelf is empty
            storage.get(shelfname)[x][y] = item;
            return 0;
        }
    }

    public boolean removeItem(char shelfname, int x, int y) {
        if (isShelfUnitEmpty(shelfname, x, y))
            return false;
        storage.get(shelfname)[x][y] = null;
        return true;
    }

    private void createShelf(char shelfname) {
        Item[][] items = new Item[10][10];
        storage.put(shelfname, items);
    }

    public Item getItem(char shelfname, int x, int y) {
        return storage.get(shelfname)[x][y];
    }

    public boolean isShelfUnitEmpty(char shelfname, int x, int y) {
        Item[][] shelf = storage.get(shelfname);
        return shelf[x][y] == null;
    }

    public char[] getShelfnames() { //ToDo
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
            System.err.println("[Error]: Failed to read data from storage");
            System.err.println("[Error]: IOException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("[Error]: Failed to read data from storage");
            System.err.println("[Error]: ClassNotFoundException");
            e.printStackTrace();
        }
        for (char c : shelfnames)
            createShelf(c);
    }
}
