package BackEnd1;


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
    

    /**
     * Construct a group giving its leading teacher and name
     * @param teacher the teacher who created this group
     * @param name the group name
     */

    public Group(int teacher, String name) {
        this.teacher = teacher;
        this.name = name;
        this.students = new int[Group.MAXNUMBER];
        this.ID = IDcreater.creat();
        testsResult = new HashMap<>();
        duedates=new HashMap<>();
    }


    /**
     * Create a group giving its leading teacher, name, students
     * group ID, and test this group has
     *
     * @param teacher the teacher who create this group
     * @param name the group name
     * @param students the list of student in this group
     * @param id the ID of the group
     * @param tests the list of test this group has
     */

    public Group(int teacher, String name, int[] students, int id, HashMap<Integer, HashMap<Integer,String[]>> tests) {
        this.ID = id;
        this.teacher = teacher;
        this.name = name;
        this.students = students;
        this.testsResult = tests;
    }
    //public Group(){

    //}

    /**
     * Add student to this group
     * @param student the student want to add
     * @return True if add successful or False otherwise
     */
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

    /**
     *
     * @return get the ID of the group
     */
    public int GetID() {
        return this.ID;
    }

    /**
     *
     * @return get the name of the group
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return get the leading teacher of the group
     */
    public int getTeacher() {
        return this.teacher;
    }


    /**
     * Add test to this group
     * @param t the test want to add
     * @return True if add success or False otherwise
     */
    
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
