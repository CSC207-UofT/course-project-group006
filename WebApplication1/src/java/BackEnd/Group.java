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
        tests = new HashMap<>();
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

    public Group(int teacher, String name, int[] students, int id, HashMap<Test, List<Answer>> tests) {
        this.ID = id;
        this.teacher = teacher;
        this.name = name;
        this.students = students;
        this.tests = tests;
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
