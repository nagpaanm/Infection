package infection;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

public class GrenadePickUp {
	public int size = 15;
	public int xCor = ThreadLocalRandom.current().nextInt(0, Main.screenX - size);
	public int yCor = ThreadLocalRandom.current().nextInt(0, Main.screenY - size);
	public boolean pickedUp = false;
	public boolean collectable = false;
	
	public void create(Graphics g){
		g.setColor(new Color(46,139,87));
		g.fillOval(xCor, yCor, size, size);
		g.fillOval(xCor + 12, yCor, size, size);
		g.fillOval(xCor + 6, yCor + 5, size, size);
		g.setColor(Color.BLACK);
		g.drawOval(xCor, yCor, size, size);
	}
	
	public void draw(Graphics g){
		g.setColor(new Color(46,139,87));
		g.fillOval(10, Main.screenY - 30, 15, 15);
		g.fillOval(22, Main.screenY - 30, 15, 15);
		g.fillOval(16, Main.screenY - 25, 15, 15);
		g.setColor(Color.BLACK);
		g.drawOval(10, Main.screenY - 30, 15, 15);
	}

}
