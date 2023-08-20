import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;

public class DocumentListener implements javax.swing.event.DocumentListener {
    JTextField textArtName;
    JTextField textPartNo;
    JTextField textAmount;
    JTextField textSize;
    JTextField textXcoord;
    JTextField textYcoord;
    Border defaultBorder;
    JButton insertButton;
    JButton callButton;
    JButton removeButton;

    public DocumentListener(JTextField textArtName, JTextField textPartNo, JTextField textAmount, JTextField textSize, JTextField textXcoord, JTextField textYcoord, Border defaultBorder, JButton insertButton, JButton callButton, JButton removeButton) {
        this.textArtName = textArtName;
        this.textPartNo = textPartNo;
        this.textAmount = textAmount;
        this.textSize = textSize;
        this.textXcoord = textXcoord;
        this.textYcoord = textYcoord;
        this.defaultBorder = defaultBorder;
        this.insertButton = insertButton;
        this.callButton = callButton;
        this.removeButton = removeButton;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        this.changed();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        this.changed();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        this.changed();
    }

    public void changed() {
        if (textYcoord.getText().equals("") || textXcoord.getText().equals("")) {
            callButton.setEnabled(false);
            removeButton.setEnabled(false);
            insertButton.setEnabled(false);
            return;
        }
        insertButton.setEnabled(!((textArtName.getText().equals("") || textPartNo.getText().equals("")) ||
                (textSize.getText().equals("") || textAmount.getText().equals(""))) ||
                (textXcoord.getText().equals("") || textYcoord.getText().equals("")));
        callButton.setEnabled(true);
        removeButton.setEnabled(true);

        textXcoord.setBorder(defaultBorder);
        textYcoord.setBorder(defaultBorder);
        textArtName.setBorder(defaultBorder);
        textPartNo.setBorder(defaultBorder);
        textSize.setBorder(defaultBorder);
        textAmount.setBorder(defaultBorder);
    }
}
