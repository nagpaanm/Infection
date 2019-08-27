package infection;

public class Blob extends Enemy{
	public int size = 60;
	public int reduction = 5;
	
	public Blob(int destX, int destY) {
		super(destX, destY);
	}
	
	public boolean detectCollision(float newX, float newY, int newSize){
		if (xCor - newSize < newX && xCor + size > newX && yCor - newSize < newY && yCor + size > newY){
			return true;
		}
		// else
		return false;
	}

}
