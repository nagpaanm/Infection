package infection;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

public class Grenade extends Bullet{

	public int size = 10;
	public int explosion = 1;
	public int explosionSize = 200;
	public boolean landed = false;
	public boolean isHit = false;
	
	public Grenade(int xCor, int yCor, int destX, int destY, boolean collision) {
		super(xCor, yCor, destX, destY, collision);
	}

	public void createGrenade(Graphics g){
		g.setColor(new Color(46,139,87));
		g.fillOval((int) xCor, (int) yCor, size, size);
		g.setColor(Color.BLACK);
		g.drawOval((int) xCor, (int) yCor, size, size);
		if (speedX >= 0){
			if (xCor <= destX){
				xCor += differenceX/2;
				yCor += differenceY/2;
			}
		}
		if (speedX < 0){
			if (xCor > destX){
				xCor += differenceX/2;
				yCor += differenceY/2;
			}
		}
		if (Math.max(Math.abs(xCor), Math.abs(destX)) - Math.min(Math.abs(xCor), Math.abs(destX)) < 5 && 
			Math.max(Math.abs(yCor), Math.abs(destY)) - Math.min(Math.abs(yCor), Math.abs(destY)) < 5){
			landed = true;
			differenceX = 0;
			differenceY = 0;
		}
		if (speedX == 0 && speedY == 0){
			// if you click directly in the middle of the player
			size = 0;
		}
	}

	public void explode(Graphics g){
		g.setColor(new Color(255,215,0));
		g.fillOval((int) xCor, (int) yCor, (int) explosion, (int) explosion);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				size = 0;
				if (explosion < 200 && explosion != 0){
					explosion += 5;
					xCor -= (float) 2.5;
					yCor -= (float) 2.5;
				}
				if (explosion >= 200){
					explosion = 0;
					}
				timer.cancel();
			}
		};
		timer.schedule(task, 200);
	}
}