package it.unibs.pajc;

public class StringMagicMain {

	public static void main(String[] args) {
		StringMagic stringa = new StringMagic("aeiuolmaogary");
		System.out.println(stringa.reverse());
		System.out.println(stringa.maskVowel());
		System.out.println(stringa.freq('o'));

	}

}
