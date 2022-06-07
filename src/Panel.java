import javax.swing.JPanel;
import java.awt.Graphics;

public class Panel extends JPanel{

    public interface EventListener{
        public void onPaintEvent(Graphics g);
    }

    private EventListener listener;

    public Panel(EventListener e){
        listener = e;
    }
    public void paint(Graphics g){
        listener.onPaintEvent(g);
    }
}
