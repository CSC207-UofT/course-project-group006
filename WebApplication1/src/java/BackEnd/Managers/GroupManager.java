package BackEnd.Managers;


import BackEnd.Interfaces.GeneralReadWriter;
import BackEnd.Entities.Group;
import BackEnd.Interfaces.ReadAll;

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


    public GroupManager(HashMap<Integer, Group> groups) {
        this.groups = groups;
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


    /////////////////////////    /////////////////////////    /////////////////////////

    public int createGroup(int teacherID, String groupName, GeneralReadWriter teacherGate) {
        Group g = new Group(teacherID, groupName);
        List<String> list = new ArrayList<>();
        list.add(g.getTeacher() + "");
        list.add(g.getName());
        List<String> result = groupGate.write(1, list);
        if (result != null) {
            int groupID = Integer.parseInt(result.get(0));
            UserManager userManager = new UserManager(teacherGate);
            userManager.addGroupToUser(teacherID, groupID, 600);
            return groupID;
        } else {
            return -1;
        }

    }

    public boolean deleteGroup(int groupID, GeneralReadWriter studentGate, GeneralReadWriter teacherGate) {
        UserManager studentManager = new UserManager(studentGate);
        int[] students = getStudents(groupID);
        if (students != null) {
            for (int id : students) {
                if (!studentManager.removeGroupFromUser(id, groupID, 500)) {
                    return false;
                }
            }
        }
        int teacherID = getTeacher(groupID);
        UserManager teacherManager = new UserManager(teacherGate);
        if (!teacherManager.removeGroupFromUser(teacherID, groupID, 600)) {
            return false;
        }

        List<String> info = new ArrayList<>();
        info.add(groupID + "");
        List<String> stringList = groupGate.write(44, info);
        return !stringList.get(0).equals("FAILED");
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

    public String getName(int groupID) {
        List<String> result = groupGate.readByID(222, 2, groupID);
        if (result != null) {
            return result.get(0);
        }
        return "FAILED";
    }

    public int getTeacher(int groupID) {
        List<String> result = groupGate.readByID(222, 3, groupID);
        if (result != null) {
            return Integer.parseInt(result.get(0));
        }
        return -1;
    }


    public HashMap<Integer, String> getJoinedGroup(int studentID, GeneralReadWriter studentGate) {
        HashMap<Integer, String> result = new HashMap<>();
        UserManager studentManager = new UserManager(studentGate);
        int[] IDList = studentManager.getGroupsFromUser(studentID, 500);
        for (int id : IDList) {
            String name = getName(id);
            result.put(id, name);
        }
        return result;
    }

    public boolean removeStudentFromGroup(int studentID, Integer groupID) {
        List<String> result = groupGate.readByID(222, 4, groupID);
        if (result.get(0).equals("")) {
            return false;
        }
        String[] strings = result.get(0).split(",");
        int[] array = new int[strings.length];
        boolean inGroup = false;
        int index = 0;
        for (int i = 0; i < strings.length; i++) {
            array[i] = Integer.parseInt(strings[i]);
            if (array[i] == studentID) {
                inGroup = true;
                index = i;
            }
        }
        if (!inGroup) {
            return false;
        }

        StringBuilder newString = new StringBuilder();
        if (array.length != 1) {
            for (int i = 0; i < array.length; i++) {
                if (i != index) {
                    newString.append(",").append(array[i]);
                }
            }
        }
        List<String> info = new ArrayList<>();
        info.add(groupID + "");
        info.add(newString.toString());
        List<String> stringList = groupGate.write(22, info);
        return !stringList.get(0).equals("FAILED");
    }


    public int[] getStudents(int id) {
        List<String> result = groupGate.readByID(222, 4, id);
        if (result.get(0).equals("")) {
            return null;
        }
        String[] strings = result.get(0).split(",");
        int[] array = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            array[i] = Integer.parseInt(strings[i]);
        }
        return array;

    }


    public boolean addStudentToGroup(int studentID, Integer groupID) {
        int[] allStudents = getStudents(groupID);
        if (allStudents != null) {
            for (int id : allStudents) {
                if (id == studentID) {
                    return false;
                }
            }
        }
        List<String> list = new ArrayList<>();
        list.add(groupID + "");
        list.add(studentID + "");
        List<String> result = groupGate.write(4, list);
        return result != null;
    }

    public boolean postAnnouncement(int groupID, String announcement) {
        List<String> info = new ArrayList<>();
        info.add(groupID + "");
        info.add(announcement);
        return !groupGate.write(5, info).get(0).equals("FAILED");
    }
}
