import java.util.ArrayList;

public class Board {
    private Tile[][] board = new Tile[9][9];
    private int Xpos;
    private int Ypos;
    public Board(){
        Xpos = 0;
        Ypos = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = null;
            }
        }
        /*
        board[0][7] = new Tile(6);

        board[1][0] = new Tile(6);
        board[1][2] = new Tile(7);
        board[1][4] = new Tile(1);
        board[1][5] = new Tile(9);
        board[1][6] = new Tile(2);

        board[2][3] = new Tile(3);
        board[2][8] = new Tile(1);

        board[3][1] = new Tile(3);
        board[3][4] = new Tile(6);
        board[3][5] = new Tile(7);
        board[3][6] = new Tile(9);

        board[4][1] = new Tile(6);
        board[4][5] = new Tile(5);
        board[4][8] = new Tile(7);

        board[5][1] = new Tile(7);
        board[5][3] = new Tile(2);
        board[5][5] = new Tile(1);

        board[6][7] = new Tile(5);

        board[7][0] = new Tile(3);
        board[7][1] = new Tile(4);
        board[7][6] = new Tile(6);
        board[7][7] = new Tile(2);

        board[8][2] = new Tile(1);
        board[8][8] = new Tile(4);
        */
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == null){
                    board[i][j] = new Tile(0);
                }
            }
        }
        
    }
    public void printBoard(){
        StringBuffer string = new StringBuffer("");
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                string.append(board[i][j].getNum());
            }
            string.append("\r\n");
        }
        System.out.println(string);
    }
    public Tile[][] getBoard(){
        return board;
    }
    public ArrayList<Integer> getNeighbors(int x, int y){
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for(int i = 0; i < 9; i++){
            if(board[x][i].getNum() !=0){
                nums.add(board[x][i].getNum());
            }
            if(board[i][y].getNum() !=0){
                nums.add(board[i][y].getNum());
            }
        }
        
        for(int i = getOffset(x); i <= getOffset(x)+2; i++){
            for(int j = getOffset(y); j <= getOffset(y)+2; j++){
                if(board[i][j].getNum() != 0){
                    nums.add(board[i][j].getNum());
                }
            }
        }
        return nums;
    }

    private int getOffset(int n){
        if(n<3){
            return 0;
        }else if(n<6){
            return 3;
        }else if(n<9){
            return 6;
        }
        return 0;
    }

    public void setNum(int x, int y, int n){
        for(int i = 0; i < 9; i++){
            board[x][i].removeMark(n);
            board[i][y].removeMark(n);
        }
        for(int i = getOffset(x); i <= getOffset(x)+2; i++){
            for(int j = getOffset(y); j <= getOffset(y)+2; j++){
                board[i][j].removeMark(n); 
            }
        }
        board[x][y].setNum(n);
    }
    public void updatePos(Direction d){
        switch(d){
            case UP:
                Ypos--;
                break;
            case DOWN:
                Ypos++;
                break;
            case LEFT:
                Xpos--;
                break;
            case RIGHT:
                Xpos++;
                break;
        }
        Xpos = Math.max(0, Xpos);
        Xpos = Math.min(8, Xpos);
        Ypos = Math.max(0, Ypos);
        Ypos = Math.min(8, Ypos);
    }

    public int getXPos(){
        return Xpos;
    }
    public int getYPos(){
        return Ypos;
    }
}
