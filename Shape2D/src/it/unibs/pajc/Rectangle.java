package it.unibs.pajc;

public class Rectangle implements Shape2D{
	private int heigth;
	private int width;
	
	public Rectangle(int heigth, int width) {
		this.heigth=heigth;
		this.width=width;
	}
	
	public double perimeter() {
		return (getHeigth()+width)*2;
	}
	
	public double area() {
		return getHeigth()*width;
	}

	@Override
	public String draw() {
		StringBuffer drawed = new StringBuffer();
		for(int i=0;i<getHeigth();i++) {
			for(int j=0;j<width;j++) {
				drawed.append('*');
			}
			drawed.append('\n');
		}
		return drawed.toString();
	}
	
	public String toString(){
		return String.format("Rettangolo: %d %d", getHeigth(), width);
	}

	public int getHeigth() {
		return heigth;
	}

}
