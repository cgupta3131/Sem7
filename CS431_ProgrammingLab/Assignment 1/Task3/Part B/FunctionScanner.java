package calc2;

import javax.swing.SwingWorker;
import javax.swing.JButton;

public class FunctionScanner extends SwingWorker 
{
    private Calc calculator;
    private int id;

    FunctionScanner(Calc calc)
    {
        calculator = calc;
        id = 0;
    }

    public int getId()
    {
        return id;
    }

    boolean updateCurrentButton(JButton curButton)
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
            boolean curStatus = true;
            curStatus = updateCurrentButton(calculator.fArea.getSingleButton(id));

            if(!curStatus)
                break;

            id = (id+1)%4;
        }

        return null;
    }
}
