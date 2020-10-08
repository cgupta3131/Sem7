package calc2;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class InfoArea {
    
    public JTextField iField;

    InfoArea()
    {
        initiateUserInterface();
    }

    public JTextField getInfoField()
    {
        return iField;
    }

    /**
     * Sets the font for the Information Text Field
     */
    private void setFont()
    {
        Font font = new Font("SansSerif", Font.BOLD, 12);
        iField.setFont(font);
        iField.setBackground(Color.RED);
        iField.setHorizontalAlignment(JTextField.CENTER);
    }
    
    /**
     * Initates the UI for Info Text
     * Sets the location and size
     */
    private void initiateUserInterface()
    {
        iField = new JTextField("Press E to evaluate and C to clear the expression");

        iField.setLocation(Constants.INFO_START_LOCATION_X, Constants.INFO_START_LOCATION_Y);
        setFont();

        iField.setEditable(false);
        iField.setSize(Constants.DISPLAY_FIELD_WIDTH, Constants.DISPLAY_FIELD_HEIGHT);
    }
}
