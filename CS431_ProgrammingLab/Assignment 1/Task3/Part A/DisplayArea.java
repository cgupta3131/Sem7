package calc1;

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

    private static boolean hasPrecedence(char op1, char op2)
    { 
        if (op2 == '(' || op2 == ')'){
            return false; 
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')){
            return false;
        } 
        return true; 
    }

    private static int applyOperation(char op, int b, int a) 
    { 
        if(op == '+')
            return a+b;
        else if(op == '-')
            return a-b;
        else if(op == '*')
            return a*b;
        else if(op == '/')
        {
            if(b == 0)
                throw new UnsupportedOperationException("Cannot divide by zero");
            else
                return a/b;
        }
        return 0;
    } 

    public void evaluate()
    {
        String expression = dField.getText();
        if(expression.length() == 0)
        {
            return;
        }

        char[] tokens = expression.toCharArray();
        int tokenLength = tokens.length;
        char c = tokens[tokenLength-1];

        if(!(c >= '0' && c <= '9')) //If last character is not an Integer, then operation is not allowed
        {
            System.out.println("Operation not allowed.");
            return;
        }

        Stack<Integer> values = new Stack<Integer>(); 
        Stack<Character> ops = new Stack<Character>();

        for(int i = 0; i < tokenLength; i++)
        {
            if(tokens[i] == ' ')
                continue;
            
            if( isNumber(tokens[i]) )
            { 
                StringBuffer stringBuff = new StringBuffer();
                while (i < tokenLength && isNumber(tokens[i]))
                stringBuff.append(tokens[i++]);
                values.push(Integer.parseInt(stringBuff.toString()));
            }

            else if(!isNumber(tokens[i])) 
            { 
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())){
                    values.push(applyOperation(ops.pop(), values.pop(), values.pop())); 
                }
                ops.push(tokens[i]);
            } 
        }

        while (!ops.empty())
            values.push(applyOperation(ops.pop(), values.pop(), values.pop())); 

        dField.setText(Integer.toString(values.pop()));
    }
}
