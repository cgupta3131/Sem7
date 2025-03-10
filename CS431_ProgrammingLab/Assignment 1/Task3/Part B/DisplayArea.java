package calc2;  

import javax.swing.JTextField;
import java.util.Stack; 
import java.awt.Color;
import java.awt.Font;

public class DisplayArea {
    public JTextField dField;

    DisplayArea()
    {
        initiateUserInterface();
    }

    public JTextField getDisplayField()
    {
        return dField;
    }

    /**
     * Sets the font of the display Field.
     * Also sets the background color.
     */
    private void setFont()
    {
        Font font = new Font("Monospaced", Font.BOLD, 20);
        dField.setFont(font);
        dField.setBackground(Color.WHITE);
    }

    /**
     * Initate the UI for the displayField to be displayed.
     * Sets location and font.
     * Also sets the size of the JTextField that needs to be displated.
     */
    private void initiateUserInterface()
    {
        dField = new JTextField("");

        dField.setLocation(Constants.PADDING, Constants.PADDING);
        setFont();

        dField.setEditable(false);
        dField.setSize(Constants.DISPLAY_FIELD_WIDTH, Constants.DISPLAY_FIELD_HEIGHT);
    }

    private boolean isNumber(char c)
    {
        return (c >= '0' && c <= '9');
    }

    /**
     * Validates that if the current expression in the dField is currect or not.
     * This can be validated by checking if the last current is a number of not.
     * @return
     */
    private boolean validate()
    {
        String cur = dField.getText();
        int lenField = cur.length();
        if(lenField == 0) //Empty Field
        {
            return false;
        }

        char lastCharacter = cur.charAt(lenField-1);
        if(!(isNumber(lastCharacter)))
            return false;
        return true;
    }

    /**
     * Appends a new character c to the textField.
     */
    public void appendChar(char c)
    {
        if(!isNumber(c)) //If any operand, then check for previous character in dField
        {
            if(!validate())
            {
                System.out.println("Operation not allowed.");
                return;
            }
        }
        
        String cur = dField.getText();

        if(isNumber(c))
            cur += c;
        else
            cur += " " + c + " ";

        dField.setText(cur);
    }

    /**
     * Clears out the whole text from the dField
     */
    public void clearText()
    {
        dField.setText("");
    }

    /**
     * Evaluates the expression on the JtextField.
     * If it is correct, displays the final answer on the TextField.
     * Else it will print out that Operation is not allowed.
     */
    public void evaluate()
    {
        String expression = dField.getText();
        EvaluateExp ev = new EvaluateExp(expression);
        Integer ans = ev.solve();

        if(ans == null)
            System.out.println("Operation Not Allowed");
        else
            dField.setText(Integer.toString(ans));
    }
}
