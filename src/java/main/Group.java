
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Group {
    private Student[] students;
    private Teacher teacher;
    private String name;
    private int ID;
    public static final int MAXNUMBER = 30;
    public HashMap<Test, List<Answer>> tests;
    public Group(Teacher teacher, String name){
        this.teacher = teacher;
        this.name=name;
        this.students=new Student[Group.MAXNUMBER];
        this.ID = IDcreater.creat();
        tests=new HashMap<>();
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
    public boolean addTest(Test t){
        if(tests.containsKey(t)){
            return false;
        }
        tests.put(t,new ArrayList<>());
        return true;
    }
    public boolean answerTest(Answer a){
        if(tests.containsKey(a.getAbout())){
            tests.get(a.getAbout()).remove(a);
            tests.get(a.getAbout()).add(a);
            return true;
        }
        return false;
    }

}
