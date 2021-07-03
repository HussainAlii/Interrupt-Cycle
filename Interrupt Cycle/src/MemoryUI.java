import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.event.ChangeListener;
import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;

public class MemoryUI extends JFrame {

	private JPanel contentPane;
	public static int selectedItem=-1;
	
	Random r=new Random();
	JPanel panelHanger = new JPanel();
     int Sizeofobject=0; //size of buttons and panels
	final int SpaceBetweenObj=4;
	
	public static void main(String[] args) {
		MemoryUI frame = new MemoryUI();
		frame.setVisible(true);
	}
	static JPanel hiding =new JPanel();
	static int moveLabelX1 = 542;
    static int moveLabelX2 = 50;
    static int moveLabelY1 = 0;
    static int moveLabelY2 = 40;
    static int speed = 30;
    static char Bin[];
    static boolean D7;
    static boolean I;
    
	static ArrayList<String> InstructionList;
	private JTextField InstructionField;
	private ArrayList<TimerTask> T;
	
	static JLabel INCmovingLabel;
	static JLabel t0L;
	static JLabel t1L;
	static JLabel t2L;
	static JLabel t3L;
	static JLabel code;
	static JLabel BasicINC;
	static RectDraw RectD12;
	static RectDraw RectD13;
	static RectDraw RectD14;
	static RectDraw RectI;
	static JTabbedPane tab;
	private JList INClist;
	static JComboBox box;  
	static DefaultListModel model;
	                         //t0 t1  t2 T2Show D M2I  I  t3
	private int time2Wait[]= {33,116,208,300,358,470,580,640};

	public MemoryUI() {
        setLocationRelativeTo(this);
		setResizable(false);
		setTitle("Timing Diagram");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 900);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		InstructionList =new ArrayList<String>();
       model = new DefaultListModel(); //I need it to insert String in jlist

		readFile();
		
		Bin=new char[16];
		
		
	    tab = new JTabbedPane(JTabbedPane.LEFT); 
		
		tab.setBackground(Color.WHITE);
		JLayeredPane Diagram =new JLayeredPane();
        
        	///////Memory panel//////
	    	Icon MemoryIcon = new ImageIcon(getClass().getResource("Resources/Memory.png"));
	    	
		    
		    JLabel INC = new JLabel(new ImageIcon(MemoryUI.class.getResource("/Resources/INC1.png")));
		    INC.setBounds(-90,-77,1200,900);
		    
		    BasicINC = new JLabel(new ImageIcon(MemoryUI.class.getResource("/Resources/basicComputer.png")));
		    BasicINC.setBounds(0, 0, 1050, 95);
		    BasicINC.setVisible(false);
		    
		    INCmovingLabel =new JLabel("----");
		    INCmovingLabel.setFont(new Font(null, Font.BOLD, 20));
		    INCmovingLabel.setBounds(moveLabelX1,moveLabelY1,moveLabelX2,moveLabelY2);
		    
		    t0L=new JLabel("T0:AR <-- PC");
		    t0L.setSize(new Dimension(120,120));
		    t0L.setFont(new Font(null, Font.BOLD, 15));
		    t0L.setLocation(542,40);
		    t0L.setVisible(false);
		    
		    t1L=new JLabel("T1:IR <-- M[AR] Fetch");
		    t1L.setSize(new Dimension(160,120));
		    t1L.setFont(new Font(null, Font.BOLD, 15));
		    t1L.setLocation(545,115);
		    t1L.setVisible(false);
		    
		    t2L=new JLabel("T2:Decode");
		    t2L.setSize(new Dimension(120,120));
		    t2L.setFont(new Font(null, Font.BOLD, 15));
		    t2L.setLocation(545,205);
		    t2L.setVisible(false);
		    
		    t3L=new JLabel();
		    t3L.setSize(new Dimension(400,400));
		    t3L.setFont(new Font(null, Font.BOLD, 17));
		    t3L.setLocation(350,200);
		    t3L.setVisible(false);
		    
