import java.io.Serializable;
import java.util.HashMap;

public class UserGroupManager {
    //public static final String teacherData="../Teacher/";
    //public static final String studentData="../Teacher/";
    public HashMap<String,Student> students;
    public HashMap<String,Teacher> teachers;
    public UserGroupManager(){
        students=new HashMap<>();
        teachers=new HashMap<>();
    }
    public boolean createTeacher(String username, String password, String email){
        if(teachers.containsKey(email)){
            return false;
        }
        teachers.put(email,new Teacher(username, password, email));
        return true;
    }
    public boolean createStudent(String username, String password, String email){
       if(students.containsKey(email)){
            return false;
       }
       students.put(email,new Student(username, password, email));
        return true;
    }

    public boolean addStudentToGroup(Student s, Group g){
        s.getJoinedGroup().add(g.getName());
        g.addStudent(s);
        return true;
    }
}
