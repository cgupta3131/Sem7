import java.util.*;

public class MatchMakingMachine extends Thread {

    private int matchedPairs = 0;
    private Map<Integer, Integer> matchedSocks;

    public MatchMakingMachine()
    {
        matchedSocks = new HashMap<Integer, Integer>(); //color and count
        matchedPairs = 0;
    }

    /**
     * For each sock color, if the current count is greater than or equal to 2, we decrease the count and increase the matchedPair.
     * Then the updated value is added into the map of matchedSocks.
     */
    @Override
    public void run()
    {
        while(matchedPairs < SockMatchingRoom.totalPairs)
        {
            synchronized(this)
            {
                for(Integer sockColor: matchedSocks.keySet())
                {
                    int currentCount = matchedSocks.get(sockColor);
                    while(currentCount >= 2)
                    {
                        currentCount -= 2;
                        System.out.println("\t\tMachine matched a pair of socks of color: " + Constants.getColor(sockColor) + " and pushed it to " + Constants.getColor(sockColor) + " shelf");
                        matchedPairs++;
                    }

                    matchedSocks.put(sockColor, currentCount);
                }
            }
        }

        System.out.println("Total Matched Pair Sock Pairs: " + matchedPairs);
    }

    /**
     * Adds a sock into the matchedSocks Map and increases the count by 1
     * @param s
     */
    public synchronized void addSock(Sock s)
    {
        int newCount = 0;
        if(matchedSocks.containsKey(s.getColor()))
            newCount = matchedSocks.get(s.getColor()) + 1;
        else
            newCount = 1;
        matchedSocks.put(s.getColor(), newCount) ;
    }

}