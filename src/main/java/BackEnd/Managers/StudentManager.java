package BackEnd.Managers;

import BackEnd.Entities.Student;
import BackEnd.Entities.Teacher;
import BackEnd.Entities.User;
import BackEnd.Interfaces.GeneralReadWriter;

import java.util.ArrayList;
import java.util.List;

public class StudentManager extends UserManager{
    public StudentManager(GeneralReadWriter userGate) {
        super(userGate);
    }
    protected User readUser(int id){
        //return new Teacher("placeholder","aaa","a",new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<>());
    List<String> info = this.userGate.readRow(id);
    if(info.get(0).equals(""+id)){
        String userName = info.get(1);
        String password = info.get(2);
        String level = info.get(9);
        String email = info.get(4);
        return new Student(userName,password,email,Integer.parseInt(level));
    }
    return null;
    }
}