		    code=new JLabel();//("1     1   1   1       1 1 1 1 1 1 1 1 1 1 1 1")
		    code.setFont(new Font(null, Font.BOLD, 26));
		    code.setBounds(312, 15, 1050, 95);
		    code.setVisible(false); 
		    
		    RectD14=new RectDraw();//("1     1   1   1       1 1 1 1 1 1 1 1 1 1 1 1")
		    RectD14.setBounds(312, 15, 270, 270);
		    RectD14.setVisible(false); 

		    RectD13=new RectDraw();//("1     1   1   1       1 1 1 1 1 1 1 1 1 1 1 1")
		    RectD13.setBounds(348, 15, 270, 270);
		    RectD13.setVisible(false); 
		    
		    RectD12=new RectDraw();//("1     1   1   1       1 1 1 1 1 1 1 1 1 1 1 1")
		    RectD12.setBounds(382, 15, 270, 270);
		    RectD12.setVisible(false); 
		    
		    RectI=new RectDraw();//("1     1   1   1       1 1 1 1 1 1 1 1 1 1 1 1")
		    RectI.setBounds(268, 15, 240, 240);
		    RectI.setVisible(false); 

		    tab.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(tab.getSelectedIndex()==1){
					    speed=Integer.parseInt(box.getSelectedItem().toString());
						selectedItem=INClist.getSelectedIndex();	
						
						Bin = Hex2Bin(InstructionList.get(selectedItem));
						t3L.setText(T3Text());
						setCodeInLabel();
						D7=getD7(Bin);
						I=getI(Bin);
						Timer timer =null;
					    timer=new Timer();
					    initialFlowChart();
						for(int i=0;i<T.size();i++) 
						timer.schedule(T.get(i),time2Wait[i]*speed);
						lockTabs(false);
					} //is tab pressed end
					
