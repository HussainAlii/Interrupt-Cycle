import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class Paint extends JPanel{
	
	private ArrayList<Point> points;
	Color c;
	
	public Paint(Color c,ArrayList<Point> p) {
		this.c=c;
		points=new ArrayList<>();
		points=p;
	}
	

	@Override
    public void paintComponent(Graphics g){
            super.paintComponent(g);
            
		Graphics2D g2d= (Graphics2D) g;
		g2d.setColor(c);
		g2d.setStroke(new BasicStroke(4));
		
		try {
		for (int i = 0; i < points.size(); i++) {
		g2d.drawLine(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y);
				}}catch (Exception e) {}
	}
	
	
	
}
