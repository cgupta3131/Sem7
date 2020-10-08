package calc2;

import javax.swing.JTextField;
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

    private void setFont()
    {
        Font font = new Font("SansSerif", Font.PLAIN, 20);
        dField.setFont(font);
        dField.setBackground(Color.WHITE);
    }

    private void initiateUserInterface()
    {
        dField = new JTextField("");

        dField.setLocation(Constants.PADDING, Constants.PADDING);
        setFont();

        dField.setEditable(false);
        dField.setSize(Constants.DISPLAY_FIELD_WIDTH, Constants.DISPLAY_FIELD_HEIGHT);
    }

    private boolean validate()
    {
        String cur = dField.getText();
        int lenField = cur.length();
        if(lenField == 0) //Empty Field
        {
            return false;
        }

        char lastCharacter = cur.charAt(lenField-1);
        if(!(lastCharacter >= '0' && lastCharacter <= '9'))
            return false;
        return true;
    }

    private boolean isNumber(char c)
    {
        return (c >= '0' && c <= '9');
    }

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

    public void clearText()
    {
        dField.setText("");
    }

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
