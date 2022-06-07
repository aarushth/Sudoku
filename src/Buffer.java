import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.BasicStroke;

public class Buffer{
    private static Color BLACKTRANSPARENT = new Color(0, 0, 0, 127);
    private int getXOffset(int n){
        switch(n%3){
            case 1:
                return -13;
            case 2:
                return 0;
            case 0:
                return 13;
        }
        return 0;
    }
    private int getYOffset(int n){
        if(n<=3){
            return -13;
        }else if(n<=6){
            return 0;
        }else if(n<=9){
            return 13;
        }
        return 0;
    }
    public void display(Tile[][] board, int x, int y, Graphics g){
       
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j].getNum() != 0){
                    if(board[i][j].isChangeable()){
                        g.setColor(Color.BLACK);
                    }else{
                        g.setColor(Color.RED);
                    }
                    g.setFont(new Font("Bold", 0, 30));
                    g.drawString(Integer.toString(board[i][j].getNum()), (i*50)+50, (j*50)+65);
                }else{
                    ArrayList<Integer> marks = board[i][j].getMarks();
                    g.setFont(new Font("Arial", 0, 13));
                    for(int k = 1; k < 10; k++){
                        if(marks.contains(k)){
                            g.drawString(Integer.toString(k), getXOffset(k)+(i*50)+50, getYOffset(k)+(j*50)+60);
                        }
                    }
                }
                ((Graphics2D) g).setStroke(new BasicStroke(3));
                g.setColor(Color.BLACK);
                g.drawRect((i*50)+30, (j*50)+30, 50, 50);
                g.setColor(Color.BLUE);
                ((Graphics2D) g).setStroke(new BasicStroke(10));
                g.drawRect(((i%3)*150)+30, ((j%3)*150)+30, 150, 150);
                if(i == x && j == y){
                    g.setColor(BLACKTRANSPARENT);
                    g.fillRect((i*50)+30, (j*50)+30, 50, 50);
                }
            }
            
        }
    }
}