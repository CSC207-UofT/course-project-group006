package BackEnd.Managers;

import BackEnd.Entities.Student;
import BackEnd.Entities.Teacher;
import BackEnd.Entities.User;
import BackEnd.Interfaces.GeneralReadWriter;

import java.util.ArrayList;
import java.util.List;

public class TeacherManager extends UserManager{

    public TeacherManager(GeneralReadWriter userGate) {
        super(userGate);
    }
    @Override
    protected Teacher readUser(int id){
        //return new Teacher("placeholder","aaa","a",new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<>());
        List<String> info = this.userGate.readRow(id);
        System.out.println(id);
        if(info!=null&&info.size()>0&&info.get(0).equals(""+id)){
            String userName = info.get(1);
            String password = info.get(2);
            String email = info.get(4);
            String[] groups = info.get(5).split(",");
            String[] tests = info.get(6).split(",");
            ArrayList<Integer> gIds= new ArrayList<Integer>();
            for (String group : groups) {
                try {
                    gIds.add(Integer.parseInt(group));
                }catch (NumberFormatException e){
                    //do nothing
                }
            }
            ArrayList<Integer> tIds= new ArrayList<Integer>();
            for (String test : tests) {
                try {
                    tIds.add(Integer.parseInt(test));
                }catch (NumberFormatException e){
                    //do nothing
                }
            }
            return new Teacher(userName,password,email,gIds,tIds);
        }
        return null;
    }
    public boolean IsTeacher(int id){
        return this.userGate.readRow(id).size()>0;
    }
    public int LogIn(String userName,String password){
        int userType = getUserType(userName);
        int userID = getID(userName);
        if(userType==TEACHER){
            String pass = userGate.readByID(222, 3, userID).get(0);
            if (pass.equals(password)) {
                return userID;
            }
        }

        return -1;
    }
    public List<Integer> groups(int id){
        return readUser(id).getGroupCreated();
    }
    public List<Integer> tests(int id){
        return readUser(id).getOwnedTest();
    }
}
