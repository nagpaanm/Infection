package infection;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

public class Crosshair {
	public int xCor = 0;
	public int yCor = 0;
	public int cushion = 10;
	public int size = 30;
	
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.drawOval(xCor, yCor, size, size);
		g.drawLine(xCor + (size/2), yCor, xCor + (size/2), yCor + size);
		g.drawLine(xCor, yCor + (size/2), xCor + size, yCor + (size/2));
	}
	
	public void animate(){
		size = 60;
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				size = 30;
				timer.cancel();
			}
		};
		timer.schedule(task, 100);
		
	}

}