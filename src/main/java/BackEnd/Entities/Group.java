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

    /**
     * Setter method of teacher
     * @param teacher Teacher in group
     */
    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }

    /**
     * Setter method of test name
     * @param name Test name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method of ID
     * @return ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Setter method of ID
     * @param ID Student ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Getter method of Test results
     * @return Hashmap of Test results
     */
    public HashMap<Integer, HashMap<Integer, String[]>> getTestsResult() {
        return testsResult;
    }

    /**
     * Setter method of Test results
     * @param testsResult Test results
     */
    public void setTestsResult(HashMap<Integer, HashMap<Integer, String[]>> testsResult) {
        this.testsResult = testsResult;
    }

    /**
     * Getter method of Due dates
     * @return Due dates
     */
    public HashMap<Integer, LocalDateTime> getDuedates() {
        return duedates;
    }

    /**
     * Setter method of Due dates
     * @param duedates Due dates
     */
    public void setDuedates(HashMap<Integer, LocalDateTime> duedates) {
        this.duedates = duedates;
    }

    /**
     * Getter method of Announcement
     * @return announcement
     */
    public List<String> getAnnouncement() {
        return announcement;
    }

    /**
     * Method to add Announcement
     * @param announcement The announcement
     */
    public void addAnnouncement(String announcement) {
        this.announcement.add(announcement);
    }

    /**
     * Getter method of Test IDs
     * @return Test ID
     */
    public List<Integer> getTestIDs() {
        return testIDs;
    }

    //public void setTestIDs(String testIDs) {
        //this.testIDs = testIDs;
    //}

    /**
     * Getter method of ID
     * @return Student ID
     */
    public int GetID() {
        return this.ID;
    }

    /**
     * Getter method of Name
     * @return Name of the test
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method of Teacher
     * @return Teacher ID
     */
    public int getTeacher() {
        return this.teacher;
    }

    /**
     * Getter method of Test
     * @return Due dates
     */
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
        this.testIDs = new ArrayList<>() ;
    }


    //public Group(){

    //}

    /**
     * Method to decide if add student
     * @param student Student ID
     * @return Decision of whether add student
     */
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

    /**
     * Method to decide if add test
     * @param t Test ID
     * @param duedate Due dates of the test
     * @return Decision of whether add test
     */
    public boolean addTest(int t, LocalDateTime duedate) {
        //if (testsResult.containsKey(t)) {
        //  return false;
        //}
        testsResult.put(t, new HashMap<Integer, String[]>());
        this.duedates.put(t, duedate);
        return true;
    }

    /**
     * Method to decide if user can answer test
     * @param test Test ID
     * @param a Answer
     * @param studentId Student ID
     * @return Decision of whether user can answer test
     */
    public boolean answerTest(int test, String[] a, int studentId) {
        if (testsResult.containsKey(test)) {
            HashMap<Integer, String[]> h = testsResult.get(test);
            h.put(studentId, a);
            return true;
        }
        return false;
    }

    /**
     * Getter method of Test results
     * @param testId Test ID
     * @return Result of the test
     */
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

    /**
     * Method to decide if a group has students
     * @param student Student ID
     * @return If there are students in the group
     */
    public boolean hasStudent(int student) {
        for (int s : students) {
            if (s == (student)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to decide if a group will remove students
     * @param student Student ID
     * @return If a group removes students
     */
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

    /**
     * ToString method of the class
     * @return String
     */
    public String toString() {
        return this.ID + this.name + this.teacher + this.students[0];
    }

    /**
     * Getter method of students id
     * @return students
     */
    public int[] getStudents() {
        int[] result = new int[students.length];
        for (int i = 0; i < students.length; i++) {
            result[i] = students[i];
        }
        return result;
    }

    /**
     *
     * @param students
     */
    public void setStudents(int[] students) {
        this.students = students;
    }
}
