import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics2D;

public class RectDraw extends JPanel{
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);        
        Graphics2D g2d = (Graphics2D) g;    
   g2d.setPaint(new GradientPaint(10,100, Color.red,200,50, Color.red,true));
    g2d.setStroke(new BasicStroke(3f));
    g2d.drawRect(40,30,30,35); //drawing Outside border
    }
}
