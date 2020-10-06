package calc2;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
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

    void addNewButton(int i)
    {
        JButton numberKey = new JButton(Integer.toString(i));
        numberKey.setBackground(Color.YELLOW);
        numberKey.setOpaque(false);
        listOfNumberKeys.add(numberKey);
    }

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
