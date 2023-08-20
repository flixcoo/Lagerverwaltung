import javax.swing.*;

public class Lagerverwaltung {
    private final LagerverwaltungData data;
    private final LagerverwaltungGUI gui;

    public Lagerverwaltung() {
        data = new LagerverwaltungData();
        gui = new LagerverwaltungGUI(data);
    }

    public static void main(String[] args) {
        Lagerverwaltung lagerverwaltung = new Lagerverwaltung();
        //Sichtbarkeit des Fensters
        lagerverwaltung.gui.pack();
        lagerverwaltung.gui.setSize(400, 300);
        lagerverwaltung.gui.setLocationRelativeTo(null);
        lagerverwaltung.gui.setVisible(true);
        lagerverwaltung.gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}