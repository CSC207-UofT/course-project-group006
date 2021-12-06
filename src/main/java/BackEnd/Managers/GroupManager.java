package BackEnd.Managers;

import BackEnd.Entities.Group;
import BackEnd.Interfaces.ReadAll;
import BackEnd.Interfaces.ReadNameID;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * The type Group manager.
 */
public class GroupManager {

    private final ReadAll groupGate;

    /**
     * Instantiates a new Group manager.
     *
     * @param readWriter the read writer
     */
    public GroupManager(ReadAll readWriter) {
        this.groupGate = readWriter;
    }



    /**
     * Create group int.
     *
     * @param teacherID   the teacher id
     * @param groupName   the group name
     * @param teacherGate the teacher gate
     * @return the int groupID
     */
    public int createGroup(int teacherID, String groupName, ReadNameID teacherGate) {
        Group g = new Group(teacherID, groupName);
        List<String> list = new ArrayList<>();
        list.add(g.getTeacher() + "");
        list.add(g.getName());
        List<String> result = groupGate.write(1, list);
        if (result != null && result.size() != 0) {
            int groupID = Integer.parseInt(result.get(0));
            UserManager userManager = new UserManager(teacherGate);
            userManager.addGroupToUser(teacherID, groupID, 600);
            return groupID;
        } else {
            return -1;
        }

    }

    /**
     * Delete group boolean.
     *
     * @param groupID     the group id
     * @param studentGate the student gate
     * @param teacherGate the teacher gate
     * @return success T, failed F
     */
    public boolean deleteGroup(int groupID, ReadNameID studentGate, ReadNameID teacherGate) {
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


    /**
     * Gets all group.
     *
     * @return the all group in the form of {id:name}
     */
    public HashMap<Integer, String> getAllGroup() {
        return groupGate.readAll();
    }

    /**
     * Gets name.
     *
     * @param groupID the group id
     * @return the name
     */
    public String getName(int groupID) {
        List<String> result = groupGate.readByID(222, 2, groupID);
        if (result != null) {
            return result.get(0);
        }
        return "FAILED";
    }

    /**
     * Gets teacher.
     *
     * @param groupID the group id
     * @return the teacher
     */
    public int getTeacher(int groupID) {
        List<String> result = groupGate.readByID(222, 3, groupID);
        if (result != null && result.size() != 0) {
            return Integer.parseInt(result.get(0));
        }
        return -1;
    }


    //    public HashMap<Integer, String> getJoinedGroup(int studentID, ReadNameID studentGate) {
//        HashMap<Integer, String> result = new HashMap<>();
//        UserManager studentManager = new UserManager(studentGate);
//        int[] IDList = studentManager.getGroupsFromUser(studentID, 500);
//        for (int id : IDList) {
//            String name = getName(id);
//            result.put(id, name);
//        }
//        return result;
//    }

    /**
     * Remove student from group boolean.
     *
     * @param studentID the student id
     * @param groupID   the group id
     * @return success T, failed F
     */
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


    /**
     * Get students int [ ].
     *
     * @param id the id
     * @return the int [ ]
     */
    public int[] getStudents(int id) {
        List<String> result = groupGate.readByID(222, 4, id);
        if (result.get(0).equals("")) {
            return new int[0];
        }
        String[] strings = result.get(0).split(",");
        int[] array = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            array[i] = Integer.parseInt(strings[i]);
        }
        return array;

    }


    /**
     * Add student to group boolean.
     *
     * @param studentID the student id
     * @param groupID   the group id
     * @return success T, failed F
     */
    public boolean addStudentToGroup(int studentID, Integer groupID) {
        int[] allStudents = getStudents(groupID);
        if (allStudents != null && allStudents.length != 0) {
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

    /**
     * Post announcement boolean.
     *
     * @param groupID      the group id
     * @param announcement the announcement
     * @return success T, failed F
     */
    public boolean postAnnouncement(int groupID, String announcement) {
        List<String> info = new ArrayList<>();
        info.add(groupID + "");
        info.add(announcement);
        return !groupGate.write(5, info).get(0).equals("FAILED");
    }

    private Group readGroup(int id) {
        //return new Group(1, "placeholder", new int[]{2,3}, 4, new HashMap<Integer, HashMap<Integer, String[]>>(), new ArrayList<>(), new ArrayList<>());
        List<String> info = groupGate.readRow(id);
        if (info.get(0).equals("" + id)) {
            String groupName = info.get(1);
            String creater = info.get(2);
            String[] students = info.get(3).split(",");
            String[] posts = info.get(4).split(",");
            String[] tests = info.get(5).split(",");
            List<Integer> tIds = new ArrayList<>();
            for (String test : tests) {
                try {
                    tIds.add(Integer.parseInt(test));
                } catch (NumberFormatException e) {
                    //do nothing
                }
            }
            int[] sIds = new int[Group.MAXNUMBER];
            int j = 0;
            for (String student : students) {
                try {
                    sIds[j] = Integer.parseInt(student);
                    j++;
                } catch (NumberFormatException e) {
                    //do nothing
                }
            }
            return new Group(Integer.parseInt(creater), groupName, sIds, tIds, posts);
        }
        return null;
    }

    public String getNameById(int id) {
        return Objects.requireNonNull(this.readGroup(id)).getName();
    }

    public HashMap<Integer, LocalDateTime> getTests(int id) {
        return Objects.requireNonNull(readGroup(id)).getTests();
    }

    public boolean addTest(int id, int testId, java.time.LocalDateTime due) {
        return Objects.requireNonNull(readGroup(id)).addTest(testId, due);
    }

    public List<Integer> createdBy() {
        return new ArrayList<>();
    }

    public void answerTest(int groupId, int testId, String[] answer, int studentId) {
        Objects.requireNonNull(readGroup(groupId)).answerTest(testId, answer, studentId);
    }

    public HashMap<Integer, String[]> getSubmition(int groupId, int testId) {
        return Objects.requireNonNull(readGroup(groupId)).getTestResults(testId);
    }

    public List<String> posts(int id) {
        return Objects.requireNonNull(readGroup(id)).getAnnouncement();
    }
}