					if(tab.getSelectedIndex()==2){
							removeAllDraw(); //clear all component
		    		    	Sizeofobject=25*InstructionList.size(); //One instruction have 22 panels * number of instructions
		    				Draw();
		    			    int size=(60*(Sizeofobject+1)+(SpaceBetweenObj*Sizeofobject+1)+SpaceBetweenObj); //calculate the whole size
		    			    panelHanger.setPreferredSize(new Dimension(0, size)); //the first zero don't care because it will not affect anything
		    			    
		    			   
		    		   
						
					} //is tab pressed end
					
				} //stateChanged end

			});
		    ///////Table panel//////
		    JPanel Memory = new JPanel();
		    tab.addTab("Memory", MemoryIcon, Memory, "Memory");
		    Memory.setLayout(null);
		    
		    INClist = new JList(model);
		    INClist.setFont(new Font(null, Font.BOLD, 20));
		    INClist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    JScrollPane s=new JScrollPane(INClist);
		    s.setBounds(354, 136, 278, 689);
		    Memory.add(s);
		    INClist.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mousePressed(MouseEvent e) {
		    		if(!INClist.isSelectionEmpty())
		    		 tab.setEnabledAt(1, true);
		    	}
			});
		    
		    InstructionField = new JTextField();
		    InstructionField.setBounds(393, 36, 239, 38);
		    Memory.add(InstructionField);
		    InstructionField.setColumns(10);
		    	    	
		    	    	JLabel lblMemoryhex = new JLabel("Memory(Hex)");
		    	    	lblMemoryhex.setFont(new Font(null, Font.BOLD, 15));
		    	    	lblMemoryhex.setBounds(436, 72, 136, 57);
		    	    	Memory.add(lblMemoryhex);
		    	    	
		    	    	JLabel lblAnimationSpeed = new JLabel("Animation Speed:");
		    	    	lblAnimationSpeed.setBounds(50, 34, 164, 38);
		    	    	lblAnimationSpeed.setFont(new Font(null,Font.BOLD,15));
		    	    	Memory.add(lblAnimationSpeed);
		    	    	
		    	    	JLabel SpeedL = new JLabel("");
		    	    	SpeedL.setIcon(new ImageIcon(MemoryUI.class.getResource("/Resources/Speed.png")));
		    	    	SpeedL.setBounds(50, 78, 30, 51);
		    	    	Memory.add(SpeedL);
		    	    	
		    	        box = new JComboBox();
		    	        box.setToolTipText("Higher number will make the movement Slower!");
		    	        box.setMaximumRowCount(40);
		    	    	box.setBounds(81, 83, 96, 38);
		    	    	Memory.add(box);
		    	    	
		    	    	for (int i = 0; i <= 40; i++) 
		    	    	box.addItem(i);
		    	        box.setSelectedIndex(25);
		    	        
		    	        JLabel Add = new JLabel("");
		    	        Add.setIcon(new ImageIcon(MemoryUI.class.getResource("/Resources/Add.png")));
		    	        Add.setBounds(354, 32, 39, 47);
		    	        Memory.add(Add);
		    	        
		    	        Add.addMouseListener(new MouseAdapter() {
		    	        	@Override
		    	        	public void mousePressed(MouseEvent e) {
		    	        		if(InstructionField.getText().length()==4) {
				    	    		InstructionList.add(InstructionField.getText());
				    	    		model.addElement(InstructionList.size()+"."+InstructionList.get(InstructionList.size()-1));} 
				    	    		else
				    	    			JOptionPane.showMessageDialog(MemoryUI.this, "Wrong Instruction!\nInstruction should be 4 digit","Error", JOptionPane.ERROR_MESSAGE);
		    	        	}
						});
		    	        
		    JPanel FlowChart =new JPanel();
		    
		    			///////FlowChart panel//////			
		    		    tab.addTab("FlowChart",new ImageIcon(getClass().getResource("/Resources/flowchart.png")),FlowChart,"FlowChart");
		    		    tab.setEnabledAt(1, false); //flowchart panel
		    		    FlowChart.setLayout(new BorderLayout(5, 5));
		    		    
		    		    
		    		    JLayeredPane CenterlayeredPane = new JLayeredPane();
		    		    FlowChart.add(CenterlayeredPane, BorderLayout.CENTER);
		    		    CenterlayeredPane.setLayout(null);
		    		    CenterlayeredPane.add(INC,0);
		    		    
		    		    JLayeredPane NorthlayeredPane = new JLayeredPane();
		    		    FlowChart.add(NorthlayeredPane, BorderLayout.NORTH);
		    		    NorthlayeredPane.setPreferredSize(new Dimension(500,90));
		    		    NorthlayeredPane.setLayout(null);
		    		    NorthlayeredPane.add(BasicINC,0);
		    		    CenterlayeredPane.add(INCmovingLabel,1,0);
		    		    CenterlayeredPane.add(t0L,1,0);
		    		    CenterlayeredPane.add(t1L,1,0);
		    		    CenterlayeredPane.add(t2L,1,0);
		    		    CenterlayeredPane.add(t3L,1,0);
		    		    NorthlayeredPane.add(code,1,0);
		    		    NorthlayeredPane.add(RectD12,2,0);
		    		    NorthlayeredPane.add(RectD13,2,0);
		    		    NorthlayeredPane.add(RectD14,2,0);
		    		    NorthlayeredPane.add(RectI,2,0);
		    ///////Diagram panel//////
		    		   
		    		    hiding.setBounds(189,11,720,800);
		    		    //hiding.setBackground(Color.red);
		    		    
		    		    JButton nextclk =new JButton("Clock+1");
		    		    nextclk.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								Timer timer2 =null;
							    timer2=new Timer();
							    nextC t =new nextC();
								timer2.schedule(t,box.getSelectedIndex());
								//hiding.setBounds(hiding.getX()+70,11,hiding.getWidth()-70,800);
								
							}
						});
		    		    nextclk.setBounds(10,10,80,30);
		    		    
		    		    Diagram.add(nextclk);
		    		    panelHanger.setLayout(new FlowLayout(FlowLayout.LEFT,0,SpaceBetweenObj)); //to make vertical gap size 4 between all panels
	    			    JScrollPane scrollPane = new JScrollPane(panelHanger); //to create scrollpane
	    			    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    			    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    			    
	    			    Diagram.add(scrollPane,BorderLayout.CENTER,2);
	    			    Diagram.setLayout(null);
	    			    scrollPane.setBounds(100,10,850,830);
	    			    
		     Icon DiagramIcon = new ImageIcon(getClass().getResource("Resources/Diagram.png"));
		     tab.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					hiding.setBounds(189,11,720,800);
				}
			});
		     scrollPane.getVerticalScrollBar().getModel().addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
		    		 Diagram.moveToFront(hiding);

				}
			});

		     scrollPane.addMouseListener(new MouseAdapter() {
		    	 
		    	 @Override
		    	public void mouseEntered(MouseEvent e) {
		    		// TODO Auto-generated method stub
		    		 Diagram.moveToFront(hiding);
		    	}
		    	 
		    	 @Override
		    	public void mouseExited(MouseEvent e) {
		    		// TODO Auto-generated method stub
		    		 Diagram.moveToFront(hiding);
		    	}
		    	 @Override
		    	public void mouseWheelMoved(MouseWheelEvent e) {
		    		// TODO Auto-generated method stub
		    		 Diagram.moveToFront(hiding);
		    	}
		    	 
		    	 @Override
		    	public void mouseMoved(MouseEvent e) {
		    		// TODO Auto-generated method stub
		    		 Diagram.moveToFront(hiding);
		    	}
			});
			    tab.addTab("Diagram",DiagramIcon,Diagram,"Diagram");
	        //////////////////////////
	        getContentPane().add(tab,BorderLayout.CENTER); 
	        Diagram.add(hiding,1);
				
	}
	
		private static void readFile() {
			BufferedReader br = null;
			String line;
			
			try {
				br=new BufferedReader(new FileReader("instruction.txt"));
			while((line=br.readLine())!=null) { //to read next line: if next line not null
				InstructionList.add(line);
				model.addElement(InstructionList.size()+"."+InstructionList.get(InstructionList.size()-1));} 
			
			}catch (Exception e) {
			}finally {
				try {br.close();}catch (Exception e) {}
			}		
	}

	private void removeAllDraw() {
		panelHanger.removeAll();
		
	}
	
	private void drawClK(int numOfIns) {
		 ArrayList<Point> point=new ArrayList<>();
		int high=20;
		int highx=35;
		
		JLabel clk=new JLabel(InstructionList.get(numOfIns)+".CLK");
		clk.setBounds(6,10,100,40);
		
		clk.setIcon(new ImageIcon(getClass().getResource("Resources/OneZero.png")));
		clk.setIconTextGap(10);
		clk.setHorizontalTextPosition(JLabel.LEFT);
		
		point.add(new Point(90, 40));
		point.add(new Point(90, 20));
		
		for (int j= 0; j < 400;j=j+20) {
			point.add(new Point(point.get(point.size()-1).x+highx, point.get(point.size()-1).y));
			point.add(new Point(point.get(point.size()-1).x, point.get(point.size()-1).y+high));
			point.add(new Point(point.get(point.size()-1).x+highx, point.get(point.size()-1).y));
			point.add(new Point(point.get(point.size()-1).x, point.get(point.size()-1).y-high));
		}
		
		
		
		Paint paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
	   JPanel p=new JPanel();
	   p=paint;
	    p.add(clk);
	    p.setLayout(null);
	    p.setPreferredSize(new Dimension(780,60));	
	    p.setBorder(new LineBorder(Color.black));
	    panelHanger.add(p);
}
	
	private void Draw() {
		
	    
		for (int nOfInst = 0; nOfInst< InstructionList.size(); nOfInst++) {
		drawClK(nOfInst); //draw clk

	   char BinNum[] = Hex2Bin(InstructionList.get(nOfInst));
	   boolean D7=getD7(BinNum);
	   I=getI(BinNum);
	   
	   int Category=-1;
	   switch (BinNum[15]) {
		case '0':
			if(BinNum[14]=='1' &&BinNum[13]=='1' &&BinNum[12]=='1') Category=2;
			else Category=0;
					
			break;
		case '1':
			if(BinNum[14]=='1' &&BinNum[13]=='1' &&BinNum[12]=='1') Category=3;
			else Category=1;
		}
	   
		for (int i = 1; i <=21; i++) {
			 JLabel label=new JLabel();
			Paint paint = null;
			ArrayList<Point> point=new ArrayList<>();
			switch (i) {
			case 1:
			 label.setText("T0  ");
			 point=getPoint(new boolean[]{false,true,false,false,false,false,false,false,true,false});
			 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 2:
				label.setText("T1  ");
				point=getPoint(new boolean[]{false,false,true,false,false,false,false,false,false,true});
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 3:
				label.setText("T2  ");
				point=getPoint(new boolean[]{false,false,false,true,false,false,false,false,false,true});
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 4:
				label.setText("T3  ");
				 point=getPoint(new boolean[]{false,false,false,false,true,false,false,false,false,false});
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 5:
				label.setText("T4  ");
				 point=getPoint(new boolean[]{false,false,false,false,false,true,false,false,false,false});
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 6:
				label.setText("T5  ");
				 point=getPoint(new boolean[]{false,false,false,false,false,false,true,false,false,false});
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 7:
				label.setText("T6  ");
				 point=getPoint(new boolean[]{false,false,false,false,false,false,false,true,false,false});
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 8:
				label.setText("BUS(S2)");
				 point=getPoint(getBoolean(8,Category,nOfInst));
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 9:
				label.setText("BUS(S1)");
				 point=getPoint(getBoolean(9,Category,nOfInst));
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 10:
				label.setText("BUS(S0)");
				 point=getPoint(getBoolean(10,Category,nOfInst));
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 11:
				label.setText("PC(LD)");
				 point=getPoint(getBoolean(11,Category,nOfInst));
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 12:
				label.setText("PC(INR)");
				 point=getPoint(getBoolean(12,Category,nOfInst));
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 13:
				label.setText("AR(LD)");
				 point=getPoint(getBoolean(13,Category,nOfInst));
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 14:
				label.setText("AR(INC)");
				 point=getPoint(getBoolean(14,Category,nOfInst));
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 15:
				label.setText("IR(LD)");
				 point=getPoint(getBoolean(15,Category,nOfInst));
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 16:
				label.setText("M(Read)");
				 point=getPoint(getBoolean(16,Category,nOfInst));
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 17:
				label.setText("M(Write)");
				 point=getPoint(getBoolean(17,Category,nOfInst));
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 18:
				label.setText("SC(CLR)");
				 point=getPoint(getBoolean(18,Category,nOfInst));
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 19:
				label.setText("SC(INC)");
				 point=getPoint(getBoolean(19,Category,nOfInst));
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 20:
				label.setText("I  ");
				 point=getPoint(getBoolean(20,Category,nOfInst));
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;
			case 21:
				char D = InstructionList.get(nOfInst).charAt(0);
				char RIO = 'D';
				if(D == '7'|| D == 'F') {
					D =' ';
					RIO = ' ';
				}
				else if(D == '8')
					D = '0';
				else if(D == '9')
					D = '1';
				else if(D == 'A')
					D = '2';
				else if(D == 'B')
					D = '3';
				else if(D == 'C')
					D = '4';
				else if(D == 'D')
					D = '5';
				else if(D == 'E')
					D = '6';
				label.setText(RIO+" "+D);
				 point=getPoint(getBoolean(21,Category,nOfInst));
				 paint =new Paint(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),point);
				break;

			}
			
			JPanel p=new JPanel();
			  p=paint;
			  label.setIcon(new ImageIcon(getClass().getResource("Resources/OneZero.png")));
			  label.setIconTextGap(8);
			  label.setHorizontalTextPosition(JLabel.LEFT);
			p.add(label);
		    p.setLayout(null);
		    p.setPreferredSize(new Dimension(780,60));	
			 label.setBounds(6,20,100,40);
		    panelHanger.add(p);
			
		
		}
	}
		
}

	private boolean[] getBoolean(int Case, int category,int numOfIns) {
		boolean b[]= new boolean[10];
		//boolean b[]={false,true,false,false,false,false,false,true,false,false};
		String instruction = InstructionList.get(numOfIns);
		char opCode = instruction.charAt(0);
		for(int i = 0; i< b.length; i++)
			b[i] = false;
		if(Case == 20 && I == true) { //I case
			for(int i = 0; i< b.length; i++)
				b[i] = true;
			return b;
		}
		
		if(Case == 19) {
			b[1] = true;
			b[2] = true;
			b[3] = true;
			if(opCode == '3' || opCode == 'B' || opCode == '4' || opCode == 'C')
				b[4] = true;
			if(opCode == '0' || opCode == '8' || opCode == '1' || opCode == '9' || opCode == '2' || opCode == 'A' || opCode == '5' || opCode == 'D') {
				b[4] = true;
				b[5] = true;
			}
			if(opCode == '6' || opCode == 'E') {
				b[4] = true;
				b[5] = true;
				b[6] = true;
			}	
			b[8] = true;
			b[9] = true;
			return b;
			
		}
			
				boolean RIO2 = false;
				
				if(opCode == 'F' || opCode == '7')
					RIO2 = true;
				for (int q = 0; q < 1; q++) {
					if(RIO2)
						break;
					if(Case == 21 && (opCode != 'F' || opCode != '7')) {
						for(int i = 3; i< b.length; i++)
							b[i] = true;
						return b;
					}
				}
		
		
		
		if(category == 1)
			category = 0;
		
		if(Case == 18)
			b[0] = true;
		else
			b[0] = false; //SC clear
		
		if(Case == 13 || Case == 9) //T0: AR LD and PC s1
			b[1] = true;
		else
			b[1] = false;
		
		if(Case == 15 || Case == 8 || Case == 9 || Case == 10 || Case == 12 || Case == 16)//T1: IR LD, S2,S1,S0,PC Inc, memory read
			b[2] = true;
		else
			b[2] = false;
		
		if(Case == 8  || Case == 10 || Case == 13 ) //T2: (S2,S0 for IR), AR LD
			b[3] = true;
		else
			b[3] = false;
		
		if((Case == 8 || Case == 9 || Case == 10) && (opCode =='8' || opCode =='9' || opCode =='A' || opCode =='B' || opCode =='C' || opCode =='D' || opCode =='E') ||
				instruction == "7020" && Case == 14 || instruction == "7010" && Case == 12 /*&& AC[15] == 0*/ || instruction == "7008" && Case == 12 /*&& AC[15] == 1*/ || instruction == "7004" && Case == 12 /*&& AC == 0*/ ||
				category == '2' && Case == 18 || category == '3' && Case == 18 || Case == 16 && I == true || Case == 13 && I == true) 
			b[4] = true; //T3 Selection lines for M read (InDirect), memory read with InDirect, register instructions(5), SC clear on register & I/O
		else
			b[4] = false; //not included: I/O F200 ,F100 :PC Inc if(F200 && fgi == 1 && Case == 12 || F100 && fgi == 0 && Case == 12)
		
		if((opCode == '0' || opCode == '8' || opCode == '1' || opCode == '9' || opCode == '2' || opCode == 'A' || opCode == '3' || opCode == 'B' || opCode == '6' || opCode == 'E' )&& Case == 8 ||
				(opCode == '0' || opCode == '8' || opCode == '1' || opCode == '9' || opCode == '2' || opCode == 'A' || opCode == '5' || opCode == 'D' || opCode == '6' || opCode == 'E' )&& Case == 9 ||
				(opCode == '0' || opCode == '8' || opCode == '1' || opCode == '9' || opCode == '2' || opCode == 'A' || opCode == '4' || opCode == 'C' || opCode == '6' || opCode == 'E' )&& Case == 10 ||
				(opCode == '4' || opCode == 'C')&& Case == 11 || category == '0' && Case == 18 && (opCode == '3' || opCode == '4' || opCode == 'B' || opCode == 'C') ||
				Case == 17 && (opCode == '3' || opCode == 'B' || opCode == '5' || opCode == 'D') || Case == 14 && (opCode == '5' || opCode == 'D') )
			b[5] = true; //T4: all cases when s2/1/0 will be 1, PC LD D4, SC clr, M write D3 + D5, AR Inc
		else
			b[5] = false;

		if(Case == 10 && (opCode == '5' || opCode == 'D') || (opCode == '5' || opCode == 'D')&& Case == 11 ||
				category == '0' && Case == 18 && (opCode == '0' || opCode == '1' || opCode == '2' || opCode == '5' || opCode == '8' || opCode == '9' || opCode == 'A' || opCode == 'D'))
			b[6] = true; //T5: AR S1, PC LD, SC clr when end with T5
		else
			b[6] = false;

		if((Case == 9 || Case == 10) && (opCode == '6' || opCode == 'E') || Case == 17 && (opCode == '6' || opCode == 'E')) //T6: DR selection lines, M write
			b[7] = true;
		else
			b[7] = false;

		
		if(Case == 13 || Case == 9) //T0: AR LD and PC s1
			b[9] = true;
		else
			b[9] = false;
		
		/*
		if(category==0 || category == 1) {//memory direct
			all+1
			case 10: //PC LoaD
			case 11://PC Inc
			case 12://AR LD
			case 13://AR Inc
			case 14://IR LD
			case 15://memory read
			case 16://memory write
			case 17: //SC clear
			case 18://SC Inc
			case 19://I flip flop
			case 20://D5
			case 21://D7
			}
			*/
		
		/* value of D(opCode): 0	1	2	3	4	5	6
		 * value of s2       : 1	1	1	1	0	0	1
		 
		if(opCode == '0' || opCode == '8' || opCode == '1' || opCode == '9' || opCode == '2' || opCode == 'A' || opCode == '3' || opCode == 'B' || opCode == '6' || opCode == 'E' ) //all cases when s2 will be 1
			b[5] = true;
		else
			b[5] = false;
		b[6] = false;//T5, all cases are 0
		b[7] = false;//T6, from DR 011
		b[8] = false;//T0 again???
		b[9] = false;//T1 again???
		 value of D(opCode): 0	1	2	3	4	5	6
		 * value of s1       : 1	1	1	0	0	1	1
		 
		if(opCode == '0' || opCode == '8' || opCode == '1' || opCode == '9' || opCode == '2' || opCode == 'A' || opCode == '5' || opCode == 'D' || opCode == '6' || opCode == 'E' ) //all cases when s1 will be 1
		 value of D(opCode): 0	1	2	3	4	5	6
		 * value of s0       : 1	1	1	0	1	0	1
		 */
		
		return b;
	}
	
	

	private ArrayList<Point> getPoint(boolean[] upperPoint) {
		ArrayList<Point> p =new ArrayList<Point>();
		if(upperPoint[0]==true) {
			p.add(new Point(90, 30));
			movXHigh(p);
		}else {
			p.add(new Point(90, 50));
			movXlow(p);
		}
		for(int i=1;i<10;i++) 
		if(upperPoint[i]==true) {
			
			if(upperPoint[i-1]==true) {
				movXHigh(p);
			}else {
				movYHigh(p);
				movXHigh(p);
			}
			
		}else {
			if(upperPoint[i-1]==true) {
				movYlow(p);
				movXlow(p);
			}else {
				movXlow(p);
			}
		}
		return p;
	}

	private void movXlow(ArrayList<Point> p) {p.add(new Point(p.get(p.size()-1).x+70, 50));}
	private void movXHigh(ArrayList<Point> p) {p.add(new Point(p.get(p.size()-1).x+70, 30));}
	private void movYlow(ArrayList<Point> p) {p.add(new Point(p.get(p.size()-1).x, 50));}
	private void movYHigh(ArrayList<Point> p) {p.add(new Point(p.get(p.size()-1).x, 30));}

	private String T3Text() {
		String T=null;
		
		switch (Bin[15]) {
		case '0':
			if(Bin[14]=='1' &&Bin[13]=='1' &&Bin[12]=='1') T="T(3):Execute Register\n Instruction"; 
			else T="T(3):Nothing";
					
			break;
		case '1':
			if(Bin[14]=='1' &&Bin[13]=='1' &&Bin[12]=='1') T="T(3):Execute I/O\n Instruction";
			else T="T(3):AR <-- M[AR]";
		}
		
		return T;
	}

	static void lockTabs(boolean b) {
		tab.setEnabledAt(0, b);
		tab.setEnabledAt(1, b);
		tab.setEnabledAt(2, b);
	}
	
	private boolean getD7(char Bin[]) {
		if(Bin[14]=='1'&&Bin[13]=='1'&&Bin[12]=='1') {return true;}
		else
		return false;
	}
	
	private boolean getI(char Bin[]) {
		if(Bin[15]=='1') {return true;}
		else
		return false;
	}
	
	private void initialFlowChart() {
	    INCmovingLabel.setText("----");
	    code.setVisible(false);
	    t0L.setVisible(false);
	    t1L.setVisible(false);
	    t2L.setVisible(false); 
	    t3L.setVisible(false); 
	    RectI.setVisible(false);
	    RectD12.setVisible(false);
	    RectD13.setVisible(false);
	    RectD14.setVisible(false);
	    BasicINC.setVisible(false);
	    
	    moveLabelX1 = 542;
	    moveLabelX2 = 50;
	    moveLabelY1 = 0;
	    moveLabelY2 = 40;
	    INCmovingLabel.setBounds(542,0,50,40);
		T =new ArrayList<TimerTask>();
		T0 t0 =new T0();
		T1 t1 =new T1();
		T2 t2 =new T2();
		T2Show t2show =new T2Show();
		D d =new D();
		I i =new I();
		Move2I M2I =new Move2I();
		T3 t3 =new T3();
		
		T.add(t0);
		T.add(t1);
		T.add(t2);
		T.add(t2show);
		T.add(d);
		T.add(M2I);
		T.add(i);
		T.add(t3);

	}

	public char[] Hex2Bin(String hex){
		char [] Bin =new char[16];
		String x="";
		  for(int i=0;i<4;i++) {
			  
			  switch (hex.charAt(i)) {
			  case '0': 
	                x+="0000";
	                break; 
	            case '1': 
	            	x+="0001"; 
	                break; 
	            case '2': 
	            	x+="0010"; 
	                break; 
	            case '3': 
	            	x+="0011"; 
	                break; 
	            case '4': 
	            	x+="0100";
	                break; 
	            case '5': 
	            	x+="0101";; 
	                break; 
	            case '6': 
	            	x+="0110"; 
	                break; 
	            case '7': 
	            	x+="0111";
	                break; 
	            case '8': 
	            	x+="1000"; 
	                break; 
	            case '9': 
	            	x+="1001";
	                break; 
	            case 'A': 
	            case 'a': 
	            	x+="1010";
	                break; 
	            case 'B': 
	            case 'b': 
	            	x+="1011";
	                break; 
	            case 'C': 
	            case 'c': 
	            	x+="1100";
	                break; 
	            case 'D': 
	            case 'd': 
	            	x+="1101";
	                break; 
	            case 'E': 
	            case 'e': 
	            	x+="1110";
	                break; 
	            case 'F': 
	            case 'f': 
	            	x+="1111";
			} //switch end
		  }			  		  
		  for(int i=0;i<16;i++) {
			  Bin[i]=x.charAt(15-i);
		  }
		
		return Bin;
	}
	
	
	private void setCodeInLabel() {
		String x="";
		x=(Bin[15]+"     "+Bin[14]+"   "+Bin[13]+"   "+Bin[12]+"     "+Bin[11]+" "+Bin[10]+"  "+Bin[9]+" "+Bin[8]+" "+Bin[7]+" "+Bin[6]+" "+Bin[5]+" "+Bin[4]+" "+Bin[3]+" "+Bin[2]+" "+Bin[1]+" "+Bin[0]);
		code.setText(x);
	}
	
}
