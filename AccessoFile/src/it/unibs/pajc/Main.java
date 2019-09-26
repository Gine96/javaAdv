package it.unibs.pajc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	public static String read(File file) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			StringBuffer sb = new StringBuffer();
			String s;
			while((s=in.readLine())!=null) {
				sb.append(in.readLine());
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
	
	public static void main(String[] args) {
		File file = new File("prova.txt");
		System.out.println(file.getAbsolutePath());
		try {
			if(file.createNewFile()) 
				System.out.println("File creato!");
			else
				System.out.println("File già esistente!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String text = read(file);
		System.out.println(text);
	}
}
