package it.unibs.pajc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	/*
	 * Voglio passare un funzione come parametro:
	 * -->posso usare un oggetto che implementa un interfaccia
	 * */
	public static String read(File file, StringTransformer transf) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			StringBuffer sb = new StringBuffer();
			String s;
			int numeroRiga=1;
			while((s=in.readLine())!=null) {
				s=transf.transform(s);
				sb.append(String.format("%3d| %s\n", numeroRiga++, s));
			}
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	public static String read(File file) {
		return read(file, new DefaultTransformer());
	}
		
	public static void main(String[] args) {
		File file = new File("src/it/unibs/pajc/Main.java");
		String text = read(file, new FirmaTesto());
		System.out.println(text);
		
	}
}
