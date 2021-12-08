package BackEnd.Entities;



import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Collection;


public class Group {

    private int[] students;
    private int teacher;
    private String name;
    private int ID;
    public static final int MAXNUMBER = 30;
    private HashMap<Integer, HashMap<Integer, SubmationData[]>> testsResult;
    private HashMap<Integer, HashMap<Integer, Integer>> testMarks;
    private HashMap<Integer, LocalDateTime> duedates;
    private String announcement;
    private List<Integer> testIDs;

    /**
     * Constructor of Group class
     * @param teacher the teacher ID
     * @param name the name
     * @param students the students contained
     * @param id the ID
     * @param tests the tests in group
     * @param announcements the announcement in group
     * @param testIDs the IDs of tests
     */
    public Group(int teacher, String name, int[] students, int id, HashMap<Integer, HashMap<Integer, String[]>> tests, String announcements, List<Integer> testIDs) {
        this.ID = id;
        this.teacher = teacher;
        this.name = name;
        this.students = students;
        this.testsResult = new HashMap<>();
        for (int i:tests.keySet()){
            HashMap<Integer,SubmationData[]> temp = new HashMap<>();
            for (int j:tests.get(i).keySet()){
                temp.put(j,from(tests.get(i).get(j)));
            }
            this.testsResult.put(i,temp);
        }
        this.announcement = announcements;
        this.testIDs = testIDs;
    }
    /**
     * Constructor of Group class
     * @param teacher the teacher ID
     * @param name the name
     * @param students the students contained
     * @param id the ID
     * @param announcements the announcement in group
     * @param duedates the due dates of tests
     */
    public Group(int teacher, String name, int[] students, int id,String announcements, String testResult,String duedates) {
        this.ID = id;
        this.teacher = teacher;
        this.name = name;
        this.students = students;
        this.announcement = announcements;
        this.testsResult=deformat(testResult);
        //System.out.println(testResult);
        this.duedates=deformatDueDate(duedates);
        //System.out.println(duedates);
        this.testIDs= new ArrayList<>();
        testIDs.addAll(this.duedates.keySet());
        //System.out.println(testIDs);
    }
    /**
     * Constructor of Group class
     * @param groupID the ID of the group
     */
    public Group(int groupID) {
        this.ID = groupID;
    }
    /**
     * Constructor of Group class
     * @param teacher the teacher ID
     * @param name the name
     * @param students the students contained
     * @param posts the posts in group
     * @param tests the tests in group
     */
    public Group(int teacher,String name, int[] students, List<Integer> tests, String posts){
        this.teacher=teacher;
        this.name=name;
        this.students=students;
        this.testIDs=tests;
        duedates=new HashMap<>();
        for (int t:tests) {
            duedates.put(t,LocalDateTime.now());
        }
        this.announcement=posts;
    }

