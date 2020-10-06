import java.util.*;

public class RoboticArm extends Thread {

    HeapOfSocks heapOfSocks;
    int roboticArmIndex;
    MatchMakingMachine matchingMachine;

    public RoboticArm(int num, HeapOfSocks h, MatchMakingMachine m)
    {
        roboticArmIndex = num;
        heapOfSocks = h;
        matchingMachine = m;
    }

    @Override
    public void run() {

        while(heapOfSocks.remainingSocks() > 0)
        {
            try {
                Sock s = heapOfSocks.selectFromHeap();
                if(s == null)
                    continue;

                System.out.println("Sock #" + s.getIndex() + " of color " + Constants.getColor(s.getColor()) + " picked up by Arm #" + roboticArmIndex);
                matchingMachine.addSock(s);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                
            }
        }
    }
}