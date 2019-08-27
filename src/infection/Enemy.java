package infection;

import java.util.concurrent.ThreadLocalRandom;

public class Enemy{
	public int size = 20;
	public int health = 100;
	public int reducedSize;
	public int spawnX = ThreadLocalRandom.current().nextInt(-500, Main.screenX + 500); //500
	public int spawnY = ThreadLocalRandom.current().nextInt(-500, Main.screenY + 500); //500
	public int spawnDist = 400;
	public int xCor;
	public int yCor;
	public int speed = ThreadLocalRandom.current().nextInt(1, 2); // 1, 2
	public int destX;
	public int destY;

	public Enemy(int destX, int destY){
		this.destX = destX;
		this.destY = destY;
		if (Math.abs(spawnX - destX) < spawnDist && Math.abs(spawnY - destY) < spawnDist){
			while (Math.abs(spawnX - destX) < spawnDist && Math.abs(spawnY - destY) < spawnDist){
				spawnX = ThreadLocalRandom.current().nextInt(-500, Main.screenX + 500);
				spawnY = ThreadLocalRandom.current().nextInt(-500, Main.screenY + 500);
			}
		}
		xCor = spawnX;
		yCor = spawnY;
	}

	public void move(){
		if (xCor > destX + 10){
			xCor -= speed;
		}
		if (xCor < destX + 10){
			xCor += speed;
		}
		if (yCor > destY + 10){
			yCor -= speed;
		}
		if (yCor < destY+ 10){
			yCor += speed;
		}
	}
	
	public boolean detectCollision(float newX, float newY, int newSize){
		if (xCor - newSize < newX && xCor + size > newX && yCor - newSize < newY && yCor + size > newY){
			return true;
		}
		// else
		return false;
	}
	
	/** method initially used in draft, but no need for it in final copy. */
	public void relocate(){
		xCor = ThreadLocalRandom.current().nextInt(0, Main.screenX);
		yCor = ThreadLocalRandom.current().nextInt(0, Main.screenY);
		size = 20;
	}
}