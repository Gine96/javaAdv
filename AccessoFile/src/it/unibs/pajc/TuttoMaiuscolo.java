package it.unibs.pajc;

public class TuttoMaiuscolo implements StringTransformer{
	public String transform(String in) {
		return in.toUpperCase();
	}
}
