package infection;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

public class Health {
	public int sizeL = 20;
	public int sizeW = 25;
	public int xCor = ThreadLocalRandom.current().nextInt(0, Main.screenX - sizeW);
	public int yCor = ThreadLocalRandom.current().nextInt(0, Main.screenY - sizeL);
	public boolean pickedUp = false;
	public boolean collectable = false;
	public int spawnRate = 20;
	
	public void create(Graphics g){
		g.setColor(new Color(34,139,34));
		g.fill3DRect(xCor, yCor, sizeW, sizeL, false);
		g.setColor(Color.WHITE);
		g.drawLine(xCor + (sizeW/2), yCor + 6, xCor + (sizeW/2), yCor + sizeL - 6);
		g.drawLine(xCor + 8, yCor + (sizeL/2), xCor + sizeW - 8, yCor + (sizeL/2));
	}

}

