package evalsystem;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

class EvaluationSystem 
{
    private ArrayList<Student> studentData; //will Store all the data of the Student
    private Map<String, ReentrantLock> rollLocks ;  //Roll No and it's particular Lock
    private ArrayList<InputEntry> inputBuffer ; //input Buffer
    private final static Scanner scanner = new Scanner(System.in);  // For reading input

    public EvaluationSystem()
    {
        inputBuffer = new ArrayList<>(); 
        studentData = new ArrayList<>();
        rollLocks = new HashMap<>(); 
    }

    public void updateDataOfStudent(String rollNumber, int marksDelta, String updater) throws Exception
    {
        if(!rollLocks.containsKey(rollNumber))
            throw new Exception("No Lock Associated with this Roll Number, Run the Program Again") ; 

        ReentrantLock rLock = rollLocks.get(rollNumber) ; 
        rLock.lock() ;
        try
        {
            Student st = updateMarksHelper(rollNumber, marksDelta, updater);
            if(st != null)
                System.out.println("\t\t" + st.rollNo + ": " + st.lastUpdatedBy + " updated marks to " + st.marks); 
        }
        finally
        {
            rLock.unlock() ; 
        }
    }
    
    private Student updateMarksHelper(String rollNumber, int marksDelta, String updater) 
    {
        for(Student st: studentData)
        {
            if(st.rollNo.equals(rollNumber))
            {
                String lastUpdater = st.lastUpdatedBy;
                if(lastUpdater.equals("CC") && !updater.equals("CC"))
                {
                    System.out.println("\t" + updater + " trying to update marks of a student which has been modified by CC. Operation Not Allowed");
                    return null ; 
                }

                int lastMarks = Integer.parseInt(st.marks);
                int currentMarks = lastMarks + marksDelta;
                st.marks = Integer.toString(currentMarks);
                st.lastUpdatedBy = updater;

                return st;
            }
        }

        System.out.println("Data for this roll doesn't exist");
        return null ; 
    }

    private void addNewEntry(String [] currentData)
    {
        studentData.add(new Student(currentData[0], currentData[1], currentData[2], currentData[3], currentData[4]));
        rollLocks.put(currentData[0], new ReentrantLock());
    }

    public void readInputData() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(Constants.INPUT_FILE)); //File from which the input has to be read
        String currentLine; //taking each line
        while ((currentLine = br.readLine()) != null) //continue till EOF is reached 
            addNewEntry(currentLine.split(","));
        br.close(); 
    }

    public static void main(String[] args) throws IOException
    {
        EvaluationSystem es = new EvaluationSystem() ; 
        es.readInputData() ;

        while(true)
        {
            int choice ; 
            System.out.println("Choose Option: \n"
                             + "1. Update Marks of a Student \n"
                             + "2. UpdateAllData \n"
                             + "3. Exit") ; 

            choice = scanner.nextInt() ; 
            if(choice == 1)
                es.takeInputFromUser();
            else if(choice == 2)
                es.updateAllMarks();
            else
                break;
        }

    }

    private void updateAllMarks() 
    {
        Instructor ta1 = new Instructor(this, Thread.NORM_PRIORITY) ; 
        Instructor ta2 = new Instructor(this, Thread.NORM_PRIORITY) ; 
        Instructor instructor = new Instructor(this, Thread.MAX_PRIORITY) ; 

        for(InputEntry entry: inputBuffer)
        {
            if(entry.teacher.equals(Constants.TA1))
                ta1.addInput(entry);
            else if(entry.teacher.equals(Constants.TA2))
                ta2.addInput(entry);
            else if(entry.teacher.equals(Constants.CC))
                instructor.addInput(entry);
        }

        inputBuffer.clear();

        ta1.start(); 
        ta2.start() ; 
        instructor.start() ; 
        
        try
        {
            instructor.join();
            ta1.join() ; 
            ta2.join() ; 
        }
        catch(Exception e)
        {
            e.printStackTrace() ;
        }

        FileGenerator fg = new FileGenerator(studentData);
        fg.generateAllFiles(); 
    }

    private void takeInputFromUser() 
    {
        String teacher = getTeacherDetails() ;
        String rollNum = getRollDetails() ; 
        String updateMarks = getMarksDetails() ;  

        inputBuffer.add(new InputEntry(teacher, rollNum, updateMarks));
    }

    private String getMarksDetails() 
    {
        System.out.println("How many marks you want to increase/decrease(Input -ve if you want to decrease)?");
        String s = Integer.toString(scanner.nextInt()) ; 
        return s ; 
    }

    private String getRollDetails() 
    {
        System.out.println("Enter Roll Number :- ") ;
        String s = scanner.next() ;

        for(Student st: studentData)
        {
            if(st.rollNo.equals(s))
                return s;
        }
        
        System.out.println("Roll Number does not exists, Pleae Try Again");
        return getRollDetails() ; 
    }

    private String getTeacherDetails() 
    {
        System.out.println("Enter Instructor's Alias: ");
        String s = scanner.next() ; 

        if(s.equals(Constants.CC) || s.equals(Constants.TA2) || s.equals(Constants.TA1))
            return s ; 

        System.out.println("Invalid Instructor's details, Please Try Again");
        return getTeacherDetails() ; 
    }
}