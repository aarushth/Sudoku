import java.awt.Graphics;

public class Control implements Frame.EventListener{

    private Frame frame;
    private Board board;
    private Buffer buffer = new Buffer();
    private Solver solver = new Solver();
    public Control(){
        frame = new Frame(this);
        board = new Board();
    }

    @Override
    public void onPaintEvent(Graphics g) {
        buffer.display(board.getBoard(), board.getXPos(), board.getYPos(), g);
    }

    @Override
    public void onEnterEvent() {
        Thread thread = new Thread(){
            public void run(){
                solver.solve(board, board.getBoard(), frame);
                frame.updateFrame();
            }
        };
        thread.start();
    }

    @Override
    public void onArrowEvent(Direction d) {
        board.updatePos(d);
        frame.updateFrame();
    }

    @Override
    public void onNumberEvent(int n) {
       board.setNum(board.getXPos(), board.getYPos(), n);
       frame.updateFrame();
    }

    @Override
    public void onResetEvent() {
        board = new Board();
        frame.updateFrame();
    }
    
}
