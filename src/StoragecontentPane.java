import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.HashMap;

public class StoragecontentPane {
    private JPanel panel;
    private JTable table;
    private String[] header = {"Regal", "Fach", "Teilenummer", "Artikelname", "Größe", "Anzahl"};
    private String[][] data = new String[80][6];
    private HashMap<Character, Item[][]> storage;
    private LagerverwaltungData d;

    public StoragecontentPane(HashMap storage, LagerverwaltungData d) {
        this.d = d;
        this.storage = storage;
        fillTable(storage);
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(408, 400));
        panel.setLayout(new BorderLayout());

        table = new JTable(data, header);

        JScrollPane pane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        panel.add(pane, BorderLayout.CENTER);

        columnFormatting();
        JOptionPane.showConfirmDialog(null, panel, "Lagerinhalt", JOptionPane.CLOSED_OPTION);
    }

    private void columnFormatting() {
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(40);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < header.length; i++)
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);


    }

    private void fillTable(HashMap storage) {
        int i = 0;
        for (char c : d.getShelfnames()) {
            Item[][] item = (Item[][]) storage.get(c);
            for (int j = 0; j < item.length; j++) {
                for (int k = 0; k < item[j].length; k++) {
                    if (i < data.length && !d.isShelfUnitEmpty(c,j,k)) {
                        try {
                            data[i][0] = String.valueOf(item[j][k].getShelf());
                            String unit = item[j][k].getXcoord() + " - " + item[j][k].getYcoord();
                            data[i][1] = unit;
                            data[i][2] = String.valueOf(item[j][k].getPartNumber());
                            data[i][3] = String.valueOf(item[j][k].getArticleName());
                            data[i][4] = String.valueOf(item[j][k].getSize());
                            data[i][5] = String.valueOf(item[j][k].getAmount());
                            i++;
                        } catch (NullPointerException e) {
                            System.err.println("[Error]: NullPointerException\n[Error]: Couldnt acces item");
                        }
                    }
                }
            }
        }
    }


    private void printArray(Item[][] arr) {
        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr[j].length; k++) {
                System.out.print(arr[j][k] + " ");
            }
            System.out.println();
        }
    }
}


