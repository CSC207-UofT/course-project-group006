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
    protected User readUser(int id){
        //return new Teacher("placeholder","aaa","a",new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<>());
        List<String> info = this.userGate.readRow(id);
        if(info.get(0).equals(""+id)){
            String userName = info.get(1);
            String password = info.get(2);
            String email = info.get(4);
            String[] groups = info.get(5).split(",");
            String[] tests = info.get(6).split(",");
            ArrayList<Integer> gIds= new ArrayList<Integer>();
            for (String group : groups) {
                gIds.add(Integer.parseInt(group));
            }
            ArrayList<Integer> tIds= new ArrayList<Integer>();
            for (String test : tests) {
                tIds.add(Integer.parseInt(test));
            }
            return new Teacher(userName,password,email,gIds,tIds);
        }
        return null;
    }
}
