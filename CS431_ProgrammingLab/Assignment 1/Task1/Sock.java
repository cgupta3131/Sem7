package sockmatching;

public class Sock {

    private Integer sockIndex;
    private Integer sockColor;
    private boolean isPicked;

    public Sock(Integer c, Integer i)
    {
        sockColor = c;
        sockIndex = i;
        isPicked = false;
    }

    /**
     * Picks the sock in a synchronous way, such that no other thread can access this function unless the current thread is done with this.
     */
    public synchronized boolean pickUpSock()
    {
        boolean curStatus = isPicked;
        isPicked = true;
        return !curStatus;
    }

    /**
     * gets the color of the sock
     */
    public Integer getColor() 
    {
        return sockColor;
    }

    /**
     * Gets the index number of the sock
     */
    public Integer getIndex()
    {
        return sockIndex;
    }
}