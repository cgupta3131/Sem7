package evalsystem;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class FileGenerator {

    ArrayList<Student> studentData; 

    public FileGenerator(ArrayList<Student> studentData)
    {
        this.studentData = studentData;
    }

    public void generateAllFiles()
    {
        generateOutputFile();
        generateSortedRollFile();
        generateSortedNameFile();
    }

    private BufferedWriter addNewLIine(BufferedWriter writer, Student st)
    {
        try {
            writer.append(st.rollNo);
            writer.append(",");
            writer.append(st.name);
            writer.append(",");
            writer.append(st.email);
            writer.append(",");
            writer.append(st.marks);
            writer.append(",");
            writer.append(st.lastUpdatedBy);
            writer.append("\n");

            return writer;
        } 
        catch (IOException e) {
            e.printStackTrace() ;
        }
        
        return null;
    }

    private void updateFile(String fileName, ArrayList<Student> data) throws IOException
    {
        BufferedWriter writer = null ; 

        try {
            writer = new BufferedWriter(new FileWriter(fileName)) ;
        } 
        catch (IOException e) {
            e.printStackTrace() ;
        }

        assert(writer != null);
        try {
            for(Student st: data)
                writer = addNewLIine(writer, st);

            writer.flush();
            writer.close();

        } 
        catch (Exception e) {
            e.printStackTrace() ; 
        }
    }

    private void generateSortedRollFile() 
    {
        final Comparator<Student> RollComparator = new Comparator<Student>()
        {
            public int compare(Student s1, Student s2)
            {
                String roll1 = s1.rollNo;
                String roll2 = s2.rollNo;
                return roll1.compareTo(roll2);
            }
        };

        ArrayList<Student> sortedRoll = new ArrayList<Student>(studentData);
        Collections.sort(sortedRoll, RollComparator) ;

        try {
            updateFile(Constants.FINAL_FILE_SORTED_ROLL, sortedRoll);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateSortedNameFile() 
    {
        final Comparator<Student> NameComparator = new Comparator<Student>()
        {
            public int compare(Student s1, Student s2)
            {
                String roll1 = s1.name;
                String roll2 = s2.name;
                return roll1.compareTo(roll2);
            }
        };

        ArrayList<Student> sortedName = new ArrayList<Student>(studentData);
        Collections.sort(sortedName, NameComparator) ;

        try {
            updateFile(Constants.FINAL_FILE_SORTED_NAME, sortedName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateOutputFile() 
    {
        ArrayList<Student> cloneData = new ArrayList<Student>(studentData);

        try {
            updateFile(Constants.FINAL_FILE, cloneData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
