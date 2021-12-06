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
    private List<String> announcement;
    private List<Integer> testIDs;

    public Group(int teacher, String name, int[] students, int id, HashMap<Integer, HashMap<Integer, String[]>> tests, List<String> announcements, List<Integer> testIDs) {
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
    public Group(int teacher, String name, int[] students, int id,List<String> announcements, String testResult,String duedates) {
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

    public HashMap<Integer, HashMap<Integer, SubmationData[]>> getTestsResult() {
        return testsResult;
    }

    public void setTestsResult(HashMap<Integer, HashMap<Integer, SubmationData[]>> testsResult) {
        this.testsResult = testsResult;
    }
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
        this.testIDs.add(t);
        testsResult.put(t, new HashMap<Integer, SubmationData[]>());
        this.duedates.put(t, duedate);
        return true;
    }
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
    public String testAnswers(){
        return format(testsResult);
    }
    private void setTestResult(String input){
        testsResult=deformat(input);
    }
    public String dueDatesString(){
        return formatDueDate(duedates);
    }
    public String testIds(){
        StringBuilder result = new StringBuilder();
        for (int i:duedates.keySet()){
            result.append(",").append(i);
        }
        return result.toString();
    }
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
    private HashMap<Integer, HashMap<Integer, SubmationData[]>>deformat(String input){
        input=input.replaceAll(" ","");
        //System.out.println(input);
        HashMap<Integer, HashMap<Integer, SubmationData[]>> result = new HashMap<>();
        String[] outer = input.split("ö");
        for(String inf:outer){
            String[] s = inf.split("@");
            try {
                int testId = Integer.parseInt(s[0]);
                //System.out.println(testId+":");
                HashMap<Integer, SubmationData[]> studentAnswers = new HashMap<>();
                result.put(testId,studentAnswers);
                if(s.length>1) {
                    String[] inner = s[1].split("\\$");
                    for (String inf2 : inner) {
                        String[] s2 = inf2.split("\\|");
                        try {
                            //System.out.println(s2[0] + ":" + Arrays.toString(s2[1].split("#")));
                            int studentId = Integer.parseInt(s2[0]);
                            studentAnswers.put(studentId, from(s2[1].split("#")));

                        } catch (NumberFormatException e) {
                            //
                        }
                    }
                }
            }catch (NumberFormatException e){
                //
            }
        }
        return result;
    }
    public static String formatDueDate(HashMap<Integer,LocalDateTime> duedates){
        String result = "";
        for(Integer i:duedates.keySet()){
            result+=i+"@"+duedates.get(i).toString().replace("T"," ").split("\\.")[0]+"|";
        }
        return result;
    }
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
    public SubmationData[] from(String[] input){
        System.out.println(Arrays.toString(input));
        SubmationData[] result=new SubmationData[input.length];
        for (int k=0;k<input.length;k++){
            result[k]=new SubmationData(input[k],-1,"");
        }
        return result;
    }
    public static void main(String[] args) {
//        HashMap<Integer, HashMap<Integer, String[]>> testsResult=new HashMap<>();
//        String[] a = new String[]{"a","b","c","d"};
//        String[] b = new String[]{"aa","bb","cc","dd"};
//        String[] c = new String[]{"aaa","bbb","ccc","ddd"};
//        String[] d = new String[]{"aaaa","bbbb","cccc","dddd"};
//        HashMap<Integer, String[]> e = new HashMap<>();
//        e.put(1,a);
//        e.put(2,b);
//        HashMap<Integer,String[]> f = new HashMap<>();
//        f.put(1,c);
//        f.put(2,d);
//        testsResult.put(3,e);
//        testsResult.put(4,f);
//        String s = format(testsResult);
//        System.out.println(s);
//        System.out.println(deformat(s));
//        String s = LocalDateTime.now().toString().replace("T"," ").split("\\.")[0];
//
//        System.out.println(s);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime dateTime = LocalDateTime.parse(s, formatter);
//        HashMap<Integer,LocalDateTime> test = new HashMap<>();
//        test.put(1,LocalDateTime.now());
//        test.put(2,LocalDateTime.now());
//        test.put(3,LocalDateTime.now());
//        test.put(4,LocalDateTime.now());
//        String s = formatDueDate(test);
//        System.out.println(s);
//        System.out.println(deformatDueDate(s));
        SubmationData s =new SubmationData("aaaaaaaa",-1,"");
        s.gread(1,"");
        SubmationData s2 = new SubmationData("a",-1,"");
        SubmationData[] s1 = new SubmationData[]{s,s2};
        HashMap<Integer,SubmationData[]> a = new HashMap<>();
        a.put(1,s1);

            System.out.println(s);

    }
    private static class SubmationData{
        private String answer;
        private int mark=-1;
        private String comment="";
        public SubmationData(String answer, int mark,String comment){
            this.answer=answer;
            this.mark=mark;
            this.comment=comment;
        }
        public void gread(int mark,String comment){
            this.comment=comment;
            this.mark=mark;
        }
        public String getAnswer(){
            return this.answer;
        }
        public String getComment(){
            return this.comment;
        }
        public int getMark(){
            return this.mark;
        }
        public void setAnswer(String answer){
            this.answer=answer;
        }
        @Override
        public String toString(){
            return this.answer+"%&"+this.mark+"&"+this.comment+"%";
        }
        public SubmationData(String input){
            String[] s = input.split("&");
            answer=s[0].replace("%","");
            mark=Integer.parseInt(s[1]);
            comment=s[2].replace("%","");
        }
    }
}
