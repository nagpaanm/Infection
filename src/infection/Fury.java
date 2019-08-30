package infection;


public class Fury extends Enemy {
	
	public int size = 25;
	public int speed = 1;
	public int health = 200;

	public Fury(int destX, int destY) {
		super(destX, destY);
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
	
        @Override
	public boolean detectCollision(float newX, float newY, int newSize){
            return xCor - newSize < newX && xCor + size > newX && yCor - newSize < newY && yCor + size > newY;
	}
	

}

