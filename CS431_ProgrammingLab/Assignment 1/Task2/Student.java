package evalsystem;

public class Student 
{
    public String rollNo;
    public String name;
    public String email;
    public String marks;
    public String lastUpdatedBy;

    public Student(String rollNo, String name, String email, String marks, String lastUpdatedBy)
    {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.marks = marks;
        this.lastUpdatedBy = lastUpdatedBy;
    }
}