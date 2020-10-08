package calc2;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Calc extends JFrame implements KeyListener 
{
    DisplayArea dArea;
    NumberArea nArea;
    FunctionArea fArea;
    InfoArea iArea;
    NumberScanner nScanner;
    FunctionScanner fScanner;

    public Calc()
    {
        super("Calculator 2.0");
        createUI();
        nScanner = new NumberScanner(this);
        fScanner = new FunctionScanner(this);
    }

    /**
     * Creates the UI for the calculator
     * Sets the size and addes the displayTextField, NumberPanel, FunctionPanel and InfoTextField
     */
    private void createUI()
    {
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.getContentPane().setLayout(null);

        dArea = new DisplayArea();
        this.add(dArea.getDisplayField());
        
        nArea = new NumberArea();
        this.add(nArea.getNumberPanel());

        fArea = new FunctionArea();
        this.add(fArea.getFunctionPanel());

        iArea = new InfoArea();
        this.add(iArea.getInfoField());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    void start()
    {
        nScanner.execute();
        fScanner.execute();
    }

    /**
     * Key press event based on the current Key presses 'e'
     * If enter is pressed, we need to get the number on which current highlighter was and append it to displayField.
     * If SpaceBar is pressed, we need to get the operand on which the current highlihter was and append it to displayField.
     * If key E is pressed, we need to evaluate the whole expression present on the DisplayField currently.
     * If key C is press, we need to clear the DisplayField completely.
     */
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) //add a new digit to the dArea
        {
            char c = (char)(nScanner.getId() + '0');
            dArea.appendChar(c);
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE) //functions and evaluate and clearText
        {
            int functionID = fScanner.getId();
            if(functionID == 0)
                dArea.appendChar('+');
            else if(functionID == 1)
                dArea.appendChar('-');
            else if(functionID == 2)
                dArea.appendChar('*');
            else if(functionID == 3)
                dArea.appendChar('/');
        }
        else if(e.getKeyCode() == KeyEvent.VK_E)
            dArea.evaluate();
        else if(e.getKeyCode() == KeyEvent.VK_C)
            dArea.clearText();

        this.repaint();
    }

    public void keyReleased(KeyEvent e)
    {
        
    }

    public void keyTyped(KeyEvent e)
    {
        
    }

    public static void main(String[] args)
    {
        Calc calc = new Calc();
        calc.start();
    }
}