package it.unibs.pajc;

public class ContaCaratteri implements StringTransformer{

	public String transform(String in) {
		return String.format("%s\t[%d]", in, in.trim().length());
	}

}
