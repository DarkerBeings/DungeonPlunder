package game;

public class Position {

	private int yPos;

	private int xPos;

	private int ylength;

	public Position(int x, int y, int ylength) {
		yPos = y;
		xPos = x;
		this.ylength = ylength;
	}

	public String getLocation() {
		return "(" + xPos + ", " + (ylength - yPos - 1) + ")";
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public void setPosition(int x, int y, int ylength) {
		xPos = x;
		yPos = y;
		this.ylength = ylength;
	}

	public void moveUp(int shiftUp) {
		yPos += shiftUp;
	}

	public void moveSide(int shiftSide) {
		xPos += shiftSide;
	}
}
