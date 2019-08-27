package infection;

import java.awt.Color;
import java.awt.Graphics;

public class Fire {
	public int xCor;
	public int yCor;
	public float size = 5;
	public boolean created = false;
	
	public Fire(int xCor, int yCor){
		this.xCor = xCor;
		this.yCor = yCor;
	}
	
	public void create(Graphics g){
		g.setColor(new Color(255,215,0));
		g.fillOval(xCor, yCor, (int) size, (int) size);
		created = true;
	}
	
	public void animate(){
		if (size < 100 && size != 0){
			size += 2;
		}
		if (size == 99){
			size = 0;
		}
	}
}

