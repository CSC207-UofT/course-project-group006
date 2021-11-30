package BackEnd;

public class IDcreater {
    public static int current=0;
    public static void setCurrent(int current){
        IDcreater.current=current;
    }
    public static int creat(){
        current+=1;
        return current;
    }
}
