package it.unibs.pajc;

public class Square extends Rectangle {
	
	public Square(int side) {
		super(side,side);
	}
	
	public String toString(){
		return String.format("Quadrato: %d", getHeigth());
	}
}
