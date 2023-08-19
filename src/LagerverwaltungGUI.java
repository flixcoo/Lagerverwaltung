import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class LagerverwaltungGUI extends JFrame {
    private final String[] shelfnames = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private final String standardText = "Artikelnummer:\nArtikelname\nGroesse:\nAnzahl";
    private LagerverwaltungData d;

    //Search-Area
    private JOptionPane optionPane;
    private JPanel searchPanel;
    private JLabel labelShelfname;
    private JLabel xcoord;
    private JLabel ycoord;
    private JTextField textXcoord;
    private JTextField textYcoord;

    //Button-Area
    private JPanel buttonPanel;
    private JButton callButton;
    private JButton insertButton;
    private JButton removeButton;
    private JButton clearButton;

    //Item-Area
    private JPanel itemPanel;
    private JLabel labelArtName;
    private JLabel labelPartNo;
    private JLabel labelSize;
    private JLabel labelAmount;
    private JTextField textArtName;
    private JTextField textPartNo;
    private JTextField textSize;
    private JTextField textAmount;


    //Menu
    private JMenuBar menubar;
    private JMenu dataMenuButton;
    private JMenu itemMenuButton;
    private JMenu debugMenuButton;
    private JMenuItem exitButton;
    private JMenuItem addItemButton;
    private JMenuItem removeItemButton;
    private JComboBox dropdown;

    public LagerverwaltungGUI(LagerverwaltungData data) {
        this.d = data;


        this.setTitle("Lagerverwaltung");
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        menubar = new JMenuBar();

        //Datei-Menu
        dataMenuButton = new JMenu("Datei");
        menubar.add(dataMenuButton);

        exitButton = new JMenuItem("Beenden");
        dataMenuButton.add(exitButton);

        //Debug-Menu
        debugMenuButton = new JMenu("Debug");
        menubar.add(debugMenuButton);

        //Item-Menu
        itemMenuButton = new JMenu("Item");
        menubar.add(itemMenuButton);

        addItemButton = new JMenuItem("Item hinzufügen");
        itemMenuButton.add(addItemButton);

        removeItemButton = new JMenuItem("Item entfernen");
        itemMenuButton.add(removeItemButton);


        this.setJMenuBar(menubar);

        //Search-Panel
        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setBackground(Color.decode("0xd9d9d9"));

        labelShelfname = new JLabel("Regal");
        searchPanel.add(labelShelfname);

        dropdown = new JComboBox(shelfnames);
        searchPanel.add(dropdown);

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

        removeButton = new JButton("Item entfernen");
        removeButton.setEnabled(false);
        buttonPanel.add(removeButton);

        clearButton = new JButton("Clear");
        buttonPanel.add(clearButton);

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

        labelSize = new JLabel("Groesse");
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
        exitButton.addActionListener(e -> exit());
        addItemButton.addActionListener((e -> insertItem()));
        clearButton.addActionListener(e -> clearAllTextfields());


        //Document-Listener
        textXcoord.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }
            public void removeUpdate(DocumentEvent e) {
                changed();
            }
            public void insertUpdate(DocumentEvent e) {
                changed();
            }
            public void changed() {
                if (textYcoord.getText().equals("") || textXcoord.getText().equals("")) {
                    callButton.setEnabled(false);
                    removeButton.setEnabled(false);
                    insertButton.setEnabled(false);
                } else if(!((textArtName.getText().equals("") && textPartNo.getText().equals("")) &&
                        (textSize.getText().equals("") && textAmount.getText().equals(""))) &&
                        (textXcoord.getText().equals("") && textYcoord.getText().equals(""))) {
                    insertButton.setEnabled(true);
                } else {
                    callButton.setEnabled(true);
                    removeButton.setEnabled(true);
                }
                textXcoord.setBorder(defaultBorder);
                textYcoord.setBorder(defaultBorder);
            }
        });

        textYcoord.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }
            public void removeUpdate(DocumentEvent e) {
                changed();
            }
            public void insertUpdate(DocumentEvent e) {
                changed();
            }
            private void changed() {
                if (textYcoord.getText().equals("") || textXcoord.getText().equals("")) {
                    callButton.setEnabled(false);
                    removeButton.setEnabled(false);
                    insertButton.setEnabled(false);
                } else if(!((textArtName.getText().equals("") && textPartNo.getText().equals("")) &&
                        (textSize.getText().equals("") && textAmount.getText().equals(""))) &&
                        (textXcoord.getText().equals("") && textYcoord.getText().equals(""))) {
                    insertButton.setEnabled(true);
                } else {
                    callButton.setEnabled(true);
                    removeButton.setEnabled(true);
                }
                textXcoord.setBorder(defaultBorder);
                textYcoord.setBorder(defaultBorder);
            }
        });

        textArtName.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                if (((textArtName.getText().equals("") || textPartNo.getText().equals("")) ||
                        (textSize.getText().equals("") || textAmount.getText().equals(""))) ||
                        (textXcoord.getText().equals("") || textYcoord.getText().equals(""))) {
                    insertButton.setEnabled(false);
                } else {
                    insertButton.setEnabled(true);
                }
                textArtName.setBorder(defaultBorder);
                textPartNo.setBorder(defaultBorder);
                textSize.setBorder(defaultBorder);
                textAmount.setBorder(defaultBorder);
            }
        });

        textPartNo.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                if (((textArtName.getText().equals("") || textPartNo.getText().equals("")) ||
                        (textSize.getText().equals("") || textAmount.getText().equals(""))) ||
                        (textXcoord.getText().equals("") || textYcoord.getText().equals(""))) {
                    insertButton.setEnabled(false);
                } else {
                    insertButton.setEnabled(true);
                }
                textArtName.setBorder(defaultBorder);
                textPartNo.setBorder(defaultBorder);
                textSize.setBorder(defaultBorder);
                textAmount.setBorder(defaultBorder);
            }
        });

        textSize.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                if (((textArtName.getText().equals("") || textPartNo.getText().equals("")) ||
                        (textSize.getText().equals("") || textAmount.getText().equals(""))) ||
                        (textXcoord.getText().equals("") || textYcoord.getText().equals(""))) {
                    insertButton.setEnabled(false);
                } else {
                    insertButton.setEnabled(true);
                }
                textArtName.setBorder(defaultBorder);
                textPartNo.setBorder(defaultBorder);
                textSize.setBorder(defaultBorder);
                textAmount.setBorder(defaultBorder);
            }
        });

        textAmount.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                if (((textArtName.getText().equals("") || textPartNo.getText().equals("")) ||
                        (textSize.getText().equals("") || textAmount.getText().equals(""))) ||
                        (textXcoord.getText().equals("") || textYcoord.getText().equals(""))) {
                    insertButton.setEnabled(false);
                } else {
                    insertButton.setEnabled(true);
                }
                textArtName.setBorder(defaultBorder);
                textPartNo.setBorder(defaultBorder);
                textSize.setBorder(defaultBorder);
                textAmount.setBorder(defaultBorder);
            }
        });
    }

    private boolean checkCoordValues(JTextField textfield) {
        int value = -1;
        try {
            value = Integer.parseInt(textfield.getText());

        } catch (NumberFormatException e) {
            System.err.println("[Error]: Value is not valid.");
            textfield.setBorder(BorderFactory.createLineBorder(Color.decode("0xed3b3b"), 2));
            JOptionPane.showMessageDialog(this, "Die Felder der Koordinaten ernthalten keine gültigen Werte.", "Falsche Eingabe", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (value > 9 || value < 0) {
            System.err.println("[Error]: Shelf does not exist.");
            textfield.setBorder(BorderFactory.createLineBorder(Color.decode("0xed3b3b"), 2));
            JOptionPane.showMessageDialog(this, "Das angegebene Regalfach existiert nicht.", "Falsche Eingabe", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void callItem() {
        if (checkCoordValues(textXcoord) && checkCoordValues(textYcoord)) {
            char shelfname = dropdown.getSelectedItem().toString().charAt(0);
            int x = Integer.parseInt(textXcoord.getText());
            int y = Integer.parseInt(textYcoord.getText());
            Item item = null; //Dummy-Object

            try {
                item = d.getItem(shelfname, x, y);
                item.printDetails();
            } catch (NullPointerException e) {
                System.err.println("[Error]: Nullpointerexception");
                JOptionPane.showMessageDialog(this, "Dieses Regalfach hat keinen Inhalt.", "Kein Inhalt", JOptionPane.INFORMATION_MESSAGE);
            }
            if (item != null) { //wenn die es keine Nullpointerexception gegeben hat.
                textPartNo.setText(String.valueOf(item.getPartNumber()));
                textArtName.setText(item.getArticleName());
                textAmount.setText(String.valueOf(item.getAmount()));
                textSize.setText(String.valueOf(item.getSize()));
            }
        } else
            return;
    }

    private void insertItem() {
        if (checkCoordValues(textXcoord) && checkCoordValues(textYcoord))
            return;
        if (submitDialog("Item einfuegen", "Moechtest du das Item " + textArtName.getText() + " (Teile-Nr.: " + textPartNo.getText() + ") in das Lager übernehmen?")) {
            try {
                Item item = new Item(textArtName.getText(),
                        Integer.parseInt(textPartNo.getText()),
                        Integer.parseInt(textSize.getText()),
                        Integer.parseInt(textAmount.getText()),
                        dropdown.getSelectedItem().toString().charAt(0),
                        Integer.parseInt(textXcoord.getText()),
                        Integer.parseInt(textYcoord.getText()));
                d.insertItem(item, item.getShelf(), item.getXcoord(), item.getYcoord());
            } catch (NumberFormatException e) {
                System.err.println("[Error]: NumberFormatException");
                JOptionPane.showMessageDialog(this, "Das Item enthält Werte, welche nicht übernommen werden konnten.", "Falsche Argumente", JOptionPane.INFORMATION_MESSAGE);
            }
        } else
            System.out.println("[info]: Submit denied");
    }

    private void insertItemButton() {
        JPanel popupPanel = new JPanel();

        JTextField artName = new JTextField(10);
        JTextField partNo = new JTextField(5);
        JTextField size = new JTextField(10);
        JTextField amount = new JTextField(5);
        JTextField xCoord = new JTextField(2);
        JTextField yCoord = new JTextField(2);


        popupPanel.add(new JLabel("Artikelname:"));
        popupPanel.add(artName);
        popupPanel.add(new JLabel("Teile-Nr.:"));
        popupPanel.add(partNo);
        popupPanel.add(new JLabel("Groesse:"));
        popupPanel.add(size);
        popupPanel.add(new JLabel("Anzahl:"));
        popupPanel.add(amount);
        popupPanel.add(Box.createHorizontalStrut(15)); // a spacer
        popupPanel.add(Box.createVerticalStrut(5));
        popupPanel.add(new JLabel("Regal:"));
        popupPanel.add(dropdown);
        popupPanel.add(new JLabel("x:"));
        popupPanel.add(xCoord);
        popupPanel.add(new JLabel("y:"));
        popupPanel.add(yCoord);

        int result = JOptionPane.showConfirmDialog(this, popupPanel,
                "Artikel hinzufuegen", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String tempArtName = artName.getText();
            int tempPartNo = Integer.parseInt(partNo.getText());
            double tempSize = Double.valueOf(size.getText());
            int tempAmount = Integer.parseInt(amount.getText());

            if (!(checkCoordValues(xCoord) && checkCoordValues(yCoord)))
                return;
            int tempX = Integer.parseInt(xCoord.getText());
            int tempY = Integer.parseInt(xCoord.getText());

            if (submitDialog("Item einfuegen", "Moechtest du das Item " + tempArtName + " (Teile-Nr.: " + tempPartNo + ") in das Lager übernehmen?")) {
                Item item = new Item(tempArtName, tempPartNo, tempSize, tempAmount, dropdown.getSelectedItem().toString().charAt(0), tempX, tempY);
                d.insertItem(item, item.getShelf(), item.getXcoord(), item.getYcoord());
            } else
                System.out.println("[Error]: Submit denied");
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