package infection;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	
	public int size = 8;
	public float xCor;
	public float yCor;
	public int destX;
	public int destY;
	public float speedX;
	public float speedY;
	public float hypotenuse = 10;
	public float differenceX;
	public float differenceY;
	public float angle;
	public float distance;
	
	public Bullet(int xCor, int yCor, int destX, int destY, boolean collision){
		this.xCor = xCor;
		this.yCor = yCor;
		this.destX = destX;
		this.destY = destY;
		speedX = (destX - xCor);
		speedY = (destY - yCor);
		distance = (float) Math.sqrt((speedX*speedX + speedY*speedY));
		angle = (float) Math.toDegrees(Math.atan(speedY/speedX));
		angle = (float) (angle * Math.PI / 180); // covert to radians
		differenceX = (float) (Math.cos(angle)*hypotenuse);
		differenceY = (float) (Math.sin(angle)*hypotenuse);
		if (angle >= 0.0 & speedX < 0.0 & speedY <= 0.0){
			// shoot upper left quadrant
			differenceX *= -1;
			differenceY *= -1;
		}
		if (angle < 0.0 && speedX < 0.0 && speedY > 0.0){
			// shoot lower left quadrant
			differenceX *= -1;
			differenceY *= -1;
		}
		if (speedX == 0 && speedY == 0){
			size = 0;
		}
	}
	
	public void createBullet(Graphics g){
		g.setColor(Color.BLACK);
		g.fillOval((int) xCor, (int) yCor, size, size);
		this.xCor += differenceX;
		this.yCor += differenceY;
	}

}
