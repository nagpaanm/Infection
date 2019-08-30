package infection;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

public class Blood {
	
	public int xCor;
	public int yCor;
	int size = ThreadLocalRandom.current().nextInt(7, 14);
	int variance = ThreadLocalRandom.current().nextInt(-15, 15);
	int variance2 = ThreadLocalRandom.current().nextInt(-50, 50);
	int variance3 = ThreadLocalRandom.current().nextInt(-5, 5);
	int variance4 = ThreadLocalRandom.current().nextInt(-25, 25);
	int variance5 = ThreadLocalRandom.current().nextInt(-10, 10);
	public int x1 = ThreadLocalRandom.current().nextInt(50, Main.SCREENX - 50);
	public int x2 = ThreadLocalRandom.current().nextInt(50, Main.SCREENX - 50);
	public int x3 = ThreadLocalRandom.current().nextInt(50, Main.SCREENX - 50);
	public int x4 = ThreadLocalRandom.current().nextInt(50, Main.SCREENX - 50);
	public int x5 = ThreadLocalRandom.current().nextInt(50, Main.SCREENX - 50);
	public int y1 = ThreadLocalRandom.current().nextInt(50, Main.SCREENY - 50);
	public int y2 = ThreadLocalRandom.current().nextInt(50, Main.SCREENY - 50);
	public int y3 = ThreadLocalRandom.current().nextInt(50, Main.SCREENY - 50);
	public int y4 = ThreadLocalRandom.current().nextInt(50, Main.SCREENY - 50);
	public int y5 = ThreadLocalRandom.current().nextInt(50, Main.SCREENY - 50);
	
	public Blood(int xCor, int yCor){
		this.xCor = xCor;
		this.yCor = yCor;
	}
	
	public void create(Graphics g){
		g.setColor(Color.RED);
		g.fillOval(xCor + variance, yCor + variance3, size, size);
		g.fillOval(xCor - variance2, yCor + variance, size, size);
		g.fillOval(xCor + variance3, yCor + variance2, size, size);
		g.fillOval(xCor - variance4, yCor + variance5, size, size);
		g.fillOval(xCor + variance5, yCor + variance4, size, size);
	}
	
	public void createMain(Graphics g){
		g.setColor(Color.RED);
                
                /*
                for (int i = 0; i < 500; i++){
                    	size = ThreadLocalRandom.current().nextInt(10, 17);
                        variance = ThreadLocalRandom.current().nextInt(-30, 30);
                        variance2 = ThreadLocalRandom.current().nextInt(-15, 15);
                        variance3 = ThreadLocalRandom.current().nextInt(-50, 50);
                        variance4 = ThreadLocalRandom.current().nextInt(-15, 15);
                        variance5 = ThreadLocalRandom.current().nextInt(-15, 15);
                        int x1 = ThreadLocalRandom.current().nextInt(50, Main.SCREENX - 50);
                        int y1 = ThreadLocalRandom.current().nextInt(50, Main.SCREENY - 50);
                        g.fillOval(x1 - variance, y1 + variance4, size, size);
                        g.fillOval(x1 + variance3, y1 + variance, size, size);
                        g.fillOval(x1 - variance5, y1 + variance2, size, size);
                        g.fillOval(x1 + variance2, y1 + variance5, size, size);
                        g.fillOval(x1 + variance4, y1 + variance3, size, size);
                }
                */
                
		g.fillOval(x1 - variance, y1 + variance4, size, size);
		g.fillOval(x1 + variance3, y1 + variance, size, size);
		g.fillOval(x1 - variance5, y1 + variance2, size, size);
		g.fillOval(x1 + variance2, y1 + variance5, size, size);
		g.fillOval(x1 + variance4, y1 + variance3, size, size);
		
		g.fillOval(x2 + variance, y2 + variance4, size, size);
		g.fillOval(x2 + variance3, y2 + variance, size, size);
		g.fillOval(x2 - variance5, y2 + variance2, size, size);
		g.fillOval(x2 + variance2, y2 + variance5, size, size);
		g.fillOval(x2 + variance4, y2 + variance3, size, size);
		
		g.fillOval(x3 + variance, y3 + variance4, size, size);
		g.fillOval(x3 - variance3, y3 - variance, size, size);
		g.fillOval(x3 + variance5, y3 - variance2, size, size);
		g.fillOval(x3 + variance2, y3 + variance5, size, size);
		g.fillOval(x3 + variance4, y3 + variance3, size, size);
		
		g.fillOval(x4 - variance, y4 + variance4, size, size);
		g.fillOval(x4 + variance3, y4 + variance, size, size);
		g.fillOval(x4 + variance5, y4 + variance2, size, size);
		g.fillOval(x4 - variance2, y4 + variance5, size, size);
		g.fillOval(x4 + variance4, y4 + variance3, size, size);
		
		g.fillOval(x5 + variance, y5 + variance4, size, size);
		g.fillOval(x5 + variance3, y5 - variance, size, size);
		g.fillOval(x5 - variance5, y5 + variance2, size, size);
		g.fillOval(x5 + variance2, y5 - variance5, size, size);
		g.fillOval(x5 - variance4, y5 + variance3, size, size);
                
	}
}
