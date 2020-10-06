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

    public int remainingSocks()
    {
        return remainingSocks;
    }
}