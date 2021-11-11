package BackEnd;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Group {
    private int[] students;
    private int teacher;
    private String name;
    private int ID;
    public static final int MAXNUMBER = 30;
    public HashMap<Test, List<Answer>> tests;

    public Group(int teacher, String name) {
        this.teacher = teacher;
        this.name = name;
        this.students = new int[Group.MAXNUMBER];
        this.ID = IDcreater.creat();
        tests = new HashMap<>();
    }

    public Group(int teacher, String name, int[] students, int id, HashMap<Test, List<Answer>> tests) {
        this.ID = id;
        this.teacher = teacher;
        this.name = name;
        this.students = students;
        this.tests = tests;
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

    public boolean addTest(Test t) {
        if (tests.containsKey(t)) {
            return false;
        }
        tests.put(t, new ArrayList<Answer>());
        return true;
    }

    public boolean answerTest(Answer a) {
        if (tests.containsKey(a.getAbout())) {
            tests.get(a.getAbout()).remove(a);
            tests.get(a.getAbout()).add(a);
            return true;
        }
        return false;
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
