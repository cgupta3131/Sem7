package calc2;

import javax.swing.SwingWorker;
import javax.swing.JButton;


public class NumberScanner extends SwingWorker 
{
    private Calc calculator;
    private int id;

    NumberScanner(Calc calc)
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
            curStatus = updateCurrentButton(calculator.nArea.getSingleButton(id));

            if(!curStatus)
                break;

            id = (id+1)%10;
        }

        return null;
    }
}
