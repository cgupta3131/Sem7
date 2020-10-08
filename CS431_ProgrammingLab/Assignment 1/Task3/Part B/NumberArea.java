package calc2;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

public class NumberArea 
{
    private JPanel nPanel;
    private ArrayList<JButton> listOfNumberKeys;

    NumberArea()
    {
        listOfNumberKeys = new ArrayList<>();
        initiateUserInterface();
    }

    public JButton getSingleButton(int id)
    {
        return listOfNumberKeys.get(id);
    }

    public JPanel getNumberPanel()
    {
        return nPanel;
    }

    /**
     * Sets the border for the button
     */
    void setBorder(JButton btn)
    {
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        btn.setBorder(compound);
    }

    /**
     * Adds a new button with text as 'i'.
     * Also sets the background and font of that particular button
     * Sets the border and pushes into the list of Number Keys
     */
    void addNewButton(int i)
    {
        JButton numberKey = new JButton(Integer.toString(i));
        numberKey.setBackground(Color.YELLOW);
        numberKey.setOpaque(false);
        numberKey.setFont(new Font("Monospaced", Font.BOLD, 20));
        setBorder(numberKey);
        listOfNumberKeys.add(numberKey);
    }

    /**
     * Initiates the UI for numberPanel and sets it's size and location.
     * Adds all the 10 buttons (0-9) on the numberPanel
     */
    private void initiateUserInterface()
    {
        GridLayout layout = new GridLayout(4, 3);
        layout.setHgap(Constants.PADDING);
        layout.setVgap(Constants.PADDING);

        nPanel = new JPanel(layout);
        nPanel.setLocation(Constants.NUMBER_START_LOCATION_X, Constants.NUMBER_START_LOCATION_Y);
        nPanel.setSize(Constants.NUMBER_FIELD_WIDTH, Constants.NUMBER_FIELD_HEIGHT);

        for(int i = 0; i < 10; i++)
            addNewButton(i);

        for(int i = 9; i > 0; i--)
            nPanel.add(listOfNumberKeys.get(i));

        nPanel.add(listOfNumberKeys.get(0));
    }
}
