package BackEnd.Entities;



import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.List;

public class Group {

    private int[] students;
    private int teacher;
    private String name;
    private int ID;
    public static final int MAXNUMBER = 30;
    private HashMap<Integer, HashMap<Integer, String[]>> testsResult;
    private HashMap<Integer, LocalDateTime> duedates;
    private List<String> announcement;
    private List<Integer> testIDs;

    public Group(int teacher, String name, int[] students, int id, HashMap<Integer, HashMap<Integer, String[]>> tests, List<String> announcements, List<Integer> testIDs) {
        this.ID = id;
        this.teacher = teacher;
        this.name = name;
        this.students = students;
        this.testsResult = tests;
        this.announcement = announcements;
        this.testIDs = testIDs;
    }

    public Group(int groupID) {
        this.ID = groupID;
    }

    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public HashMap<Integer, HashMap<Integer, String[]>> getTestsResult() {
        return testsResult;
    }

    public void setTestsResult(HashMap<Integer, HashMap<Integer, String[]>> testsResult) {
        this.testsResult = testsResult;
    }

    public HashMap<Integer, LocalDateTime> getDuedates() {
        return duedates;
    }

    public void setDuedates(HashMap<Integer, LocalDateTime> duedates) {
        this.duedates = duedates;
    }

    public List<String> getAnnouncement() {
        return announcement;
    }

    public void addAnnouncement(String announcement) {
        this.announcement.add(announcement);
    }

    public List<Integer> getTestIDs() {
        return testIDs;
    }

    //public void setTestIDs(String testIDs) {
        //this.testIDs = testIDs;
    //}

    public int GetID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public int getTeacher() {
        return this.teacher;
    }

    public HashMap<Integer, LocalDateTime> getTests() {
        return duedates;
    }


    public Group(int teacher, String name) {
        this.teacher = teacher;
        this.name = name;
        this.students = new int[Group.MAXNUMBER];
        this.testsResult = new HashMap<>();
        this.duedates = new HashMap<>();
        this.announcement = new ArrayList<String>(){
        };
        this.testIDs =new ArrayList<>() ;
    }


    //public Group(){

    //}

    public boolean addStudent(int student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == 0) {
                students[i] = student;
                //student.getJoinedGroup().add(name);
                return true;
            }
        }
        return false;
    }


    public boolean addTest(int t, LocalDateTime duedate) {
        //if (testsResult.containsKey(t)) {
        //  return false;
        //}
        testsResult.put(t, new HashMap<Integer, String[]>());
        this.duedates.put(t, duedate);
        return true;
    }

    public boolean answerTest(int test, String[] a, int studentId) {
        if (testsResult.containsKey(test)) {
            HashMap<Integer, String[]> h = testsResult.get(test);
            h.put(studentId, a);
            return true;
        }
        return false;
    }


    public HashMap<Integer, String[]> getTestResults(int testId) {
        HashMap<Integer, String[]> result = new HashMap<Integer, String[]>();
        if (this.testsResult.containsKey(testId)) {
            for (int i : testsResult.get(testId).keySet()) {
                String[] answer = testsResult.get(testId).get(i);
                String[] ans = new String[answer.length];
                for (int j = 0; j < answer.length; j++) {
                    ans[j] = answer[j];
                }
                result.put(i, ans);
            }
        }
        return result;
    }

    public boolean hasStudent(int student) {
        for (int s : students) {
            if (s == (student)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeStudent(int student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == (student)) {
                students[i] = 0;
                //student.getJoinedGroup().add(name);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return this.ID + this.name + this.teacher + this.students[0];
    }

    public int[] getStudents() {
        int[] result = new int[students.length];
        for (int i = 0; i < students.length; i++) {
            result[i] = students[i];
        }
        return result;
    }

    public void setStudents(int[] students) {
        this.students = students;
    }
}
