package infection;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class LevelUp {
	
	public int xCor = Main.SCREENX;
	public int yCor = Main.SCREENY;
	public int size = 20;
	public boolean leveled = false;
	
	public void create(Graphics g, int level){
		g.setColor(new Color(55, 179, 252));
		g.setFont(new Font("ariel", Font.BOLD, size));
		if (level < 10){
			g.drawString(level + "", xCor/2 - size/3 , yCor/2 + size/3);
		}
		else{
			g.drawString(level + "", xCor/2 - size/2 , yCor/2 + size/3);
		}
		size += 5;
	}

}
