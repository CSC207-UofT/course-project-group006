package BackEnd;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type Group manager.
 */
public class GroupManager {
    //public static final String teacherData="../Teacher/";
    //public static final String studentData="../Teacher/";
    //public HashMap<String,Student> students;
    //public HashMap<String,Teacher> teachers;
    public HashMap<Integer, Group> groups;
    private GeneralReadWriter groupGate;

    public GroupManager(GeneralReadWriter readWriter) {
        this.groupGate = readWriter;
    }

    /**
     * Construct a UserGroup Manager
     */
    public GroupManager() {
        groups = new HashMap<>();
        creatGroup(2, "aaa");
        addStudentToGroup(3, 1);
    }

    /**
     * construct a UserGroup Manager giving a certain group
     *
     * @param groups the group wants to manager
     */
    public GroupManager(HashMap<Integer, Group> groups) {
        this.groups = groups;
    }

//    /**
//     * Add student to a certain group
//     *
//     * @param s     the Student's ID
//     * @param group the group want to add student to
//     * @return true if add succeed or false otherwise
//     */
//    public boolean addStudentToGroup(int s, Integer group) {
//        return groups.get(group).addStudent(s);
//    }

    /**
     * Remove a student from a certain group
     *
     * @param s     the student's ID
     * @param group the group that the student needs to be removed from
     * @return true if succeed or false otherwise
     */
    public boolean removeStudentFromGroup(int s, Integer group) {
        return groups.get(group).removeStudent(s);

    }


    /**
     * Get a list of group created by certain teacher
     *
     * @param t the teacher's ID
     * @return the list of group ID this teacher created
     */
    public ArrayList<Integer> createdBy(int t) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Group g : groups.values()) {
            if (g.getTeacher() == (t)) {
                result.add(g.GetID());
            }
        }
        return result;
    }

    /**
     * Get the name of a group
     *
     * @param i the group ID
     * @return the name of the group
     */
    public String getNameOfGroup(int i) {
        return groups.get(i).getName();
    }

    /**
     * Gets all group.
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
     *
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
     *
     * @param i the group ID
     */
    public void deleatGroup(int i) {
        groups.remove(i);
    }

    /**
     * Get the students in one group
     *
     * @param id the group ID
     * @return the list of student in this group
     */
    public int[] getStudents(int id) {
        return groups.get(id).getStudents();
    }

    /**
     * Get the teacher in this group
     *
     * @param id teacher ID
     * @return the teacher in this group
     */
    public int getTeacher(int id) {
        return groups.get(id).getTeacher();
    }

    /**
     * Gets tests.
     *
     * @param id the id
     * @return the tests
     */
    public HashMap<Integer, LocalDateTime> getTests(int id) {
        return groups.get(id).getTests();
    }

    /**
     * Add test.
     *
     * @param id  the id
     * @param t   the t
     * @param due the due
     */
    public void addTest(int id, int t, LocalDateTime due) {
        groups.get(id).addTest(t, due);
    }

    /**
     * Answer test boolean.
     *
     * @param groupId   the group id
     * @param test      the test
     * @param a         the a
     * @param studentId the student id
     * @return the boolean
     */
    public boolean answerTest(int groupId, int test, String[] a, int studentId) {
        return groups.get(groupId).answerTest(test, a, studentId);
    }

    /**
     * Gets submition.
     *
     * @param groupId the group id
     * @param testId  the test id
     * @return the submition
     */
    public HashMap<Integer, String[]> getSubmition(int groupId, int testId) {
        return groups.get(groupId).getTestResults(testId);
    }


    /**
     * Create a certain group giving teacher's ID and the group name
     *
     * @param t    teacher's ID
     * @param name the group name
     * @return the boolean
     */
    public boolean creatGroup(int t, String name) {
        Group g = new Group(t, name);
        groups.put(g.GetID(), g);
        return true;
    }


    /**
     * Create a new group .
     *
     * @param teacherID the teacher id
     * @param groupName the group name
     * @return int groupID/-1
     */
    public int createGroup(int teacherID, String groupName) {
        //TODO: check duplicates
        Group g = new Group(teacherID, groupName);
        List<String> list = new ArrayList<>();
        list.add(g.getTeacher() + "");
        list.add(g.getName());
        List<String> result = groupGate.write(1, list);
        if (result != null) {
            return Integer.parseInt(result.get(0));
        }
        return -1;
    }

//    public boolean changeGroupName(int groupID, String groupName){
//        //TODO: check duplicates
//        Group g = new Group(groupID);
//        g.setName(groupName);
//        String newName = g.getName();
//
//
//
//    }


    public boolean addStudentToGroup(int studentID, Integer groupID) {


        return groups.get(groupID).addStudent(studentID);
    }
}
