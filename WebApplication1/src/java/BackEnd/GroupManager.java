package BackEnd;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupManager {
    //public static final String teacherData="../Teacher/";
    //public static final String studentData="../Teacher/";
    //public HashMap<String,Student> students;
    //public HashMap<String,Teacher> teachers;
    public HashMap<Integer, Group> groups;
    private ReadAll groupGate;

    public GroupManager(ReadAll readWriter) {
        this.groupGate = readWriter;
    }

    public GroupManager() {
        groups = new HashMap<>();
        creatGroup(2, "aaa");
        addStudentToGroup(3, 1);
    }

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
//
//    /**
//     * Remove a student from a certain group
//     *
//     * @param s     the student's ID
//     * @param group the group that the student needs to be removed from
//     * @return true if succeed or false otherwise
//     */
//    public boolean removeStudentFromGroup(int s, Integer group) {
//        return groups.get(group).removeStudent(s);
//
//    }


    public ArrayList<Integer> createdBy(int t) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Group g : groups.values()) {
            if (g.getTeacher() == (t)) {
                result.add(g.GetID());
            }
        }
        return result;
    }

    public String getNameOfGroup(int i) {
        return groups.get(i).getName();
    }

//
//    /**
//     * Get the group this student joined
//     *
//     * @param student the student ID
//     * @return the list of group this student joined
//     */
//    public HashMap<Integer, String> getJoinedGroup(int student) {
//        HashMap<Integer, String> result = new HashMap<>();
//        for (Integer i : groups.keySet()) {
//            if (groups.get(i).hasStudent(student)) {
//                result.put(i, groups.get(i).getName());
//            }
//        }
//        return result;
//    }

    public void deleatGroup(int i) {
        groups.remove(i);
    }

    public int[] getStudents(int id) {
        return groups.get(id).getStudents();
    }

    public int getTeacher(int id) {
        return groups.get(id).getTeacher();
    }

    public HashMap<Integer, LocalDateTime> getTests(int id) {
        return groups.get(id).getTests();
    }

    public void addTest(int id, int t, LocalDateTime due) {
        groups.get(id).addTest(t, due);
    }

    public boolean answerTest(int groupId, int test, String[] a, int studentId) {
        return groups.get(groupId).answerTest(test, a, studentId);
    }

    public HashMap<Integer, String[]> getSubmition(int groupId, int testId) {
        return groups.get(groupId).getTestResults(testId);
    }


    public boolean creatGroup(int t, String name) {
        Group g = new Group(t, name);
        groups.put(g.GetID(), g);
        return true;
    }

    /////////////////////////    /////////////////////////    /////////////////////////

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


    public HashMap<Integer, String> getAllGroup() {
        return groupGate.readAll();

    }


    public HashMap<Integer, String> getJoinedGroup(int student) {
        //TODO
        return null;
    }

    public boolean removeStudentFromGroup(int studentID, Integer group) {
        //TODO
        return false;
    }


    public boolean addStudentToGroup(int studentID, Integer groupID) {
        //TODO
        return false;
    }
}
