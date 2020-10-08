package evalsystem;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Instructor extends Thread
{
    EvaluationSystem es; 
    private ArrayList<InputEntry> bufferList;

    public Instructor(EvaluationSystem evaluationSystem, int priority)
    {
        this.es = evaluationSystem; 
        this.setPriority(priority);
        bufferList = new ArrayList<>(); 
    }

    void addInput(InputEntry entry)
    {
        bufferList.add(entry);
    }

    @Override
    public void run()
    {
        while(!bufferList.isEmpty())
        {
            InputEntry ip = bufferList.get(0) ;
            try {
                es.updateDataOfStudent(ip.studentRoll, Integer.parseInt(ip.marksDelta), ip.teacher);
                bufferList.remove(0) ; 
            }
            catch(Exception e) {
                e.printStackTrace() ; 
            }
        }
    }
}