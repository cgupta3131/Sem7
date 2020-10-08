package sockmatching;

import java.util.*;

public class HeapOfSocks {
    List<Sock> socks;
    private int remainingSocks;
    private int curIdx;

    public HeapOfSocks(List<Sock> s)
    {
        curIdx = 0;
        socks = s;
        remainingSocks = s.size();
    }

    /**
     * Selects a particular sock(top of the heap) and checks if that particular sock is pickable(success = true)
     * If it is, then we remove the sock from the heap(increase pointer) and decrease remainingSocks count. All this is done in a synchronous manner
     * @return
     */
    public Sock selectFromHeap()
    {
        Sock s = socks.get(curIdx);
        boolean success = s.pickUpSock();
        
        if(success) 
        {
            synchronized(this)
            {
                curIdx++;
                remainingSocks--;
            }
            return s;
        }
        else
            return null;
    }

    /**
     * Returns the count of remaining socks
     */
    public int remainingSocks()
    {
        return remainingSocks;
    }
}