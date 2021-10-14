import javafx.scene.Parent;

public class Group {
    private String[] students;
    private String teacher;
    private String name;
    public static final int MAXNUMBER = 30;
    public Group(Teacher teacher){
        this.teacher = teacher.getEmail();
        this.students=new String[Group.MAXNUMBER];
    }
    public Group(){

    }
    public boolean addStudent(Student student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student.getEmail();
                student.getJoinedGroup().add(name);
                return true;
            }
        }
        return false;
    }
    public String getName(){
        return this.name;
    }

}
