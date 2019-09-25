package it.unibs.pajc;

public class Ellipse implements Shape2D{
	
	private int semiAxeX;
	private int semiAxeY;
	
	public Ellipse(int semiAxeX, int semiAxeY) {
		this.semiAxeX=semiAxeX;
		this.semiAxeY=semiAxeY;
	}
	
	public double perimeter() {
		return 2*Math.PI*Math.sqrt(((getSemiAxeX()*getSemiAxeX())+(getSemiAxeY()*getSemiAxeY()))/2);
	}
	
	public double area() {
		return Math.PI*getSemiAxeX()*getSemiAxeY();
	}

	private int getSemiAxeY() {
		return semiAxeY;
	}

	@Override
	public String draw() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString(){
		return String.format("Ellisse: %d %d", getSemiAxeX(), getSemiAxeY());
	}

	public int getSemiAxeX() {
		return semiAxeX;
	}
}
