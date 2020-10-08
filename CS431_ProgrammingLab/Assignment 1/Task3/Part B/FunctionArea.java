package calc2;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;  
import java.util.Map;  


import java.awt.Color;
import java.awt.GridLayout;

public class FunctionArea 
{
    private JPanel fPanel;
    private JButton addKey;
    private JButton subtractKey;
    private JButton productKey;
    private JButton divisionKey;

    FunctionArea()
    {
        initiateUserInterface();
    }

    public JPanel getFunctionPanel()
    {
        return fPanel;
    }

    /**
     * Returns a single button based on the ID provided.
     */
    public JButton getSingleButton(int id)
    {
        if(id == 0)
            return addKey;
        if(id == 1)
            return subtractKey;
        if(id == 2)
            return productKey;
        if(id == 3)
            return divisionKey;
        
        return null;
    }    

    /**
     * Sets all the property of btn such as background, Borders and Font.
     */
    JButton setProperty(JButton btn)
    {
        btn.setBackground(Color.GREEN);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        btn.setBorder(compound);
        btn.setOpaque(false);
        btn.setFont(new Font("Monospaced", Font.BOLD, 25));
        return btn;
    }

    /**
     * Adds all the buttons(operators) to the Function Panel
     */
    void addAllButtons()
    {
        addKey = new JButton("+");
        addKey = setProperty(addKey);
        fPanel.add(addKey);

        subtractKey = new JButton("-");
        subtractKey = setProperty(subtractKey);
        fPanel.add(subtractKey);
        
        productKey = new JButton("*");
        productKey = setProperty(productKey);
        fPanel.add(productKey);

        divisionKey = new JButton("/");
        divisionKey = setProperty(divisionKey);
        fPanel.add(divisionKey);
    }

    /**
     * Initiates the UI for functionPanel by setting the layout details.
     * Also sets the location and size of the functionPanel.
     */
    void initiateUserInterface()
    {
        GridLayout layout = new GridLayout(2, 3);
        layout.setHgap(Constants.PADDING);
        layout.setVgap(Constants.PADDING);

        fPanel = new JPanel(layout);
        fPanel.setLocation(Constants.FUNTION_START_LOCATION_X, Constants.FUNCTION_START_LOCATION_Y);
        fPanel.setSize(Constants.FUNCTION_FIELD_WIDTH, Constants.FUNCTION_FIELD_HEIGHT);

        addAllButtons();
    }
}
