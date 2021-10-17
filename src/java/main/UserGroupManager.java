import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserGroupManager {
    //public static final String teacherData="../Teacher/";
    //public static final String studentData="../Teacher/";
    //public HashMap<String,Student> students;
    //public HashMap<String,Teacher> teachers;
    public HashMap<Integer,Group> groups;
    public UserGroupManager(){
        groups=new HashMap<>();
    }



    public boolean addStudentToGroup(String s, Group g){
        //s.getJoinedGroup().add(g.getName());
        g.addStudent(s);
        return true;
    }
    public boolean creatGroup(String t, String name){
        Group g = new Group(t,name);
        groups.put(g.GetID(),g);
        return true;
    }
    public ArrayList<Integer> createdBy(String t){
        ArrayList<Integer> result = new ArrayList<>();
        for(Group g : groups.values()){
            if(g.getTeacher().equals(t)){
                result.add(g.GetID());
            }
        }
        return result;
    }
    public String getNameOfGroup(int i){
        return groups.get(i).getName();
    }
}
