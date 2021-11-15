package BackEnd1;

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

    /**
     * Construct a UserGroup Manager
     */

    public UserGroupManager() {
        groups = new HashMap<>();
        creatGroup(2,"aaa");
        addStudentToGroup(3,1); 
    }

    /**
     * construct a UserGroup Manager giving a certain group
     * @param groups the group wants to manager
     */

    public UserGroupManager(HashMap<Integer, Group> groups) {
        this.groups = groups; 
    }

    /**
     * Add student to a certain group
     * @param s the Student's ID
     * @param group the group want to add student to
     * @return true if add succeed or false otherwise
     */

    public boolean addStudentToGroup(int s, Integer group) {
        return groups.get(group).addStudent(s);
    }

    /**
     * Remove a student from a certain group
     * @param s the student's ID
     * @param group the group that the student needs to be removed from
     * @return true if succeed or false otherwise
     */
    public boolean removeStudentFromGroup(int s, Integer group) {
        return groups.get(group).removeStudent(s);

    }

    /**
     * Create a certain group giving teacher's ID and the group name
     * @param t teacher's ID
     * @param name the group name
     */
    public boolean creatGroup(int t, String name) {
        Group g = new Group(t, name);
        groups.put(g.GetID(), g);
        return true;
    }

    /**
     * Get a list of group created by certain teacher
     * @param t the teacher's ID
     * @return the list of group ID this teacher created
     */
    public ArrayList<Integer> createdBy(int t) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Group g : groups.values()) {
            if (g.getTeacher()==(t)) {
                result.add(g.GetID());
            }
        }
        return result;
    }

    /**
     * Get the name of a group
     * @param i the group ID
     * @return the name of the group
     */
    public String getNameOfGroup(int i) {
        return groups.get(i).getName();
    }

    /**
     *
     * @return all the group in a form of ID and name
     */
    public HashMap<Integer, String> getAllGroup() {
        HashMap<Integer, String> result = new HashMap<>();
        for (Integer i : groups.keySet()) {
            result.put(i, groups.get(i).getName());
        }
        return result;
    }

    /**
     * Get the group this student joined
     * @param student the student ID
     * @return the list of group this student joined
     */
    public HashMap<Integer, String> getJoinedGroup(int student) {
        HashMap<Integer, String> result = new HashMap<>();
        for (Integer i : groups.keySet()) {
            if (groups.get(i).hasStudent(student)) {
                result.put(i, groups.get(i).getName());
            }
        }
        return result;
    }

    /**
     * Delete a group
     * @param i the group ID
     */
    public void deleatGroup(int i){
        groups.remove(i);
    }

    /**
     * Get the students in one group
     * @param id the group ID
     * @return the list of student in this group
     */
    public int[] getStudents(int id){
        return groups.get(id).getStudents();
    }

    /**
     * Get the teacher in this group
     * @param id teacher ID
     * @return the teacher in this group
     */
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
    public HashMap<Integer, String[]> getSubmition(int groupId, int testId){
        return groups.get(groupId).getTestResults(testId);
    }
}