    /**
     * Setter method for teacher
     * @param teacher the teacher
     */
    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }

    /**
     * Setter method for name
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for ID
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Setter method for ID
     * @param ID the ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Getter method for test results
     * @return the test result hashmap
     */
    public HashMap<Integer, HashMap<Integer, SubmationData[]>> getTestsResult() {
        return testsResult;
    }

    /**
     * Setter method for test results
     * @param testsResult the test results
     */
    public void setTestsResult(HashMap<Integer, HashMap<Integer, SubmationData[]>> testsResult) {
        this.testsResult = testsResult;
    }

    /**
     * Calculate grade
     * @param test the test
     * @param student the student
     * @param mark the mark gotten
     * @param comment the comment given
     */
    public void grade(int test, int student, int[] mark, String[] comment){
        System.out.println(Arrays.toString(mark));
        System.out.println(Arrays.toString(comment));
        SubmationData[] s=testsResult.get(test).get(student);
        for (int i=0;i<s.length;i++){
            int m = -1;
            if (i < mark.length) {
                m = mark[i];
            }
            String c = "";
            if (i < comment.length && comment[i] != null) {
                c = comment[i];
            }
            s[i].gread(m, c);
        }
    }

    /**
     * Get due dates
     * @return the due dates of test
     */
    public HashMap<Integer, LocalDateTime> getDuedates() {
        return duedates;
    }

    /**
     * Set due dates
     * @param duedates the due dates of tests
     */
    public void setDuedates(HashMap<Integer, LocalDateTime> duedates) {
        this.duedates = duedates;
    }

    /**
     * Getter method for announcement
     * @return the announcement in group
     */
    public String getAnnouncement() {
        if(announcement!=null){
        return announcement;}
        return "";
    }

    /**
     * Getter method for test ID
     * @return the list of test ID
     */
    public List<Integer> getTestIDs() {
        return testIDs;
    }
    /**
     * Getter method for ID
     * @return the int ID
     */
    public int GetID() {
        return this.ID;
    }
    /**
     * Getter method for name
     * @return the String name
     */
    public String getName() {
        return this.name;
    }
    /**
     * Getter method for teacher
     * @return the int teacher
     */
    public int getTeacher() {
        return this.teacher;
    }
    /**
     * Getter method for test
     * @return the test hashmap
     */
    public HashMap<Integer, LocalDateTime> getTests() {
        return duedates;
    }

    /**
     * Constructor for Group
     * @param teacher the teacher of the group
     * @param name the name
     */
    public Group(int teacher, String name) {
        this.teacher = teacher;
        this.name = name;
        this.students = new int[Group.MAXNUMBER];
        this.testsResult = new HashMap<>();
        this.duedates = new HashMap<>();
        this.announcement ="";
        this.testIDs =new ArrayList<>() ;
    }

    /**
     * Whether add students
     * @param student the student being added
     * @return the boolean deciding if adding student successfully
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
     * Whether add tests
     * @param t the test
     * @param duedate the due date
     * @return the boolean deciding if adding test successfully
     */
    public boolean addTest(int t, LocalDateTime duedate) {
        //if (testsResult.containsKey(t)) {
        //  return false;
        //}
        this.testIDs.add(t);
        testsResult.put(t, new HashMap<Integer, SubmationData[]>());
        this.duedates.put(t, duedate);
        return true;
    }
    /**
     * Whether remove tests
     * @param t the test
     * @return the boolean deciding if removing test successfully
     */
    public boolean removeTest(int t){
        boolean result = this.testIDs.remove((Object) t);
        if((this.testsResult.remove(t)==null)){
            result = true;
        }
        if(!(this.duedates.remove(t)==null)){
            result = true;
        }
        return result;
    }
    /**
     * Whether answer tests
     * @param a the String from test
     * @param test the test
     * @param studentId the student ID
     * @return the boolean deciding if answering test successfully
     */
    public boolean answerTest(int test, String[] a, int studentId) {
        if (testsResult.containsKey(test)) {
            HashMap<Integer, SubmationData[]> h = testsResult.get(test);
            System.out.println(h);
            System.out.println(Arrays.toString(a));
            System.out.println(from(a));
            h.put(studentId, from(a));
            return true;
        }
        return false;
    }

    /**
     * Get test results
     * @param testId the ID of the test
     * @return the result gotten
     */
    public HashMap<Integer, String[]> getTestResults(int testId) {
        HashMap<Integer, String[]> result = new HashMap<Integer, String[]>();
        if (this.testsResult.containsKey(testId)) {
            for (int i : testsResult.get(testId).keySet()) {
                SubmationData[] answer = testsResult.get(testId).get(i);
                String[] ans = new String[answer.length];
                for (int j = 0; j < answer.length; j++) {
                    ans[j] = answer[j].answer;
                }
                result.put(i, ans);
            }
        }
        return result;
    }
    /**
     * Whether have students
     * @param student the student
     * @return the boolean deciding if there are students in the group
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
     * Whether remove students
     * @param student the student
     * @return the boolean deciding if removing student successfully
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
     * The toString method
     * @return the String with ID name teacher student form
     */
    public String toString() {
        return this.ID + this.name + this.teacher + this.students[0];
    }

    /**
     * Get students
     * @return the integer list of students
     */
    public int[] getStudents() {
        int[] result = new int[students.length];
        for (int i = 0; i < students.length; i++) {
            result[i] = students[i];
        }
        return result;
    }

    /**
     * Setter method for students
     * @param students the student
     */
    public void setStudents(int[] students) {
        this.students = students;
    }

    /**
     * Test answers
     * @return the String test answers
     */
    public String testAnswers(){
        return format(testsResult);
    }

    /**
     * Set rest results
     * @param input the test result inputted
     */
    private void setTestResult(String input){
        testsResult=deformat(input);
    }

    /**
     * Set due dates
     * @return the String due date
     */
    public String dueDatesString(){
        return formatDueDate(duedates);
    }

    /**
     * Method to create a Test ID
     * @return Test ID
     */
    public String testIds(){
        StringBuilder result = new StringBuilder();
        for (int i:duedates.keySet()){
            result.append(",").append(i);
        }
        return result.toString();
    }

    /**
     * Method to format
     * @param testsResult the test result
     * @return the specific designed format representing info
     */
    private String format(HashMap<Integer, HashMap<Integer, SubmationData[]>> testsResult){
        StringBuilder result = new StringBuilder();
        for (Integer i:testsResult.keySet()) {
            result.append(i).append("@");
            HashMap<Integer,SubmationData[]> sans = testsResult.get(i);
            for (Integer j: sans.keySet()) {
                result.append(j).append("|");
                for (SubmationData s:sans.get(j)) {
                    result.append(s).append("#");
                }
                result.append("$");
            }
            result.append("ö");
        }
        return result.toString();
    }

    /**
     * Method to deformat
     * @param input the formatted input
     * @return the deformatted output from the formatted input
     */
    private HashMap<Integer, HashMap<Integer, SubmationData[]>>deformat(String input){
        input=input.replaceAll(" ","");
        //System.out.println(input);
        HashMap<Integer, HashMap<Integer, SubmationData[]>> result = new HashMap<>();
        String[] outer = input.split("ö");
        for(String inf:outer){
            String[] s = inf.split("@");
            try {
                int testId = Integer.parseInt(s[0]);
                System.out.println(testId+":");
                HashMap<Integer, SubmationData[]> studentAnswers = new HashMap<>();
                result.put(testId,studentAnswers);
                if(s.length>1) {
                    String[] inner = s[1].split("\\$");
                    for (String inf2 : inner) {
                        String[] s2 = inf2.split("\\|");
                        try {
                            System.out.println(s2[0] + ":" + Arrays.toString(s2[1].split("#")));
                            int studentId = Integer.parseInt(s2[0]);
                            String[] s3 = s2[1].split("#");
                            SubmationData[] s4=new SubmationData[s3.length];
                            for (int k=0;k<s3.length;k++){
                                s4[k]=new SubmationData(s3[k]);
                                System.out.println(s4[k]);
                            }
                            studentAnswers.put(studentId, s4);

                        } catch (NumberFormatException e) {
                            //
                        }
                    }
                }
            }catch (NumberFormatException e){
                //
            }
        }
        System.out.println("result:"+result);
        return result;
    }

    /**
     * Format due dates
     * @param duedates the due dates
     * @return the String formatted due dates
     */
    public static String formatDueDate(HashMap<Integer,LocalDateTime> duedates){
        String result = "";
        for(Integer i:duedates.keySet()){
            result+=i+"@"+duedates.get(i).toString().replace("T"," ").split("\\.")[0]+"|";
        }
        return result;
    }

    /**
     * Deformat due dates
     * @param input the formatted due dates
     * @return the deformatted output from the formatted input
     */
    public static HashMap<Integer,LocalDateTime> deformatDueDate(String input){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        HashMap<Integer,LocalDateTime> result = new HashMap<>();
        String[] info = input.split("\\|");
        for(String s: info){
            try {
                String[] info2 = s.split("@");
                result.put(Integer.parseInt(info2[0]),LocalDateTime.parse(info2[1], formatter));
            }catch (NumberFormatException e){
                //
            }
        }
        return result;
    }

    /**
     * The from of submission data
     * @param input the input info
     * @return the list of info
     */
    public SubmationData[] from(String[] input){
        //System.out.println(Arrays.toString(input));
        SubmationData[] result=new SubmationData[input.length];
        for (int k=0;k<input.length;k++){
            result[k]=new SubmationData(input[k],-1,"");
        }
        return result;
    }

    /**
     * Get grades
     * @param testId the test ID
     * @param strudentId the student ID
     * @return the integer list of grades
     */
    public int[] getGrades(int testId, int strudentId){
        SubmationData[] submation = testsResult.get(testId).get(strudentId);
        boolean hasMark=false;
        if(submation!=null){
            int[] result = new int[submation.length];
            for(int i=0;i<submation.length;i++){
                result[i]=submation[i].getMark();
                if(result[i]!=-1){
                    hasMark=true;
                }
            }
            if(hasMark) {
                return result;
            }else {
                return null;
            }
        }
        return null;
    }

    /**
     * Get comment
     * @param testId the test ID
     * @param strudentId the student ID
     * @return the String list of comments
     */
    public String[] getComment(int testId, int strudentId){
        SubmationData[] submation = testsResult.get(testId).get(strudentId);
        if(submation!=null){
            String[] result = new String[submation.length];
            for(int i=0;i<submation.length;i++){
                result[i]=submation[i].getComment();

            }
            return result;
        }
        return new String[0];
    }
    public static void main(String[] args) {
        System.out.println(new SubmationData("aaa%&-1%&%"));

    }

    /**
     * Submission data class
     */
    private static class SubmationData{
        private String answer;
        private int mark=-1;
        private String comment="";

        /**
         * Constructor of SubmationData class
         * @param answer the answer
         * @param mark the mark
         * @param comment the comment
         */
        public SubmationData(String answer, int mark,String comment){
            this.answer=answer;
            this.mark=mark;
            this.comment=comment;
        }

        /**
         * Grade gotten
         * @param mark the mark
         * @param comment the comment
         */
        public void gread(int mark,String comment){
            this.comment=comment;
            this.mark=mark;
        }

        /**
         * Getter method for answer
         * @return the String answer
         */
        public String getAnswer(){
            return this.answer;
        }

        /**
         * Getter method for comment
         * @return the String comment
         */
        public String getComment(){
            return this.comment;
        }

        /**
         * Getter method for mark
         * @return the int mark
         */
        public int getMark(){
            return this.mark;
        }

        /**
         * Setter method for answer
         * @param answer the answer
         */
        public void setAnswer(String answer){
            this.answer=answer;
        }

        /**
         * The toString method
         * @return the String in answer mark comment form
         */
        @Override
        public String toString(){
            return this.answer+"%&"+this.mark+"%&"+this.comment+"%";
        }

        /**
         * Submission data
         * @param input the input
         */
        public SubmationData(String input){
            String[] s = input.split("&");
            answer=s[0].replace("%","");
            mark=Integer.parseInt(s[1].replace("%",""));
            comment=s[2].replace("%","");
        }
    }
}
