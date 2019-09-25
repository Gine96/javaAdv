package it.unibs.pajc;

public class Circle extends Ellipse{
	
	public Circle(int radius) {
		super(radius,radius);
	}
	
	public String toString(){
		return String.format("Circle: %d", getSemiAxeX());
	}
}
