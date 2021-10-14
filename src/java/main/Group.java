import javafx.scene.Parent;

public class Group {
    private Student[] students;
    private Teacher teacher;
    private String name;
    private int ID;
    public static final int MAXNUMBER = 30;
    public Group(Teacher teacher, String name){
        this.teacher = teacher;
        this.name=name;
        this.students=new Student[Group.MAXNUMBER];
        this.ID = IDcreater.creat();
    }
    //public Group(){

    //}
    public boolean addStudent(Student student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                //student.getJoinedGroup().add(name);
                return true;
            }
        }
        return false;
    }
    public int GetID(){
        return this.ID;
    }
    public String getName(){
        return this.name;
    }
    public Teacher getTeacher(){
        return this.teacher;
    }

}
