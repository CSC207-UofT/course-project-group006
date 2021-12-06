package BackEnd.Entities;



import java.util.ArrayList;
import java.util.Arrays;
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
    public Group(int teacher,String name, int[] students, List<Integer> tests, String[] posts){
        this.teacher=teacher;
        this.name=name;
        this.students=students;
        this.testIDs=tests;
        duedates=new HashMap<>();
        for (int t:tests) {
            duedates.put(t,LocalDateTime.now());
        }
        this.announcement=List.of(posts);
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
        if(announcement!=null){
        return announcement;}
        return new ArrayList<String>();
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
    private String testAnswers(){
        return format(testsResult);
    }
    private void setTestResult(String input){
        testsResult=deformat(input);
    }
    private static String format(HashMap<Integer, HashMap<Integer, String[]>> testsResult){
        String result = "";
        for (Integer i:testsResult.keySet()) {
            result+=i+"@";
            HashMap<Integer,String[]> sans = testsResult.get(i);
            for (Integer j: sans.keySet()) {
                result+=j+"|";
                for (String s:sans.get(j)) {
                    result+=s+"#";
                }
                result+="$";
            }
            result+="ö";
        }
        return result;
    }
    private static HashMap<Integer, HashMap<Integer, String[]>>deformat(String input){
        HashMap<Integer, HashMap<Integer, String[]>> result = new HashMap<>();
        String[] outer = input.split("ö");
        for(String inf:outer){
            String[] s = inf.split("@");
            try {
                int testId = Integer.parseInt(s[0]);
                System.out.println(testId+":");
                String[] inner = s[1].split("\\$");
                HashMap<Integer, String[]> studentAnswers = new HashMap<>();
                for (String inf2 : inner) {
                    String[] s2 = inf2.split("\\|");
                    try {
                        System.out.println(s2[0]+":"+ Arrays.toString(s2[1].split("#")));
                        int studentId = Integer.parseInt(s2[0]);
                        studentAnswers.put(studentId, s2[1].split("#"));

                    } catch (NumberFormatException e) {
                        //
                    }
                }
                result.put(testId,studentAnswers);
            }catch (NumberFormatException e){
                //
            }
        }
        return result;
    }
    public static void main(String[] args) {
        HashMap<Integer, HashMap<Integer, String[]>> testsResult=new HashMap<>();
        String[] a = new String[]{"a","b","c","d"};
        String[] b = new String[]{"aa","bb","cc","dd"};
        String[] c = new String[]{"aaa","bbb","ccc","ddd"};
        String[] d = new String[]{"aaaa","bbbb","cccc","dddd"};
        HashMap<Integer, String[]> e = new HashMap<>();
        e.put(1,a);
        e.put(2,b);
        HashMap<Integer,String[]> f = new HashMap<>();
        f.put(1,c);
        f.put(2,d);
        testsResult.put(3,e);
        testsResult.put(4,f);
        String s = format(testsResult);
        System.out.println(s);
        System.out.println(deformat(s));
    }
}
