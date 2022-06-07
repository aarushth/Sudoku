import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame extends JFrame implements Panel.EventListener{

    public interface EventListener{
		public void onPaintEvent(Graphics g);
        public void onEnterEvent();
		public void onArrowEvent(Direction d);
		public void onNumberEvent(int n);
	}

    private EventListener listener;
	private Panel p;

    public Frame(EventListener l){
        listener = l;
        setTitle("Sudoku");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(true);
        setBounds(30, 30, 550, 550);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		p = new Panel(this);
		add(p);

        addKeyListener(new KeyListener());
		
    }

    @Override
    public void onPaintEvent(Graphics g) {
        listener.onPaintEvent(g);
    }

    public void updateFrame(){
        repaint();
    }
    private class KeyListener extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent event) {
			int keyCode = event.getKeyCode();
			
			if(keyCode == KeyEvent.VK_ENTER) {
				listener.onEnterEvent();
			}else if(keyCode == KeyEvent.VK_UP) {
				listener.onArrowEvent(Direction.UP);
			}else if(keyCode == KeyEvent.VK_DOWN) {
				listener.onArrowEvent(Direction.DOWN);
			}else if(keyCode == KeyEvent.VK_LEFT) {
				listener.onArrowEvent(Direction.LEFT);
			}else if(keyCode == KeyEvent.VK_RIGHT) {
				listener.onArrowEvent(Direction.RIGHT);
			}else if(keyCode == KeyEvent.VK_1) {
				listener.onNumberEvent(1);
			}else if(keyCode == KeyEvent.VK_2) {
				listener.onNumberEvent(2);
			}else if(keyCode == KeyEvent.VK_3) {
				listener.onNumberEvent(3);
			}else if(keyCode == KeyEvent.VK_4) {
				listener.onNumberEvent(4);
			}else if(keyCode == KeyEvent.VK_5) {
				listener.onNumberEvent(5);
			}else if(keyCode == KeyEvent.VK_6) {
				listener.onNumberEvent(6);
			}else if(keyCode == KeyEvent.VK_7) {
				listener.onNumberEvent(7);
			}else if(keyCode == KeyEvent.VK_8) {
				listener.onNumberEvent(8);
			}else if(keyCode == KeyEvent.VK_9) {
				listener.onNumberEvent(9);
			}
			
		}
	} 
}