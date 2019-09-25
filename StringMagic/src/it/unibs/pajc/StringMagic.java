package it.unibs.pajc;

public class StringMagic {

	private final String stringa;

	public StringMagic(String stringa) {
		this.stringa=stringa;
	}
	/*
	//come fatto io
	public String reverse1() {
		String reversed="";
		
		for(int i=this.stringa.length()-1;i>=0;i--) 
			/*
			 * usare il + per concatenazione di stringhe implica che 
			 * il complilatore crea oggetti per ogni +
			 * ==> si può usare lo StringBuilder
			 * ==> usare un array
			 * */
			/*
			reversed+=this.stringa.charAt(i);
		return reversed;
	}*/
	
	public String getString() {
		//il this. non è necessario qua
		return stringa;
	}
	
	public String reverse() {
		char[] source = stringa.toCharArray();
		char[] dest = new char[source.length];
		for(int i=0;i<source.length;i++) 
			dest[i]=source[source.length-i-1];
		return new String(dest);
	}
	
	public String maskVowel() {
		return stringa.replaceAll("[aeiou]", "*");
	}
	
	public int freq(char c) {
		int count=0;
		//la collezioen che usi implementa Ienuberable
		for(char ch:stringa.toCharArray())
			if(ch==c)
				count++;
		return count;
	}

}
