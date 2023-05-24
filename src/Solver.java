import java.util.ArrayList;

public class Solver {
    public void solve(Board board, Tile[][] b, Frame f){
        //long start = System.currentTimeMillis();
        addMarks(board, b, f);
        while(checkBoxes(b, board, f) || checkMarks(b, board, f) || checkRows(b, board, f) || checkColumns(b, board, f));
        bruteForce(board, f);
        //long stop = System.currentTimeMillis();
        //System.out.println(stop-start);
    }

    private void pause(Frame f){
        f.updateFrame();
        long start = System.currentTimeMillis();
		while(start >= System.currentTimeMillis() - 25);
    }

    private void addMarks(Board board, Tile[][] b, Frame f){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(b[i][j].getNum() == 0){
                    ArrayList<Integer> ans = board.getNeighbors(i, j);
                    for(int k = 1; k <= 9; k++){
                        if(!ans.contains(k)){
                            b[i][j].addMark(k);
                        }
                    }
                    pause(f);
                }
            }
        }
    }

    private boolean checkBoxes(Tile[][] b, Board board, Frame f){
        boolean shouldCont = false;
        for(int outi = 0; outi < 9; outi+=3){
            for(int outj = 0; outj < 9; outj+=3){
                int[] numMarks = {0, 0, 0, 0, 0, 0, 0, 0, 0};
                for(int i = outi; i <= outi + 2; i++){
                    for(int j = outj; j <= outj + 2; j++){
                        if(b[i][j].getNum() == 0){
                            ArrayList<Integer> marks = b[i][j].getMarks();
                            for(int m = 0; m < marks.size(); m++){
                                numMarks[marks.get(m)-1]++;
                            }
                        }
                    }
                }
                for(int m = 0; m < 9; m++){
                    if(numMarks[m] == 1){
                        for(int i = outi; i <= outi + 2; i++){
                            for(int j = outj; j <= outj + 2; j++){
                                if(b[i][j].getNum() == 0 && b[i][j].getMarks().contains(m+1)){
                                    board.setNum(i, j, m+1);
                                    pause(f);
                                    shouldCont = true;
                                }
                            }
                        }
                    }
                }

            }
        }
        return shouldCont;
    }

    private boolean checkRows(Tile[][] b, Board board, Frame f){
        boolean shouldCont = false;
        for(int i = 0; i < 9; i++){
            int[] numMarks = {0, 0, 0, 0, 0, 0, 0, 0, 0};
            for(int j = 0; j < 9; j++){
                if(b[i][j].getNum() == 0){
                    ArrayList<Integer> marks = b[i][j].getMarks();
                    for(int m = 0; m < marks.size(); m++){
                        numMarks[marks.get(m)-1]++;
                    }
                }
            }
            for(int m = 0; m < 9; m++){
                if(numMarks[m] == 1){
                        for(int j = 0; j < 9; j++){
                            if(b[i][j].getNum() == 0 && b[i][j].getMarks().contains(m+1)){
                                board.setNum(i, j, m+1);
                                pause(f);
                                shouldCont = true;
                            }
                        }
                    }
            }
        }
        return shouldCont;
    }

    private boolean checkColumns(Tile[][] b, Board board, Frame f){
        boolean shouldCont = false;
        for(int i = 0; i < 9; i++){
            int[] numMarks = {0, 0, 0, 0, 0, 0, 0, 0, 0};
            for(int j = 0; j < 9; j++){
                if(b[j][i].getNum() == 0){
                    ArrayList<Integer> marks = b[j][i].getMarks();
                    for(int m = 0; m < marks.size(); m++){
                        numMarks[marks.get(m)-1]++;
                    }
                }
            }
            for(int m = 0; m < 9; m++){
                if(numMarks[m] == 1){
                        for(int j = 0; j < 9; j++){
                            if(b[j][i].getNum() == 0 && b[j][i].getMarks().contains(m+1)){
                                board.setNum(j, i, m+1);
                                pause(f);
                                shouldCont = true;
                            }
                        }
                    }
            }
        }
        return shouldCont;
    }

    private boolean checkMarks(Tile[][] b, Board board, Frame f){
        boolean shouldCont = false;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(b[i][j].getNum() == 0 && b[i][j].getMarks().size() == 1){
                    board.setNum(i, j, b[i][j].getMarks().get(0));
                    pause(f);
                    shouldCont = true;
                }
            }
        }
        return shouldCont;
    }

    private boolean bruteForce(Board board, Frame f){
        int num = 0;
        Tile[][] clone = cloneBoard(board.getBoard());
        int x = 0;
        int y = 0;
        int size = 10;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board.getBoard()[i][j].getNum() == 0 && board.getBoard()[i][j].getMarks().size() < size){
                    x = i;
                    y = j;
                    size = board.getBoard()[i][j].getMarks().size();
                }
            }
        }
        boolean condition = true;
        while(condition){
            condition = false;
            board.setNum(x, y, board.getBoard()[x][y].getMarks().get(num));
            pause(f);
            while(checkBoxes(board.getBoard(), board, f) || checkMarks(board.getBoard(), board, f) || checkRows(board.getBoard(), board, f) || checkColumns(board.getBoard(), board, f));
            if(board.isFilled()){
                return true;
            }else if(board.isSolvable()){
                if(bruteForce(board, f)){
                    return true;
                }else if(num < (size-1)){
                    num++;
                    board.setBoard(clone);
                    addMarks(board, board.getBoard(), f);
                    condition = true;
                }else{
                    return false;
                }
            }else{
                if(num < (size-1)){
                    num++;
                    board.setBoard(clone);
                    addMarks(board, board.getBoard(), f);
                    condition = true;
                }else{
                    return false;
                }
            }
        } 
        return true;  
    }

    private Tile[][] cloneBoard(Tile[][] b){
        Tile[][] clone = new Tile[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                clone[i][j] = new Tile(b[i][j].getNum());
            }
        }
        return clone;
    }
}