package it.unibs.pajc;

import java.io.File;

public class Main {
	/*
	 * Voglio passare un funzione come parametro:
	 * -->posso usare un oggetto che implementa un interfaccia
	 * */
			
	public static void main(String[] args) {
		//File file = new File("src/it/unibs/pajc/Main.java");
		String filename = "bin/it/unibs/pajc/Main.class";
		//String text = FileUtil.read(file, new FirmaTesto());
		//System.out.println(text);
		FileUtil.dump(filename);
	}
}
