import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LagerverwaltungGUI extends JFrame {
    private final String[] shelfnames = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private final String[] shelfNums = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}; //ToDo
    private final LagerverwaltungData d;

    //Search-Area
    private final JPanel searchPanel;
    private final JLabel labelShelfname;
    private final JComboBox dropdownShelf;
    private final JLabel xcoord;
    private final JTextField textXcoord;
    private final JLabel ycoord;
    private final JTextField textYcoord;

    //Button-Area
    private final JPanel buttonPanel;
    private final JButton callButton;
    private final JButton insertButton;
    private final JButton removeButton;
    private final JButton clearButton;
    private final JButton storageButton;

    //Item-Area
    private final JPanel itemPanel;
    private final JLabel labelArtName;
    private final JLabel labelPartNo;
    private final JLabel labelSize;
    private final JLabel labelAmount;
    private final JTextField textArtName;
    private final JTextField textPartNo;
    private final JTextField textSize;
    private final JTextField textAmount;


    //Menu
    private final JMenuBar menubar;
    private final JMenu dataMenu;
    private final JMenu viewMenu;
    private final JMenuItem exitMenuButton;
    private final JMenuItem clearMenuButton;


    public LagerverwaltungGUI(LagerverwaltungData data) {
        this.d = data;
        this.setTitle("Lagerverwaltung");
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        menubar = new JMenuBar();

        //Datei-Menu
        dataMenu = new JMenu("Datei");
        menubar.add(dataMenu);

        exitMenuButton = new JMenuItem("Beenden");
        dataMenu.add(exitMenuButton);

        //Debug-Menu
        viewMenu = new JMenu("Ansicht");
        menubar.add(viewMenu);

        clearMenuButton = new JMenuItem("Clear");
        viewMenu.add(clearMenuButton);

        this.setJMenuBar(menubar);

        //Search-Panel
        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setBackground(Color.decode("0xd9d9d9"));

        labelShelfname = new JLabel("Regal");
        searchPanel.add(labelShelfname);

        dropdownShelf = new JComboBox(shelfnames);
        searchPanel.add(dropdownShelf);

        xcoord = new JLabel("x:");
        searchPanel.add(xcoord);

        textXcoord = new JTextField();
        searchPanel.add(textXcoord);
        textXcoord.setColumns(4);

        ycoord = new JLabel("y:");
        searchPanel.add(ycoord);

        textYcoord = new JTextField();
        searchPanel.add(textYcoord);
        textYcoord.setColumns(4);

        Border defaultBorder = textXcoord.getBorder();

        this.add(searchPanel);

        //Button-Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.decode("0xebebeb"));

        callButton = new JButton("Fach abfragen");
        callButton.setEnabled(false);
        buttonPanel.add(callButton);

        insertButton = new JButton("Item hinzufügen");
        insertButton.setEnabled(false);
        buttonPanel.add(insertButton);

        removeButton = new JButton("Fach leeren");
        removeButton.setEnabled(false);
        buttonPanel.add(removeButton);

        clearButton = new JButton("Clear");
        buttonPanel.add(clearButton);

        storageButton = new JButton("Lager anzeigen");
        buttonPanel.add(storageButton);

        this.add(buttonPanel);

        //Item-Panel
        itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(4, 2));
        itemPanel.setBackground(Color.decode("0xd9d9d9"));

        labelPartNo = new JLabel("Artikel-Nr.:");
        textPartNo = new JTextField();
        //textPartNo.setEditable(false);
        textPartNo.setColumns(10);

        labelArtName = new JLabel("Artikel-Name:");
        textArtName = new JTextField();
        //textArtName.setEditable(false);
        textArtName.setColumns(10);

        labelSize = new JLabel("Größe");
        textSize = new JTextField();
        //textSize.setEditable(false);
        textSize.setColumns(10);

        labelAmount = new JLabel("Anzahl");
        textAmount = new JTextField();
        //textAmount.setEditable(false);
        textAmount.setColumns(10);

        itemPanel.add(labelPartNo);
        itemPanel.add(textPartNo);
        itemPanel.add(labelArtName);
        itemPanel.add(textArtName);
        itemPanel.add(labelSize);
        itemPanel.add(textSize);
        itemPanel.add(labelAmount);
        itemPanel.add(textAmount);

        this.add(itemPanel);

        //Action-Listener
        callButton.addActionListener(e -> callItem());
        insertButton.addActionListener(e -> insertItem());
        exitMenuButton.addActionListener(e -> exit());
        clearButton.addActionListener(e -> clearAllTextfields());
        removeButton.addActionListener(e -> removeItem());
        storageButton.addActionListener(e -> showStorage());
        clearMenuButton.addActionListener(e -> clearAllTextfields());


        //Document-Listener
        DocumentListener documentListener = new DocumentListener(textArtName,textPartNo,textAmount,textSize,textXcoord,textYcoord,defaultBorder,insertButton, callButton, removeButton);
        textArtName.getDocument().addDocumentListener(documentListener);
        textPartNo.getDocument().addDocumentListener(documentListener);
        textAmount.getDocument().addDocumentListener(documentListener);
        textSize.getDocument().addDocumentListener(documentListener);
        textXcoord.getDocument().addDocumentListener(documentListener);
        textYcoord.getDocument().addDocumentListener(documentListener);

    }
    private void showStorage()
    {
        StoragecontentPane show = new StoragecontentPane(d.getStorage(), d);
    }
    private boolean checkCoordValues(JTextField textfield) {
        int value;
        try {
            value = Integer.parseInt(textfield.getText());
        } catch (NumberFormatException e) {
            System.err.println("[Error]: Value is not valid.");
            e.printStackTrace();
            textfield.setBorder(BorderFactory.createLineBorder(Color.decode("0xed3b3b"), 2));
            JOptionPane.showMessageDialog(this, "Die Felder der Koordinaten ernthalten keine gültigen Werte.", "Falsche Eingabe", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (value > 9 || value < 0) {
            System.err.println("[Error]: Shelf does not exist.");
            textfield.setBorder(BorderFactory.createLineBorder(Color.decode("0xed3b3b"), 2));
            JOptionPane.showMessageDialog(this, "Das angegebene Regalfach existiert nicht.", "Falsche Eingabe", JOptionPane.ERROR_MESSAGE);
            return false;

        }
        return true;
    }

    private void callItem() {
        if (checkCoordValues(textXcoord) && checkCoordValues(textYcoord)) {
            char shelfname = dropdownShelf.getSelectedItem().toString().charAt(0);
            int x = Integer.parseInt(textXcoord.getText());
            int y = Integer.parseInt(textYcoord.getText());
            Item item = null; //Dummy-Object

            if(d.isShelfUnitEmpty(shelfname,x,y)){
                JOptionPane.showMessageDialog(this, "Dieses Regalfach hat keinen Inhalt.", "Kein Inhalt", JOptionPane.WARNING_MESSAGE);
                clearAllTextfields();
                return;
            }

            try {
                item = d.getItem(shelfname, x, y);
                item.printDetails();
            } catch (NullPointerException e) {
                System.err.println("[Error]: Item not");
                System.err.println("[Error]: Nullpointerexception");
                e.printStackTrace();
                clearAllTextfields();
                JOptionPane.showMessageDialog(this, "Dieses Regalfach hat keinen Inhalt.", "Kein Inhalt", JOptionPane.WARNING_MESSAGE);
            }
            if (item != null) { //wenn die es keine Nullpointerexception gegeben hat.
                textPartNo.setText(String.valueOf(item.getPartNumber()));
                textArtName.setText(item.getArticleName());
                textAmount.setText(String.valueOf(item.getAmount()));
                textSize.setText(String.valueOf(item.getSize()));
            }
        }
    }

    private void insertItem() {
        if (!(checkCoordValues(textXcoord) && checkCoordValues(textYcoord)))
            return;

        if (submitDialog("Item einfügen", "Möchtest du " + textAmount.getText() + "x " + textArtName.getText() + " (Teile-Nr.: " + textPartNo.getText() + ") in das Lager übernehmen?")) {
            Item item;
            try {
                item = new Item(textArtName.getText(),
                        Integer.parseInt(textPartNo.getText()),
                        Double.parseDouble(textSize.getText()),
                        Integer.parseInt(textAmount.getText()),
                        dropdownShelf.getSelectedItem().toString().charAt(0),
                        Integer.parseInt(textXcoord.getText()),
                        Integer.parseInt(textYcoord.getText()));
            } catch (NumberFormatException e) {
                System.err.println("[Error]: NumberFormatException");
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Das Item enthält Werte, welche nicht übernommen werden konnten.", "Falsche Argumente", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int returnCode = d.insertItem(item, item.getShelf(), item.getXcoord(), item.getYcoord());

            if (returnCode == 0) {
                System.out.println("[System]: Insert successful");
                JOptionPane.showMessageDialog(this, "Das Item wurde erfolgreich hinzugefügt", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
            }

            if (returnCode == 1) {
                System.err.println("[Error]: Item too big for shelf unit");
                JOptionPane.showMessageDialog(this, "Das Item konnte nicht hinzugefügt werden.\nGrund: Das Item ist zu groß.", "Fehlgeschlagen", JOptionPane.ERROR_MESSAGE);
            }

            if (returnCode == 2) {
                System.err.println("[Error]: Shelf unit is occupied");
                JOptionPane.showMessageDialog(this, "Das Item konnte nicht hinzugefügt werden.\nGrund: Das Fach ist bereits belegt.", "Fehlgeschlagen", JOptionPane.ERROR_MESSAGE);
            }
        } else
            System.out.println("[System]: Insert submit denied");
    }

    private void removeItem() {
        try {
            char shelf = dropdownShelf.getSelectedItem().toString().charAt(0);
            if (!(checkCoordValues(textXcoord) && checkCoordValues(textYcoord)))
                return;
            int x = Integer.parseInt(textXcoord.getText());
            int y = Integer.parseInt(textYcoord.getText());
            Item item = d.getItem(shelf, x, y);
            if (submitDialog("Item entfernen", "Möchtest du das Item " + item.getArticleName() + " (Teile-Nr.: " + item.getPartNumber() + ") aus dem Lager entfernen?")) {
                if (d.removeItem(shelf, x, y)) {
                    System.out.println("[System]: Removal successful");
                    JOptionPane.showMessageDialog(this, "Das Entfernen war erfolgreich.", "Erfolgreich entfernt", JOptionPane.INFORMATION_MESSAGE);
                    clearAllTextfields();
                } else {
                    System.out.println("[System]: Removal not successful");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("[Error]: NumberFormatException");
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Das Item enthält Werte, welche nicht übernommen werden konnten.", "Falsche Argumente", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            System.out.println("[Error]: NullPointerException");
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Das Lager enthält kein Item in diesem Regalfach.", "Regal leer", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearAllTextfields() {
        textAmount.setText("");
        textSize.setText("");
        textPartNo.setText("");
        textArtName.setText("");
        textYcoord.setText("");
        textXcoord.setText("");
    }

    public void exit() {
        d.saveData();
        System.exit(0);
    }

    private boolean submitDialog(String title, String message) {
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            System.out.println("submitDialog: NO_OPTION");
        } else if (response == JOptionPane.YES_OPTION) {
            System.out.println("submitDialog: YES_OPTION");
            return true;
        } else if (response == JOptionPane.CLOSED_OPTION) {
            System.out.println("submitDialog: CLOSED_OPTION");
        }
        return false;
    }
}