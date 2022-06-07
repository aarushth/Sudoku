import java.util.ArrayList;

public class Tile {
    private ArrayList<Integer> marks = new ArrayList<Integer>();
    private int num;
    private boolean changeable;

    public Tile(int n){
        if(n == 0){
            changeable = true;
            num = 0;
        }else{
            num = n;
            changeable = false;
        }
    }

    public boolean isChangeable(){
        return changeable;
    }
    public void addMark(int n){
        if(changeable && 0< n && n < 10 && !marks.contains(n)){
            marks.add(n);
        }
    }

    public void removeMark(int n){
        if(changeable && marks.contains(n)){
            marks.remove((Object) n);
        }
    }
    public void resetMarks(){
        marks = new ArrayList<Integer>();
    }
    public void setNum(int n){
        if(changeable){
            num = n;
            resetMarks();
        }
    }
    public int getNum(){
        return num;
    }
    public ArrayList<Integer> getMarks(){
        return marks;
    }
}
