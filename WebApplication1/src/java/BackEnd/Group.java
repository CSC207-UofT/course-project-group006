package BackEnd;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;

public class Group {
    private int[] students;
    private int teacher;
    private String name;
    private int ID;
    public static final int MAXNUMBER = 30;
    private HashMap<Integer, HashMap<Integer,String[]>> testsResult;
    private HashMap<Integer,LocalDateTime> duedates;
    

    public Group(int teacher, String name) {
        this.teacher = teacher;
        this.name = name;
        this.students = new int[Group.MAXNUMBER];
        this.ID = IDcreater.creat();
        testsResult = new HashMap<>();
        duedates=new HashMap<>();
    }

    public Group(int teacher, String name, int[] students, int id, HashMap<Integer, HashMap<Integer,String[]>> tests) {
        this.ID = id;
        this.teacher = teacher;
        this.name = name;
        this.students = students;
        this.testsResult = tests;
    }
    //public Group(){

    //}
    public boolean addStudent(int student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] ==0) {
                students[i] = student;
                //student.getJoinedGroup().add(name);
                return true;
            }
        }
        return false;
    }

    public int GetID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public int getTeacher() {
        return this.teacher;
    }

    public boolean addTest(int t, LocalDateTime duedate) {
        //if (testsResult.containsKey(t)) {
          //  return false;
        //}
        testsResult.put(t, new HashMap<Integer,String[]>());
        this.duedates.put(t, duedate);
        return true;
    }

    public boolean answerTest(int test,String[] a, int studentId) {
        if (testsResult.containsKey(test)) {
            HashMap<Integer,String[]> h = testsResult.get(test);
            h.put(studentId, a);
            return true;
        }
        return false;
    }
    public HashMap<Integer,LocalDateTime> getTests(){
        return duedates;
    }
    public HashMap<Integer,String[]> getTestResults(int testId){
        HashMap<Integer,String[]> result = new HashMap<Integer,String[]>();
        if(this.testsResult.containsKey(testId)){
            for(int i:testsResult.get(testId).keySet()){
                String[] answer = testsResult.get(testId).get(i);
                String[] ans = new String[answer.length];
                for(int j=0; j<answer.length;j++){
                    ans[j]=answer[j];
                }
                result.put(i, ans);
            }
        }
        return result;
    }
    public boolean hasStudent(int student) {
        for (int s : students) {
            if (s==(student)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeStudent(int student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i]==(student)) {
                students[i] = 0;
                //student.getJoinedGroup().add(name);
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return this.ID+this.name+this.teacher+this.students[0];
    }
    public int[] getStudents(){
        int[] result = new int[students.length];
        for(int i=0; i<students.length;i++){
            result[i]=students[i];
        }
        return result;
    }

}
