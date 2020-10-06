package calc1;

import javax.swing.SwingWorker;
import javax.swing.JButton;

public class Scanner extends SwingWorker
{
    private Calc calculator;
    private int numID;
    private int functionID;

    Scanner(Calc calc)
    {
        calculator = calc;
        numID = 0;
        functionID = 0;
    }

    public int getNumID()
    {
        return numID;
    }

    public int getFunctionID()
    {
        return functionID;
    }

    public void resetID()
    {
        numID = 0;
        functionID = 0;
    }


    boolean updateCurrentNumberButton(JButton curButton)
    {
        try
        {
            curButton.setOpaque(true);
            calculator.repaint();
            Thread.sleep(Constants.SLEEP_TIME);
            curButton.setOpaque(false);
            calculator.repaint();
            return true;   
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    boolean updateCurrentFunctionButton(JButton curButton)
    {
        if(curButton == null)
            return false;

        try
        {
            curButton.setOpaque(true);
            calculator.repaint();
            Thread.sleep(Constants.SLEEP_TIME);
            curButton.setOpaque(false);
            calculator.repaint();
            return true;   
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
        
    }

    @Override
    protected String doInBackground() throws Exception 
    {
        while(true)
        {
            boolean numHighlighted = calculator.numberHighlighted;
            if(numHighlighted)
            {
                boolean curStatus = true;
                curStatus = updateCurrentNumberButton(calculator.nArea.getSingleButton(numID));
                if(!curStatus)
                    break;
                numID = (numID+1)%10;
            }
            else
            {
                boolean curStatus = true;
                curStatus = updateCurrentFunctionButton(calculator.fArea.getSingleButton(functionID));
    
                if(!curStatus)
                    break;
    
                functionID = (functionID+1)%4;
            }
        }

        return null;
    }
}
