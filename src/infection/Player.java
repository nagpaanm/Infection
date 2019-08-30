package infection;

import java.util.concurrent.ThreadLocalRandom;

public class Player {
	
	public int size = 45;
	public int xCor = (int) Main.SCREENX/2;
	public int yCor = (int) Main.SCREENY/2;
	public int xSpeed = 0;
	public int ySpeed = 0;
	public int maxSpeed = 2;
	public int health = 100;
	public int maxHealth = 100;
	public int damageE = 50; //50
	public int damageG = 75; //75
	public int damageF = 15;
	public int grenadeCount = 0;
	public int maxGrenade = 1;//3

	public boolean detectCollision(float newX, float newY, int newSize){
		if (xCor - (newSize-2) <= newX && xCor + size >= (newX+2) && yCor - (newSize-2) <= newY && yCor + size >= (newY+2)){
			return true;
		}
		//else
		return false;
	}
	
	public void relocate(){
		xCor = ThreadLocalRandom.current().nextInt(0, Main.SCREENX - size);
		yCor = ThreadLocalRandom.current().nextInt(0, Main.SCREENY - size);
	}

}
