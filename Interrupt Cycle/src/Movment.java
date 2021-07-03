import java.util.Timer;
import java.util.TimerTask;

class T0 extends TimerTask{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Timer timer =new Timer();
		for(int i=0;i<80;i++) 
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				MemoryUI.moveLabelY1++;
				MemoryUI.INCmovingLabel.setLocation(MemoryUI.moveLabelX1, MemoryUI.moveLabelY1);
			}
		},MemoryUI.speed*i);		
	}
}	

class nextC extends TimerTask{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Timer timer =new Timer();
		for(int i=0;i<70;i++) 
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				MemoryUI.hiding.setBounds(MemoryUI.hiding.getX()+1,11,MemoryUI.hiding.getWidth()-1,800);
			}
		},MemoryUI.box.getSelectedIndex()*i);		
	}
}

class T1 extends TimerTask{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		MemoryUI.t0L.setVisible(true);
		Timer timer =new Timer();
		for(int i=0;i<80;i++) 
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				MemoryUI.moveLabelY1++;
				MemoryUI.INCmovingLabel.setLocation(MemoryUI.moveLabelX1, MemoryUI.moveLabelY1);
			}
		},MemoryUI.speed*i);
	}
}	

class T2 extends TimerTask{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		MemoryUI.INCmovingLabel.setText(MemoryUI.InstructionList.get(MemoryUI.selectedItem));
		MemoryUI.t1L.setVisible(true);
		Timer timer =new Timer();
		for(int i=0;i<150;i++) 
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				MemoryUI.moveLabelY1++;
				MemoryUI.INCmovingLabel.setLocation(MemoryUI.moveLabelX1, MemoryUI.moveLabelY1);
				
			}
		},MemoryUI.speed*i);
		
	}
	
}	

class T2Show extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		MemoryUI.t2L.setVisible(true);
		MemoryUI.code.setVisible(true);
		MemoryUI.BasicINC.setVisible(true);
	}
}

class D extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		MemoryUI.RectD14.setOpaque(false);
		MemoryUI.RectD14.setVisible(true);	
		
		MemoryUI.RectD13.setOpaque(false);
		MemoryUI.RectD13.setVisible(true);	
		
		MemoryUI.RectD12.setOpaque(false);
		MemoryUI.RectD12.setVisible(true);	
		
		Timer timer =new Timer();
		if(MemoryUI.D7) {
		for(int i=0;i<200;i++) 
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				MemoryUI.moveLabelX1--;
				MemoryUI.INCmovingLabel.setLocation(MemoryUI.moveLabelX1, MemoryUI.moveLabelY1);
			}
		},MemoryUI.speed*i);}
		else {
				for(int i=0;i<110;i++) 
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						MemoryUI.moveLabelX1++;
						MemoryUI.INCmovingLabel.setLocation(MemoryUI.moveLabelX1, MemoryUI.moveLabelY1);
					}
				},MemoryUI.speed*i);
		}
	}
	
}

class Move2I extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Timer timer =new Timer();
		for(int i=0;i<110;i++) 
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				MemoryUI.moveLabelY1++;
				MemoryUI.INCmovingLabel.setLocation(MemoryUI.moveLabelX1, MemoryUI.moveLabelY1);
			}
		},MemoryUI.speed*i);
	}
	
}

class I extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		MemoryUI.RectI.setOpaque(false);
        MemoryUI.RectI.setVisible(true);	
		
		Timer timer =new Timer();
		if(MemoryUI.I) {
		for(int i=0;i<50;i++) 
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				MemoryUI.moveLabelX1--;
				MemoryUI.INCmovingLabel.setLocation(MemoryUI.moveLabelX1, MemoryUI.moveLabelY1);
			}
		},MemoryUI.speed*i);}
		else {
				for(int i=0;i<50;i++) 
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						MemoryUI.moveLabelX1++;
						MemoryUI.INCmovingLabel.setLocation(MemoryUI.moveLabelX1, MemoryUI.moveLabelY1);
					}
				},MemoryUI.speed*i);
		}
	}
	
}

class T3 extends TimerTask{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		MemoryUI.t3L.setVisible(true);
		Timer timer =new Timer();
		for(int i=0;i<150;i++) 
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				MemoryUI.moveLabelY1++;
				MemoryUI.INCmovingLabel.setLocation(MemoryUI.moveLabelX1, MemoryUI.moveLabelY1);
			}
		},MemoryUI.speed*i);
		
		timer.schedule(new TimerTask() {public void run() {MemoryUI.lockTabs(true);}},150*MemoryUI.speed);	

	}
	
}	