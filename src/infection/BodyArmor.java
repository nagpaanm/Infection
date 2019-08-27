package infection;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

public class BodyArmor {
	public int size = 20;
	public int xCor = ThreadLocalRandom.current().nextInt(0 + size, Main.screenX - 2*size);
	public int yCor = ThreadLocalRandom.current().nextInt(0 + size, Main.screenY - 2*size);
	public boolean collectable = false;
	public boolean collected = false;
	
	public void create(Graphics g){
		collectable = true;
		g.setColor(new Color(135,206,250));
		g.fillRoundRect(xCor, yCor, size, size, 2, 2);
		g.setColor(Color.BLACK);
		g.drawRoundRect(xCor, yCor, size, size, 2, 2);
	}
	
	public void createArmor(Graphics g, int playerXCOR, int playerYCOR, int playerSIZE){
		collected = true;
		g.setColor(new Color(135,206,250));
		g.drawOval(playerXCOR, playerYCOR, playerSIZE, playerSIZE);
		g.drawOval(playerXCOR + 1, playerYCOR + 1, playerSIZE - 2 , playerSIZE - 2);
	}

}

