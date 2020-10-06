package calc2;

import javax.swing.JPanel;
import javax.swing.JButton;
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

    JButton setProperty(JButton btn)
    {
        btn.setBackground(Color.CYAN);
        btn.setOpaque(false);
        return btn;
    }

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
