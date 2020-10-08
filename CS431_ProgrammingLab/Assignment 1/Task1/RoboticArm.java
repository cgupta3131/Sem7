package sockmatching;

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

    /**
     * The particular Robotic Arm tries to pich the socks from the heap and if it is able to pick, it adds it to matchingMachine.
     */
    @Override
    public void run() {

        while(heapOfSocks.remainingSocks() > 0)
        {
            try {
                Sock s = heapOfSocks.selectFromHeap();
                if(s == null)
                    continue;

                System.out.println("Arm " + roboticArmIndex + ": Sock #" + s.getIndex() + " of color " + Constants.getColor(s.getColor()) );
                matchingMachine.addSock(s);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                
            }
        }
    }
}