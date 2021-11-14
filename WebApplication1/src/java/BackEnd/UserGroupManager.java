package BackEnd;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserGroupManager {
    //public static final String teacherData="../Teacher/";
    //public static final String studentData="../Teacher/";
    //public HashMap<String,Student> students;
    //public HashMap<String,Teacher> teachers;
    public HashMap<Integer, Group> groups;

    public UserGroupManager() {
        groups = new HashMap<>();
        creatGroup(2,"aaa");
        addStudentToGroup(3,1); 
    }

    public UserGroupManager(HashMap<Integer, Group> groups) {
        this.groups = groups; 
    }


    public boolean addStudentToGroup(int s, Integer group) {
        return groups.get(group).addStudent(s);
    }

    public boolean removeStudentFromGroup(int s, Integer group) {
        return groups.get(group).removeStudent(s);

    }

    public boolean creatGroup(int t, String name) {
        Group g = new Group(t, name);
        groups.put(g.GetID(), g);
        return true;
    }

    public ArrayList<Integer> createdBy(int t) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Group g : groups.values()) {
            if (g.getTeacher()==(t)) {
                result.add(g.GetID());
            }
        }
        return result;
    }

    public String getNameOfGroup(int i) {
        return groups.get(i).getName();
    }

    public HashMap<Integer, String> getAllGroup() {
        HashMap<Integer, String> result = new HashMap<>();
        for (Integer i : groups.keySet()) {
            result.put(i, groups.get(i).getName());
        }
        return result;
    }

    public HashMap<Integer, String> getJoinedGroup(int student) {
        HashMap<Integer, String> result = new HashMap<>();
        for (Integer i : groups.keySet()) {
            if (groups.get(i).hasStudent(student)) {
                result.put(i, groups.get(i).getName());
            }
        }
        return result;
    }
    public void deleatGroup(int i){
        groups.remove(i);
    }
    public int[] getStudents(int id){
        return groups.get(id).getStudents();
    }
    public int getTeacher(int id){
        return groups.get(id).getTeacher();
    }
    public HashMap<Integer,LocalDateTime> getTests(int id){
        return groups.get(id).getTests();
    }
    public void addTest(int id, int t, LocalDateTime due){
        groups.get(id).addTest(t, due);
    }
    public boolean answerTest(int groupId,int test,String[] a, int studentId) {
        return groups.get(groupId).answerTest(test, a, studentId);
    }
}
