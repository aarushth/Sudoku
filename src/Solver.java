import java.util.ArrayList;

public class Solver {
    public void solve(Board board, Tile[][] b, Frame f){
        addMarks(board, b, f);
        while(checkBoxes(b, board, f) || checkMarks(b, board, f) || checkRows(b, board, f) || checkColumns(b, board, f));
    }

    private void pause(Frame f){
        f.updateFrame();
        long start = System.currentTimeMillis();
		while(start >= System.currentTimeMillis() - 50);
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
}
