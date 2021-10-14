import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserGroupManager {
    //public static final String teacherData="../Teacher/";
    //public static final String studentData="../Teacher/";
    public HashMap<String,Student> students;
    public HashMap<String,Teacher> teachers;
    public HashMap<Integer,Group> groups;
    public UserGroupManager(){
        students=new HashMap<>();
        teachers=new HashMap<>();
        groups=new HashMap<>();
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
        //s.getJoinedGroup().add(g.getName());
        g.addStudent(s);
        return true;
    }
    public boolean creatGroup(Teacher t, String name){
        Group g = new Group(t,name);
        groups.put(g.GetID(),g);
        return true;
    }
    public ArrayList<Group> createdBy(Teacher t){
        ArrayList<Group> result = new ArrayList<>();
        for(Group g : groups.values()){
            if(g.getTeacher().equals(t)){
                result.add(g);
            }
        }
        return result;
    }
}
